package com.kaiba.demo.redis;

import redis.clients.jedis.*;

import java.util.*;

/**
 * Created by luliru on 2017/4/15.
 */
public class JedisUtilTest {

    private static Jedis jedis;
    private static JedisPool pool;
    private static int EXPIRE_SECONDS = 300;

    public static void main(String[] args) {
        init();
        testBasicString();
        returnJedis();
        destroy();
    }

    /**
     * 单台模式
     */
    public static void init(){
        //public JedisPool(final GenericObjectPoolConfig poolConfig, final String host, int port, int timeout, final String password, final int database)
        //new JedisPool(new JedisPoolConfig(), "localhost", 6379, 10000, null, SMS_REDIS_DB);
        pool = new JedisPool(getConfig(),"localhost");
        jedis = pool.getResource();
        //jedis.auth("password");

        int DBIndex = 0;
        jedis.connect();
        jedis.select(DBIndex);
    }

    /**
     * 分片模式（ShardedJedis）
     */
    public static void shardingInit(){
        JedisShardInfo jedisShardInfo = new JedisShardInfo("localhost");
        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
        list.add(jedisShardInfo);

        ShardedJedisPool pool = new ShardedJedisPool(getConfig(), list);

        // 从池中获取一个Jedis对象
        ShardedJedis jedis = pool.getResource();
        // 释放对象池
        pool.returnResource(jedis);
    }

    /**
     * 集群模式（BinaryJedisCluster）
     */
    public static void clusterInit(){
        HostAndPort hp0 = new HostAndPort("localhost", 7000);
        HostAndPort hp1 = new HostAndPort("localhost", 7001);

        Set<HostAndPort> hps = new HashSet<HostAndPort>();
        hps.add(hp0);
        hps.add(hp1);

        // 超时，最大的转发数，最大链接数，最小链接数都会影响到集群
        JedisCluster jedisCluster = new JedisCluster(hps, 5000, 10, getConfig());
    }

    /**
     * sentinel群集
     */
    public static void sentinelInit(){
        JedisPoolConfig config = getConfig();

        String masterName = "mymaster";
        Set<String> sentinels = new HashSet<>();
        sentinels.add("192.168.137.128:26379");
        sentinels.add("192.168.137.128:26380");
        sentinels.add("192.168.137.128:26381");

        JedisSentinelPool pool = new JedisSentinelPool(masterName, sentinels, config);
        jedis = pool.getResource();
    }

    public static JedisPoolConfig getConfig(){
        JedisPoolConfig config = new JedisPoolConfig();

        //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        config.setBlockWhenExhausted(true);

        //设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
        config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");

        //是否启用pool的jmx管理功能, 默认true
        config.setJmxEnabled(true);

        //MBean ObjectName = new ObjectName("org.apache.commons.pool2:type=GenericObjectPool,name=" + "pool" + i); 默 认为"pool", JMX不熟,具体不知道是干啥的...默认就好.
        config.setJmxNamePrefix("pool");

        //是否启用后进先出, 默认true
        config.setLifo(true);

        //最大空闲连接数, 默认8个
        config.setMaxIdle(8);

        //最大连接数, 默认8个
        config.setMaxTotal(8);

        //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        config.setMaxWaitMillis(-1);

        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        config.setMinEvictableIdleTimeMillis(1800000);

        //最小空闲连接数, 默认0
        config.setMinIdle(0);

        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        config.setNumTestsPerEvictionRun(3);

        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
        config.setSoftMinEvictableIdleTimeMillis(1800000);

        //在获取连接的时候检查有效性, 默认false
        config.setTestOnBorrow(false);

        //在空闲时检查有效性, 默认false
        config.setTestWhileIdle(false);

        //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        config.setTimeBetweenEvictionRunsMillis(-1);

        return config;
    }

    public static void returnJedis(){
        pool.returnResource(jedis);
    }

    public static void destroy(){
        pool.destroy();
    }

    /**
     * Redis存储初级的字符串
     */
    public static void testBasicString(){
        jedis.set("name","minxr");
        jedis.expire("name",EXPIRE_SECONDS);
        jedis.get("name");		//minxr

        jedis.append("name","jarorwar");   //很直观，类似map 将jarorwar append到已经有的value之后
        jedis.get("name");		//执行结果:minxrjarorwar

        //直接覆盖原来的数据
        jedis.set("name","闵晓荣");

//过期时间为1s，只有该项原来不存在时，才添加
        String result = jedis.set("key", "value", "NX", "EX", 1);

        //删除key对应的记录
        jedis.del("name");

        /**
         * mset相当于
         * jedis.set("name","minxr");
         * jedis.set("jarorwar","闵晓荣");
         */
        jedis.mset("name","minxr","jarorwar","闵晓荣");
        jedis.mget("name","jarorwar");
    }

    /**
     * jedis操作Map
     */
    public static void testMap(){
        Map<String,String> user=new HashMap<String,String>();
        user.put("name","minxr");
        user.put("pwd","password");
        jedis.hmset("user",user);
        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget("user", "name");
        System.out.println(rsmap);

        //删除map中的某个键值
//        jedis.hdel("user","pwd");
        System.out.println(jedis.hmget("user", "pwd")); //因为删除了，所以返回的是null
        System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数1
        System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true
        System.out.println(jedis.hkeys("user"));//返回map对象中的所有key  [pwd, name]
        System.out.println(jedis.hvals("user"));//返回map对象中的所有value  [minxr, password]

        Iterator<String> iter=jedis.hkeys("user").iterator();
        while (iter.hasNext()){
            String key = iter.next();
            System.out.println(key+":"+jedis.hmget("user",key));
        }
    }

    /**
     * jedis操作List
     */
    public static void testList(){
        //开始前，先移除所有的内容
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework",0,-1));
        //先向key java framework中存放三条数据
        jedis.lpush("java framework","spring");
        jedis.lpush("java framework","struts");
        jedis.lpush("java framework","hibernate");
        //再取出所有数据jedis.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange("java framework",0,-1));
    }

    /**
     * jedis操作Set
     */
    public static void testSet(){
        //添加
        jedis.sadd("sname","minxr");
        jedis.sadd("sname","jarorwar");
        jedis.sadd("sname","闵晓荣");
        jedis.sadd("sanme","noname");
        //移除noname
        jedis.srem("sname","noname");
        System.out.println(jedis.smembers("sname"));//获取所有加入的value
        System.out.println(jedis.sismember("sname", "minxr"));//判断 minxr 是否是sname集合的元素
        System.out.println(jedis.srandmember("sname"));
        System.out.println(jedis.scard("sname"));//返回集合的元素个数
    }

    public static void test() throws InterruptedException {
        //keys中传入的可以用通配符
        System.out.println(jedis.keys("*")); //返回当前库中所有的key  [sose, sanme, name, jarorwar, foo, sname, java framework, user, braand]
        System.out.println(jedis.keys("*name"));//返回的sname   [sname, name]
        System.out.println(jedis.del("sanmdde"));//删除key为sanmdde的对象  删除成功返回1 删除失败（或者不存在）返回 0
        System.out.println(jedis.ttl("sname"));//返回给定key的有效时间，如果是-1则表示永远有效
        jedis.setex("timekey", 10, "min");//通过此方法，可以指定key的存活（有效时间） 时间为秒
        Thread.sleep(5000);//睡眠5秒后，剩余时间将为<=5
        System.out.println(jedis.ttl("timekey"));   //输出结果为5
        jedis.setex("timekey", 1, "min");        //设为1后，下面再看剩余时间就是1了
        System.out.println(jedis.ttl("timekey"));  //输出结果为1
        System.out.println(jedis.exists("key"));//检查key是否存在
        System.out.println(jedis.rename("timekey","time"));
        System.out.println(jedis.get("timekey"));//因为移除，返回为null
        System.out.println(jedis.get("time")); //因为将timekey 重命名为time 所以可以取得值 min

        //jedis 排序
        //注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
        jedis.del("a");//先清除数据，再加入数据进行测试
        jedis.rpush("a", "1");
        jedis.lpush("a","6");
        jedis.lpush("a","3");
        jedis.lpush("a","9");
        System.out.println(jedis.lrange("a",0,-1));// [9, 3, 6, 1]
        System.out.println(jedis.sort("a")); //[1, 3, 6, 9]  //输入排序后结果
        System.out.println(jedis.lrange("a",0,-1));
    }
}

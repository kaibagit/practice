package com.kaiba.demo.mongodb3;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.PushOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.geojson.LineString;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Polygon;
import com.mongodb.client.model.geojson.Position;
import org.bson.BsonType;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Indexes.geo2d;
import static com.mongodb.client.model.Indexes.geo2dsphere;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Accumulators.*;

/**
 * Created by luliru on 2017/5/11.
 */
public class CrudExamples {

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        MongoDatabase database = mongoClient.getDatabase("testdb");
//        collectionCrud(database);
//        collectionFind(database);
//        collectionAggregates(database);
        collectionGeospatial(database);

        mongoClient.close();
    }

    private static void collectionCrud(MongoDatabase database) {
        MongoCollection<Document> mc = database.getCollection("language");

        mc.insertOne(new Document("oop", "java"));

        //插入一个包含两个字段的文档
        Document doc = new Document("oop", "csharp").append("copyright", "microsoft");
        mc.insertOne(doc);

        //查找并修改一个文档
        mc.findOneAndReplace(new Document("oop", "java"), new Document("oop", "java").append("copyright", "oracle"));

        //删除一个文档
        mc.deleteOne(new Document("oop", "java"));

        //删除全部文档
        mc.deleteMany(new Document());

        //重新插入测试文档
        mc.insertOne(new Document("oop", "java").append("copyright", "oracle"));
        mc.insertOne(new Document("oop", "csharp").append("copyright", "microsoft"));

        //$set 文档中存在指定字段则修改,没有则添加
        mc.updateMany(new Document(), set("rank", 100));

        //$unset 文档中存在指定字段则删除该字段
        mc.updateOne(new Document("oop", "csharp"), unset("rank"));

        //$inc 文档中存在指定字段则相加,没有则添加
        mc.updateOne(new Document("oop", "csharp"), inc("rank", 30));

        mc.updateOne(new Document("oop", "csharp"), inc("rank", 31));

        //$setOnInsert 在更新时指定upsert=true并实际触发了插入操作时生效
        mc.updateOne(new Document("oop", "swift").append("copyright", "apple"), setOnInsert("rank", 100), new UpdateOptions().upsert(true));

        //$mul 相乘
        mc.updateOne(new Document("oop", "java"), mul("rank", 0.2));

        //$rename 重命名
        mc.updateMany(new Document(), rename("rank", "ranks"));

        //$min 取当前值和指定值之间比较小的
        mc.updateMany(new Document(), com.mongodb.client.model.Updates.min("ranks", 50));

        //$max 取当前值和指定值之间比较大的
        mc.updateMany(new Document(), com.mongodb.client.model.Updates.max("ranks", 40));

        //$currentDate
        mc.updateMany(new Document("oop", "java"), currentDate("add"));

        //$currentTimestamp
        mc.updateMany(new Document("oop", "java"), currentTimestamp("lastModified"));

        //$addToSet 添加一个元素到不重复集合
        mc.updateMany(new Document("oop", "java"), com.mongodb.client.model.Updates.addToSet("keywords", "for"));
        mc.updateMany(new Document("oop", "java"), com.mongodb.client.model.Updates.addToSet("keywords", "for"));

        //$addEachToSet 添加一组元素到不重复集合
        mc.updateMany(new Document("oop", "java"), addEachToSet("keywords", Arrays.asList("while", "true", "do", "new", "override")));
        mc.updateMany(new Document("oop", "java"), addEachToSet("keywords", Arrays.asList("while", "true", "do", "new", "override")));

        //$popFirst 删除第一个元素
        mc.updateMany(new Document("oop", "java"), popFirst("keywords"));

        //$popLast 删除最后一个元素
        mc.updateMany(new Document("oop", "java"), popLast("keywords"));

        //$pull 删除指定元素
        mc.updateMany(new Document("oop", "java"), pull("keywords", "new"));

        //$pullByFilter 根据Filters删除
        mc.updateMany(new Document("oop", "java"), pullByFilter(Filters.gte("keywords", "true")));

        //$pullAll 删除一组元素
        mc.updateMany(new Document("oop", "java"), pullAll("keywords", Arrays.asList("while", "true", "do", "new", "override")));

        //$push 添加一个元素到可重复集合
        mc.updateMany(new Document("oop", "java"), com.mongodb.client.model.Updates.push("scores", 89));

        //$pushEach 添加一组元素到可重复集合
        mc.updateMany(new Document("oop", "java"), pushEach("scores", Arrays.asList(89, 90, 92)));

        //在集合的指定位置插入一组元素
        mc.updateMany(new Document("oop", "java"), pushEach("scores", Arrays.asList(11, 12, 13), new PushOptions().position(0)));

        //在集合的指定位置插入一组元素并倒序排列
        mc.updateMany(new Document("oop", "java"), pushEach("scores", Arrays.asList(40, 41), new PushOptions().sort(-1)));

        //在集合的指定位置插入一组元素, 倒序排列后保留前3个
        mc.updateMany(new Document("oop", "java"), pushEach("scores", Arrays.asList(60, 61), new PushOptions().sort(-1).slice(3)));

        //插入一组内嵌文档
        Bson bson = pushEach("experts",
                Arrays.asList(new Document("first", "Rod").append("last", "Johnson"),
                        new Document("first", "Doug").append("last", "Cutting")));
        mc.updateOne(new Document("oop", "java"), bson);

        //combine 组合Bson
        bson = combine(set("author", "James Gosling"), set("version", "8.0"));
        mc.updateOne(new Document("oop", "java"), bson);

        FindIterable<Document> iterable = mc.find();
        iterable.forEach(new Block<Document>() {
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
    }

    private static void collectionFind(MongoDatabase database){
        MongoCollection<Document> mc = database.getCollection("blog");
        //每次执行前清空集合以方便重复运行
        mc.drop();

        //插入用于测试的文档
        Document doc1 = new Document("title", "good day").append("owner", "tom").append("words", 300)
                .append("comments", Arrays.asList(new Document("author", "joe").append("score", 3).append("comment", "good"), new Document("author", "white").append("score", 1).append("comment", "oh no")));
        Document doc2 = new Document("title", "good").append("owner", "john").append("words", 400)
                .append("comments", Arrays.asList(new Document("author", "william").append("score", 4).append("comment", "good"), new Document("author", "white").append("score", 6).append("comment", "very good")));
        Document doc3 = new Document("title", "good night").append("owner", "mike").append("words", 200)
                .append("tag", Arrays.asList(1, 2, 3, 4));
        Document doc4 = new Document("title", "happiness").append("owner", "tom").append("words", 1480)
                .append("tag", Arrays.asList(2, 3, 4));
        Document doc5 = new Document("title", "a good thing").append("owner", "tom").append("words", 180)
                .append("tag", Arrays.asList(1, 2, 3, 4, 5));
        mc.insertMany(Arrays.asList(doc1, doc2, doc3, doc4, doc5));


        //创建单字段索引
        mc.createIndex(new Document("words", 1));
        //创建组合索引(同样遵循最左前缀原则)
        mc.createIndex(new Document("title", 1).append("owner", -1));
        //创建全文索引
        mc.createIndex(new Document("title", "text"));

        //查询全部
        FindIterable<Document> iterable = mc.find();

        //查询title=good
        iterable = mc.find(new Document("title", "good"));

        //查询title=good and owner=tom
        iterable = mc.find(new Document("title", "good").append("owner", "tom"));

        //查询title like %good% and owner=tom
        iterable = mc.find(and(regex("title", "good"), eq("owner", "tom")));

        //查询全部按title排序
        iterable = mc.find().sort(ascending("title"));

        //查询全部按owner,title排序
        iterable = mc.find().sort(ascending("owner", "title"));

        //查询全部按words倒序排序
        iterable = mc.find().sort(descending("words"));

        //查询owner=tom or words>350
        iterable = mc.find(new Document("$or", Arrays.asList(new Document("owner", "tom"), new Document("words", new Document("$gt", 350)))));

        //返回title和owner字段
        iterable = mc.find().projection(include("title", "owner"));

        //返回除title外的其他字段
        iterable = mc.find().projection(exclude("title"));

        //不返回_id字段
        iterable = mc.find().projection(excludeId());

        //返回title和owner字段且不返回_id字段
        iterable = mc.find().projection(fields(include("title", "owner"), excludeId()));

        //内嵌文档匹配
        iterable = mc.find(new Document("comments.author", "joe"));

        //一个错误的示例, 想查询评论中包含作者是white且分值>2的, 返回结果不符合预期
        iterable = mc.find(new Document("comments.author", "white").append("comments.score", new Document("$gt", 2)));

        //上面的需求正确的写法
        iterable = mc.find(Projections.elemMatch("comments", Filters.and(eq("author", "white"), gt("score", 2))));

        //查找title以good开头的, 并且comments只保留一个元素
        iterable = mc.find(regex("title", "^good")).projection(slice("comments", 1));

        //全文索引查找
        iterable = mc.find(text("good"));

        //用Filters构建的title=good
        iterable = mc.find(eq("title", "good"));

        //$in 等同于sql的in
        iterable = mc.find(in("owner", "joe", "john", "william"));

        //$nin 等同于sql的not in
        iterable = mc.find(nin("owner", "joe", "john", "tom"));

        //查询内嵌文档
        iterable = mc.find(in("comments.author", "joe", "tom"));

        //$ne 不等于
        iterable = mc.find(ne("words", 300));

        //$and 组合条件
        iterable = mc.find(and(eq("owner", "tom"), gt("words", 300)));

        //较复杂的组合
        iterable = mc.find(and(or(eq("words", 300), eq("words", 400)), or(eq("owner", "joe"), size("comments", 2))));

        //查询第2个元素值为2的数组
        iterable = mc.find(eq("tag.1", 2));

        //查询匹配全部值的数组
        iterable = mc.find(all("tag", Arrays.asList(1, 2, 3, 4)));

        //$exists
        iterable = mc.find(exists("tag"));

        iterable = mc.find(type("words", BsonType.INT32));

        iterable.forEach(new Block<Document>() {
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
    }

    private static void collectionAggregates(MongoDatabase database){
        MongoCollection<Document> mc = database.getCollection("blog");
        //每次执行前清空集合以方便重复运行
        mc.drop();

        //插入用于测试的文档
        Document doc1 = new Document("title", "good day").append("owner", "tom").append("words", 300)
                .append("comments", Arrays.asList(new Document("author", "joe").append("score", 3).append("comment", "good"), new Document("author", "white").append("score", 1).append("comment", "oh no")));
        Document doc2 = new Document("title", "good").append("owner", "john").append("words", 400)
                .append("comments", Arrays.asList(new Document("author", "william").append("score", 4).append("comment", "good"), new Document("author", "white").append("score", 6).append("comment", "very good")));
        Document doc3 = new Document("title", "good night").append("owner", "mike").append("words", 200)
                .append("tag", Arrays.asList(1, 2, 3, 4));
        Document doc4 = new Document("title", "happiness").append("owner", "tom").append("words", 1480)
                .append("tag", Arrays.asList(2, 3, 4));
        Document doc5 = new Document("title", "a good thing").append("owner", "tom").append("words", 180)
                .append("tag", Arrays.asList(1, 2, 3, 4, 5));
        mc.insertMany(Arrays.asList(doc1, doc2, doc3, doc4, doc5));

        AggregateIterable<Document> iterable = mc.aggregate(Arrays.asList(match(eq("owner", "tom")),
                group("$author", sum("totalWords", "$words"))));

        // $match 确定复合条件的文档, 可组合多个条件
        iterable = mc.aggregate(Arrays.asList(match(and(eq("owner", "tom"), gt("words", 300)))));

        // $sum求和 $avg平均值 $max最大值 $min最小值
        iterable = mc.aggregate(Arrays.asList(
                match(in("owner", "tom", "john", "mike")),
                group("$owner", sum("totalWords", "$words"),
                        avg("averageWords", "$words"),
                        com.mongodb.client.model.Accumulators.max("maxWords", "$words"), com.mongodb.client.model.Accumulators.min("minWords", "$words"))));

        // $out 把聚合结果输出到集合
        mc.aggregate(Arrays.asList(
                match(in("owner", "tom", "john", "mike")),
                group("$owner", sum("totalWords", "$words"),
                        avg("averageWords", "$words"),
                        com.mongodb.client.model.Accumulators.max("maxWords", "$words"), com.mongodb.client.model.Accumulators.min("minWords", "$words")),
                out("wordsCount")));
        iterable = database.getCollection("wordsCount").aggregate(
                Arrays.asList(sample(3)));

        // 随机取3个文档, 仅返回title和owner字段
        iterable = mc.aggregate(Arrays.asList(sample(3),
                project(fields(include("title", "owner"), excludeId()))));

        // 从第2个文档开始取2个文档, 仅返回title和owner字段
        iterable = mc.aggregate(Arrays.asList(skip(1), limit(2),
                project(fields(include("title", "owner"), excludeId()))));

        // $lookup 和另一个集合关联
        database.getCollection("scores").drop();
        database.getCollection("scores").insertMany(
                Arrays.asList(
                        new Document("writer", "tom").append("score", 100),
                        new Document("writer", "joe").append("score", 95),
                        new Document("writer", "john").append("score", 80)));
        iterable = mc.aggregate(Arrays.asList(lookup("scores", "owner",
                "writer", "joinedOutput")));

        // 拆分comments为单个文档
        iterable = mc.aggregate(Arrays.asList(match(size("comments", 2)),
                project(fields(include("comments"), excludeId())),
                unwind("$comments")));

        System.out.println("distinct");
        DistinctIterable<String> di = mc.distinct("owner", String.class);
        di.forEach(new Block<String>() {
            public void apply(final String str) {
                System.out.println(str);
            }
        });
        System.out.println("------------------------------------------------------");
        System.out.println();

        System.out.println("count");
        long count = mc.count(Filters.eq("owner", "tom"));
        System.out.println("count=" + count);
        System.out.println("------------------------------------------------------");
        System.out.println();

        System.out.println("mapreduce");
        String map = "function() { var category; "
                + "if ( this.words >= 280 ) category = 'Long blogs'; "
                + "else category = 'Short blogs'; "
                + "emit(category, {title: this.title});}";

        String reduce = "function(key, values) { var cnt = 0; "
                + "values.forEach(function(doc) { cnt += 1; }); "
                + "return {count: cnt};} ";
        MapReduceIterable<Document> mi = mc.mapReduce(map, reduce);
        mi.forEach(new Block<Document>() {
            public void apply(final Document str) {
                System.out.println(str);
            }
        });
        System.out.println("------------------------------------------------------");
        System.out.println();
    }

    private static void collectionGeospatial(MongoDatabase database){
        MongoCollection<Document> mc = database.getCollection("people");
        mc.drop();

        Document doc1 = new Document("name", "tom").append("raid", Arrays.asList(10, 10)).append("gps", new Point(new Position(10, 10)));
        Document doc2 = new Document("name", "jone").append("raid", Arrays.asList(10.1, 10)).append("gps", new Point(new Position(10.1, 10)));
        Document doc3 = new Document("name", "john").append("raid", Arrays.asList(10, 10.1)).append("gps", new Point(new Position(10, 10.1)));
        Document doc4 = new Document("name", "jack").append("raid", Arrays.asList(9.9, 10)).append("gps", new Point(new Position(9.9, 10)));
        Document doc5 = new Document("name", "mary").append("raid", Arrays.asList(10, 9.9)).append("gps", new Point(new Position(10, 9.9)));
        Document doc6 = new Document("name", "abby").append("raid", Arrays.asList(10.2, 10)).append("gps", new Point(new Position(10.2, 10)));
        Document doc7 = new Document("name", "adam").append("raid", Arrays.asList(10.3, 10)).append("gps", new Point(new Position(10.3, 10)));
        Document doc8 = new Document("name", "barry").append("raid", Arrays.asList(10.4, 10)).append("gps", new Point(new Position(10.4, 10)));
        Document doc9 = new Document("name", "anne").append("raid", Arrays.asList(10.5, 10)).append("gps", new Point(new Position(10.5, 10)));
        mc.insertMany(Arrays.asList(doc1, doc2, doc3, doc4, doc5, doc6, doc7, doc8, doc9));

        mc.createIndex(geo2d("raid"));
        mc.createIndex(geo2dsphere("gps"));

        //$geoWithin 匹配任意几何图形内搜索
        FindIterable<Document> iterable = mc.find(Filters.geoWithin("raid", new Polygon(Arrays.asList(new Position(10.2, 10), new Position(10, 10.2), new Position(9.8, 10), new Position(10, 9.8), new Position(10.2, 10)))));

        //$geoWithinBox 在以左下角和右上角坐标构成方形内搜索
        iterable = mc.find(Filters.geoWithinBox("raid", 9.8, 9.8, 10.2, 10.2));

        //$geoWithinPolygon 在多边形内搜索
        List<Double> p1 = new ArrayList<>();
        List<Double> p2 = new ArrayList<>();
        List<Double> p3 = new ArrayList<>();
        p1.add(10d);
        p1.add(10d);
        p2.add(10.1);
        p2.add(10.16);
        p3.add(10.2);
        p3.add(10d);
        List<List<Double>> polygon = Arrays.asList(p1, p2, p3);
        iterable = mc.find(Filters.geoWithinPolygon("raid", polygon));

        p2.clear();
        p2.add(9.9);
        p2.add(10.16);
        p3.clear();
        p3.add(9.8);
        p3.add(10d);
        polygon = Arrays.asList(p1, p2, p3);
        iterable = mc.find(Filters.geoWithinPolygon("gps", polygon));

        //$geoWithinCenter 在指定圆心和半径的圆形内搜索
        iterable = mc.find(Filters.geoWithinCenter("raid", 10d, 10d, 0.25));

        //$geoWithinCenterSphere 在球体(地球)上指定圆心和弧度搜索, 例如搜索以[10,10]为中心500米内的文档, 参数为...10d, 10d, 0.5/6371
        iterable = mc.find(Filters.geoWithinCenterSphere("gps", 10d, 10d, 11d/6371));

        //$geoIntersects
        iterable = mc.find(Filters.geoIntersects("gps", new LineString(Arrays.asList(new Position(10, 10.1), new Position(10.1, 10), new Position(10, 9.9)))));

        //$near
        iterable = mc.find(Filters.near("gps", new Point(new Position(10, 10)), 20566d, 0d));

        //$nearSphere
        iterable = mc.find(Filters.nearSphere("gps", new Point(new Position(10, 10)), 20566d, 10d));

        iterable.forEach(new Block<Document>() {
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
    }
}

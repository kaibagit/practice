package com.kaiba.demo.mongodb3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * Created by luliru on 2017/5/11.
 */
public class MongoTemplateExample {

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:mongodb/applicationContext-mongo.xml"});
        MongoTemplate mongoTemplate = ctx.getBean(MongoTemplate.class);

        String collectionName = "users";
        String userId = "1L";
        User user = new User();
        user.setId(userId);
        user.setName("luliru");
        user.setAge(14);

        mongoTemplate.createCollection(collectionName);
        mongoTemplate.insert(user, collectionName);
        user = mongoTemplate.findOne(new Query(Criteria.where("id").is(userId)), User.class,collectionName);
        System.out.println(user.getName());
        List<User> userList = mongoTemplate.find(new Query(Criteria.where("age").lt(18)), User.class,collectionName);
        mongoTemplate.upsert(new Query(Criteria.where("id").is(userId)), new Update().set("name", "luliru2"),collectionName);
        mongoTemplate.remove(new Query(Criteria.where("id").is(userId)),User.class,collectionName);
    }
}

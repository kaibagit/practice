package com.kaiba.demo.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.esotericsoftware.kryo.serializers.MapSerializer;
import com.kaiba.demo.beans.Member;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by luliru on 2016/12/13.
 */
public class KryoTest {

    public static void main(String[] args) throws IOException {
        testSet();
    }

    public static void testList() throws IOException {
        Class clazz = Member.class;

        List<Member> list = new ArrayList<>();
        list.add(new Member(1L,"luliru",true));
        list.add(new Member(2L,"kaiba",false));

        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);

        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);

        kryo.register(clazz, new JavaSerializer());
        kryo.register(ArrayList.class, serializer);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, list);
        output.flush();
        output.close();

        byte[] bytes = baos.toByteArray();
        baos.flush();
        baos.close();

        Kryo kryo2 = new Kryo();
        kryo2.setReferences(false);
        kryo2.setRegistrationRequired(true);

        CollectionSerializer serializer2 = new CollectionSerializer();
        serializer2.setElementClass(clazz, new JavaSerializer());
        serializer2.setElementsCanBeNull(false);

        kryo2.register(clazz, new JavaSerializer());
        kryo2.register(ArrayList.class, serializer2);

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Input input = new Input(bais);
        list = kryo.readObject(input, ArrayList.class, serializer);
        for(Member m : list){
            System.out.println(m);
        }
    }

    public static void testObject() throws IOException {
        Member obj = new Member();
        obj.setName("luliru");
        obj.setId(1L);
        obj.setSex(true);

        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(obj.getClass(), new JavaSerializer());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeClassAndObject(output, obj);
        output.flush();
        output.close();

        byte[] bytes = baos.toByteArray();

        baos.flush();
        baos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Input input = new Input(bais);
        obj = (Member)kryo.readClassAndObject(input);

        System.out.println(obj);
    }

    public static void testMap() throws IOException {
        Class clazz = Member.class;

        Map<Long,Member> map = new HashMap<>();
        map.put(1L,new Member(1L,"luliru",true));
        map.put(2L,new Member(2L,"kaiba",false));

        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);

        MapSerializer serializer = new MapSerializer();
        serializer.setKeyClass(Long.class,new JavaSerializer());
        serializer.setKeysCanBeNull(false);
        serializer.setValueClass(clazz,new JavaSerializer());
        serializer.setValuesCanBeNull(true);

        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashMap.class, serializer);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, map);
        output.flush();
        output.close();

        byte[] bytes = baos.toByteArray();
        baos.flush();
        baos.close();

        Kryo kryo2 = new Kryo();
        kryo2.setReferences(false);
        kryo2.setRegistrationRequired(true);

        MapSerializer serializer2 = new MapSerializer();
        serializer2.setKeyClass(Long.class,new JavaSerializer());
        serializer2.setKeysCanBeNull(false);
        serializer2.setValueClass(clazz,new JavaSerializer());
        serializer2.setValuesCanBeNull(true);

        kryo2.register(clazz, new JavaSerializer());
        kryo2.register(ArrayList.class, serializer2);

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Input input = new Input(bais);
        map = kryo.readObject(input, HashMap.class, serializer);
        for(Map.Entry<Long,Member> entry : map.entrySet()){
            System.out.println("key=>"+entry.getKey()+" value=>"+entry.getValue());
        }
    }

    public static void testSet() throws IOException {
        Class clazz = Member.class;

        Set<Member> set = new HashSet<>();
        set.add(new Member(1L,"luliru",true));
        set.add(new Member(2L,"kaiba",false));

        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);

        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);

        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashSet.class, serializer);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, set);
        output.flush();
        output.close();

        byte[] bytes = baos.toByteArray();
        baos.flush();
        baos.close();

        Kryo kryo2 = new Kryo();
        kryo2.setReferences(false);
        kryo2.setRegistrationRequired(true);

        CollectionSerializer serializer2 = new CollectionSerializer();
        serializer2.setElementClass(clazz, new JavaSerializer());
        serializer2.setElementsCanBeNull(false);

        kryo2.register(clazz, new JavaSerializer());
        kryo2.register(ArrayList.class, serializer2);

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Input input = new Input(bais);
        set = kryo.readObject(input, HashSet.class, serializer);
        for(Member m : set){
            System.out.println(m);
        }
    }

}

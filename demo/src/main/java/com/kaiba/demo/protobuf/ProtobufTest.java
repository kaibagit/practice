package com.kaiba.demo.protobuf;

import com.example.tutorial.AddressBookProtos.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by luliru on 2016/12/13.
 */
public class ProtobufTest {

    public static void main(String[] args) throws IOException {
        Person john = Person.newBuilder()
            .setId(1234)
            .setName("John Doe")
            .setEmail("jdoe@example.com")
            .addPhone(
                Person.PhoneNumber.newBuilder()
                .setNumber("555-4321")
                .setType(Person.PhoneType.HOME)
            ).build();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        john.writeTo(baos);

        byte[] bytes = baos.toByteArray();
        baos.flush();
        baos.close();

        Person person = Person.parseFrom(new ByteArrayInputStream(bytes));
        System.out.println(person);
    }
}

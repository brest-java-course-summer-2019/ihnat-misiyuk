package com.epam.brest.summer.courses2019.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SerializationTest {

    private static  final String VALUE = "123456789";

    @Test
    void test() throws IOException, ClassNotFoundException {
        SerializableObject obj = create();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(out);
        stream.close();
        stream.writeObject(obj);

        byte[] bytes = out.toByteArray();
        System.out.println(new String(bytes));

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        Object result = new ObjectInputStream(in).readObject();

        assertTrue(result instanceof SerializableObject);
        obj = (SerializableObject) result;
        assertEquals(VALUE, obj.getA());
    }

    @Test
    void testXML() {
        SerializableObject obj = create();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(out);
        encoder.writeObject(obj);
        encoder.close();

        byte[] bytes = out.toByteArray();
        System.out.println(new String(bytes));

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        Object result = new XMLDecoder(in).readObject();

        assertTrue(result instanceof SerializableObject);
        obj = (SerializableObject) result;
        assertEquals(VALUE, obj.getA());
    }

    @Test
    void testJson() throws IOException {
        SerializableObject obj = new SerializableObject();
        ObjectMapper objectMapper = new ObjectMapper();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        objectMapper.writeValue(out, obj);

        byte[] bytes = out.toByteArray();
        System.out.println(new String(bytes));

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        obj = objectMapper.readValue(in, SerializableObject.class);

        assertEquals(VALUE, obj.getA());
    }

    private SerializableObject create() {
        SerializableObject object = new ExternalizableObject();
        object.setA(VALUE);
        return object;
    }

    public static class SerializableObject implements Serializable {

        private String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }

    public static class ExternalizableObject extends SerializableObject implements Externalizable {

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(String.format("obj=%s", getA()));
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            setA(((String) in.readObject()).split("=")[1]);
        }
    }
}

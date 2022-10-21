package com.telenet.telenet.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telenet.telenet.models.BaseEntity;
import com.telenet.telenet.models.area.Area;
import com.telenet.telenet.models.enums.roles.RoleEnum;
import com.telenet.telenet.models.user.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class JsonMapper {
    private static String pathTemplate = "src/main/resources/dataBase/";

    public static <T extends BaseEntity> Map<Integer, T> deserialize(Class<T> classType) throws IOException {
        String path = pathTemplate + classType.getSimpleName();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String json = reader.readLine();
        Map<Integer, T> res = new HashMap<>();
        ObjectMapper objMapper = new ObjectMapper();
        while (json != null) {
            T value = objMapper.readValue(json, classType);
            res.put(value.getId(), value);
            json = reader.readLine();
        }
        return res;
    }

    public static Map<Integer, User> deserialize(RoleEnum role) throws IOException {
        String path = pathTemplate + User.class.getSimpleName() + role.toString();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String json = reader.readLine();
        Map<Integer, User> res = new HashMap<>();
        ObjectMapper objMapper = new ObjectMapper();
        while (json != null) {
            User value = objMapper.readValue(json, User.class);
            res.put(value.getId(), value);
            json = reader.readLine();
        }
        return res;
    }

    public static <T extends BaseEntity> void serialize(Map<Integer, T> values, Class<T> className) throws IOException {
        try (Writer writer = new FileWriter(pathTemplate + className.getSimpleName(), true);) {
            for (T value :
                    values.values()) {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(value);
                writer.write(json + "\n");
            }
        }
    }
    public static void serialize(Map<Integer, User> values, RoleEnum role) throws IOException {
        try (Writer writer = new FileWriter(pathTemplate + User.class.getSimpleName() + role.toString(), true);) {
            for (User value :
                    values.values()) {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(value);
                writer.write(json + "\n");
            }
        }
    }

    public static void main(String[] args) {
        User a1 = new User();
        User a2 = new User();
        a2.setRole(RoleEnum.ADMIN);
        a1.setRole(RoleEnum.ADMIN);
        Map<Integer,User> useers = new HashMap<>();
        useers.put(1,a1);
        useers.put(2,a2);
        try {
            serialize(useers, useers.get(1).getRole());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

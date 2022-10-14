package com.telenet.telenet.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telenet.telenet.models.BaseEntity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonMapper {
    private static String pathTemplate = "src/main/resources/dataBase/";
    public static <T extends BaseEntity> Map<Integer, T> deserialize(Class<T> classType) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(pathTemplate + classType.getSimpleName()));
        String json = reader.readLine();
        Map <Integer, T> res = new HashMap<>();
        while(json != null){
            ObjectMapper objMapper = new ObjectMapper();
            T value = objMapper.readValue(json, classType);
            res.put(value.getId(), value);
            json = reader.readLine();
        }
        return res;
    }
}

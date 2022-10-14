package com.telenet.telenet.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonMapper {
    private static String pathTemplate = "src/main/resources/dataBase/";
    public static <T> Map<Integer, T> serialize(Class classType) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(pathTemplate + classType.getSimpleName()));
        String json = reader.readLine();
        Map <Integer, T> res = new HashMap<>();
        while(json != null){
            ObjectMapper objMapper = new ObjectMapper();
            res.put(objMapper.readValue(json, classType).,objMapper.readValue(json, classType))

            json = reader.readLine();
        }

    }
}

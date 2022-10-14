package com.telenet.telenet.utils;

import java.util.Map;

public class JsonMapper {
    private static String pathTemplate = "src/main/resources/dataBase/";
    public static String serialize(Class classType){
        String dataBasePath = pathTemplate + classType.getSimpleName();
        return dataBasePath;
    }
}

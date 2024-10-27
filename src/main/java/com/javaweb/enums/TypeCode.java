package com.javaweb.enums;


import java.util.*;

public enum TypeCode {
    TANG_TRET("Tầng trẹt"),
    NOI_THAT("Nội thất"),
    NGUYEN_CAN("Nguyên căn");

    private final String name;
    TypeCode(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public static Map<String, String> type(){
        Map<String, String> typeCodes = new TreeMap<>();
        for(TypeCode item : TypeCode.values()){
            typeCodes.put(item.getName(), item.name);
        }
        return typeCodes;
    }
}

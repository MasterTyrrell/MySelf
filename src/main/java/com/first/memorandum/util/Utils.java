package com.first.memorandum.util;

import java.util.UUID;

public class Utils {

    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }



}

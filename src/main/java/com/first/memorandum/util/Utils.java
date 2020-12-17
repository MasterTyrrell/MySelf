package com.first.memorandum.util;

import com.first.memorandum.dto.JsonContent;

import java.util.UUID;

import static com.first.memorandum.enumfolder.ResponseEnum.EEROR_PAHE_PARAMETER;
import static com.first.memorandum.enumfolder.ResponseEnum.NO_PAHE_PARAMETER;

public class Utils {

    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static JsonContent checkPageData(Integer pageIndex,Integer pageSize){
        if(pageIndex==null||pageSize==null){
            return new JsonContent(NO_PAHE_PARAMETER);
        }
        if(pageIndex<=0||pageSize<=0){
            return new JsonContent(EEROR_PAHE_PARAMETER);
        }
        return null;
    }


}

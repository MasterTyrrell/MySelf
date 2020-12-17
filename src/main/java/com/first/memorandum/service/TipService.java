package com.first.memorandum.service;

import com.first.memorandum.dto.JsonContent;
import com.first.memorandum.entity.Tip;

public interface TipService {

    JsonContent addTip(Tip tip,String userNo);

    JsonContent deleteTip(String tipNo,String userNo);

    JsonContent updateTipe(Tip tip,String userNo);

    JsonContent listTips(String userNo,Integer pageIndex,Integer pageSize);
}

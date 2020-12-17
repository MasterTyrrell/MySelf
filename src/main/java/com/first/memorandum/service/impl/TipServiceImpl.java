package com.first.memorandum.service.impl;

import com.first.memorandum.dto.JsonContent;
import com.first.memorandum.dto.PageDto;
import com.first.memorandum.entity.Tip;
import com.first.memorandum.enumfolder.ResponseEnum;
import com.first.memorandum.enumfolder.TipCycleEnum;
import com.first.memorandum.enumfolder.TipStatusEnum;
import com.first.memorandum.service.TipService;
import com.first.memorandum.util.Utils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.first.memorandum.enumfolder.ResponseEnum.*;
import static com.first.memorandum.util.Utils.checkPageData;

@Service
public class TipServiceImpl implements TipService {
    @Resource(name = "mySqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;
    @Override
    public JsonContent addTip(Tip tip, String userNo) {
        if(tip==null||tip.getTimestampstr()==null||tip.getTimestampstr().length()!=15){
            return new JsonContent(NO_TIP_PARAMETER);
        }
        if(!TipStatusEnum.checkCodeExist(tip.getStatus())){
            return new JsonContent(ERROR_STATUS);
        }
        if(!TipCycleEnum.checkCodeExist(tip.getTipCycle())){
            return new JsonContent(ERROR_CYCLE);
        }
        tip.setTipNo(Utils.getUuid());
        tip.setCreateTime(LocalDateTime.now());
        tip.setUpdateTime(LocalDateTime.now());
        tip.setDelFlag(false);
        tip.setUserNo(userNo);
        Integer num = sqlSessionTemplate.insert(Tip.class.getName()+".addTip",tip);
        if(num>0){
            return new JsonContent(SUCCESS);
        }
        return new JsonContent(FAILED);
    }

    @Override
    public JsonContent deleteTip(String tipNo, String userNo) {
        if(tipNo==null){
            return new JsonContent(NO_NOTE_NO);
        }
        Map<String,Object> param = new HashMap<>(2);
        param.put("tipNo",tipNo);
        param.put("userNo",userNo);
        Integer num = sqlSessionTemplate.delete(Tip.class.getName()+".deleteTip",param);
        if(num>0){
            return new JsonContent(SUCCESS);
        }
        return new JsonContent(FAILED);
    }

    @Override
    public JsonContent updateTipe(Tip tip, String userNo) {
        if(tip.getTopic()==null&&tip.getContent()==null&&(tip.getTimestampstr()==null||tip.getTimestampstr().length()!=15)&&tip.getStatus()==null&&tip.getTipCycle()==null){
            return new JsonContent(NO_NOTE_PARAMETER);
        }
        if(tip.getTipNo()==null){
            return new JsonContent(NO_NOTE_NO);
        }
        Map<String,Object> param = new HashMap<>(2);
        param.put("tip",tip);
        param.put("userNo",userNo);
        Integer num = sqlSessionTemplate.update(Tip.class.getName()+".updateTip",param);
        if(num>0){
            return new JsonContent(SUCCESS);
        }
        return new JsonContent(FAILED);
    }

    @Override
    public JsonContent listTips(String userNo, Integer pageIndex, Integer pageSize) {
        JsonContent checkResult = checkPageData(pageIndex,pageSize);
        if(checkResult!=null){
            return checkResult;
        }
        Integer pageStart = (pageIndex-1) * pageSize;
        Long count = sqlSessionTemplate.selectOne(Tip.class.getName()+".countTipsList",userNo);
        Map<String,Object> param = new HashMap<>(2);
        param.put("pageStart",pageStart);
        param.put("pageSize",pageSize);
        param.put("userNo",userNo);
        List<Tip> result = sqlSessionTemplate.selectList(Tip.class.getName()+".ListTipsByUserNo",param);
        JsonContent jsonContent = new JsonContent(ResponseEnum.SUCCESS);
        jsonContent.setResult(new PageDto<>(result,count,pageIndex,pageSize));
        return jsonContent;
    }
}

package com.first.memorandum.controller;

import com.first.memorandum.dto.JsonContent;
import com.first.memorandum.entity.Tip;
import com.first.memorandum.service.TipService;
import com.first.memorandum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/tip")
public class TipController {
    @Autowired
    private TipService tipService;

    @RequestMapping("/addTips")
    @ResponseBody
    public JsonContent addTip(Tip tip, @SessionAttribute("userNo")String userNo){
        return tipService.addTip(tip,userNo);
    }

    @RequestMapping("/deleteTip")
    @ResponseBody
    public JsonContent deleteTip(String tipNo,@SessionAttribute("userNo")String userNo){
        return tipService.deleteTip(tipNo,userNo);
    }

    @RequestMapping("/updateTip")
    @ResponseBody
    public JsonContent updateTip(Tip tip,@SessionAttribute("userNo")String userNo){
        return tipService.updateTipe(tip,userNo);
    }

    @RequestMapping("/listTips")
    @ResponseBody
    public JsonContent listTips(Integer pageIndex,Integer pageSize,@SessionAttribute("userNo")String userNo){
        return tipService.listTips(userNo,pageIndex,pageSize);
    }
}


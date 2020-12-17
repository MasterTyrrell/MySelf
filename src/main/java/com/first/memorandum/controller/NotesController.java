package com.first.memorandum.controller;


import com.first.memorandum.dto.JsonContent;
import com.first.memorandum.entity.Notes;
import com.first.memorandum.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import static com.first.memorandum.enumfolder.ResponseEnum.*;


@Controller
@RequestMapping("/notes")
public class NotesController {
    @Autowired
    private NotesService notesService;
    //新增
    @RequestMapping("/addNotes")
    @ResponseBody
    public JsonContent addNotes(Notes note, @SessionAttribute("userNo") String userNo){
        if(note==null){
            return new JsonContent(NO_NOTE_PARAMETER);
        }
        return notesService.addNotes(note,userNo);
    }
    //删除
    @RequestMapping("/deleteNotes")
    @ResponseBody
    public JsonContent deleteNotes(String noteNo,@SessionAttribute("userNo") String userNo){
        if(noteNo == null){
            return new JsonContent(NO_NOTE_NO);
        }
        return notesService.deleteNotes(noteNo,userNo);
    }

    //更新
    @RequestMapping("/updateNotes")
    @ResponseBody
    public JsonContent updateNotes(Notes note,@SessionAttribute("userNo") String userNo){
        if(note==null||note.getNoteNo()==null||(note.getTopic()==null&&note.getContent()==null)){
            return new JsonContent(NO_NOTE_PARAMETER);
        }
        return notesService.updateNotes(note,userNo);
    }


    //查询
    @RequestMapping("/listNotes")
    @ResponseBody
    public JsonContent listNotes(@SessionAttribute("userNo") String userNo,Integer pageIndex,Integer pageSize){
        return notesService.ListNotes(userNo,pageSize,pageIndex);
    }
}

package com.first.memorandum.service.impl;

import com.first.memorandum.dto.JsonContent;
import com.first.memorandum.dto.PageDto;
import com.first.memorandum.entity.Notes;
import com.first.memorandum.enumfolder.ResponseEnum;
import com.first.memorandum.mapper.NotesMapper;
import com.first.memorandum.service.NotesService;
import com.first.memorandum.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.first.memorandum.util.Utils.checkPageData;

@Service
public class NotesServiceImpl implements NotesService {

    @Autowired
    private NotesMapper notesMapper;

    @Override
    public JsonContent addNotes(Notes note, String userNo) {
        //新增随机
        note.setUserNo(userNo);
        //随记编号用作更新
        note.setNoteNo(Utils.getUuid());
        note.setCreateTime(LocalDateTime.now());
        note.setUpdateTime(LocalDateTime.now());
        note.setDelFlag(false);
        //随机初始版本号
        note.setVersion(0);
        Integer num = notesMapper.addNotes(note);
        if(num>0){
            JsonContent result = new JsonContent(ResponseEnum.SUCCESS);
            result.setResult(note);
            return result;
        }
        return new JsonContent(ResponseEnum.FAILED);
    }

    @Override
    public JsonContent deleteNotes(String noteNum, String userNo) {
        Integer num = notesMapper.deleteNotes(noteNum,userNo);
        if(num>0){
            return new JsonContent(ResponseEnum.SUCCESS);
        }
        return new JsonContent(ResponseEnum.FAILED);
    }

    @Override
    public JsonContent updateNotes(Notes note, String userNo) {
        Integer num = notesMapper.updateNotes(note,userNo);
        if(num>0){
            return new JsonContent(ResponseEnum.SUCCESS);
        }
        return new JsonContent(ResponseEnum.FAILED);
    }

    @Override
    public JsonContent ListNotes(String userNo, Integer pageSize, Integer pageIndex) {
        JsonContent checkResult = checkPageData(pageIndex,pageSize);
        if(checkResult!=null){
            return checkResult;
        }
        Long count = notesMapper.countNotesList(userNo);
        Integer pageStart = (pageIndex-1) * pageSize;
        List<Notes> result = notesMapper.ListNotesByUserNo(userNo,pageStart,pageSize);
        JsonContent jsonContent = new JsonContent(ResponseEnum.SUCCESS);
        jsonContent.setResult(new PageDto<>(result,count,pageIndex,pageSize));
        return jsonContent;
    }


}

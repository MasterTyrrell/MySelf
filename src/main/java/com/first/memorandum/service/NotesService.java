package com.first.memorandum.service;

import com.first.memorandum.dto.JsonContent;
import com.first.memorandum.entity.Notes;

public interface NotesService {

    JsonContent addNotes(Notes note,String userNo);

    JsonContent deleteNotes(String noteNum,String userNo);

    JsonContent updateNotes(Notes note,String userNo);

    JsonContent ListNotes(String userNo,Integer PageSize,Integer PageCount);

}

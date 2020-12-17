package com.first.memorandum.mapper;

import com.first.memorandum.entity.Notes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesMapper {

    Integer addNotes(Notes note);

    Integer deleteNotes(String noteNo,String userNo);

    Integer updateNotes(Notes notes,String userNo);

    List<Notes> ListNotesByUserNo(String userNo,Integer pageStart,Integer pageSize);

    Long countNotesList(String userNo);
}

package ru.gb.course1.notebook.domain;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * CRUD - Create Read Update Delete
 */

public interface NotesRepo {
    List<NoteEntity> getNotes();

    @Nullable
    Integer createNote(NoteEntity note);

    boolean deleteNote(Integer id);

    boolean updateNote(Integer id, NoteEntity note);
}



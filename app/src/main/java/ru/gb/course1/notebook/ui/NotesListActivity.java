package ru.gb.course1.notebook.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import ru.gb.course1.notebook.R;
import ru.gb.course1.notebook.domain.Constant;
import ru.gb.course1.notebook.domain.NoteEntity;
import ru.gb.course1.notebook.domain.NotesRepo;
import ru.gb.course1.notebook.impl.NotesRepoImpl;

public class NotesListActivity extends AppCompatActivity implements Constant {

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private final NotesRepo notesRepo = new NotesRepoImpl();
    private final NotesAdapter adapter = new NotesAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        initToolbar();
        initRecyclerView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.notes_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.new_note_menu) {
            openNewNote();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void openThisNote(NoteEntity item) {
        Intent openThisNote= new Intent(this, ThisNoteEditActivity.class);
        openThisNote.putExtra(RETURN_RESULT_THIS_NOTE_KEY,item);
       // startActivity(openThisNote);
       // setResult(RESULT_OK,openThisNote);
        startActivityForResult(openThisNote,REQUEST_CODE_RESULT_THIS_NOTE);


    }

    private void openNewNote() {
        Intent openNewNoteIntent = new Intent(this, NewNoteEditActivity.class);
        startActivityForResult(openNewNoteIntent, REQUEST_CODE_RESULT_NEW_NOTE);

    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this::onItemClick);
        adapter.setData(notesRepo.getNotes());
    }

    private void onItemClick(NoteEntity item) {
        openThisNote(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_RESULT_NEW_NOTE) {
            if (resultCode == Activity.RESULT_OK) {
                assert data != null;
                NoteEntity noteEntity = data.getParcelableExtra(RETURN_RESULT_NEW_NOTE_KEY);
                if (noteEntity != null) {
                    notesRepo.createNote(noteEntity);

                }

            }
        }
        if (requestCode == REQUEST_CODE_RESULT_THIS_NOTE) {
            if (resultCode == Activity.RESULT_OK) {
                assert data != null;
                NoteEntity noteEntity = data.getParcelableExtra(RETURN_RESULT_THIS_NOTE_KEY);
                if (noteEntity != null) {
                    notesRepo.updateNote(noteEntity.getId(), noteEntity);

                }

            }
        }
    }

}




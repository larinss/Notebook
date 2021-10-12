package ru.gb.course1.notebook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import ru.gb.course1.notebook.R;
import ru.gb.course1.notebook.domain.Constant;
import ru.gb.course1.notebook.domain.NoteEntity;

import android.content.Intent;


public class NewNoteEditActivity extends AppCompatActivity implements Constant {

    private EditText titleEditText;
    private EditText detailEditText;
    private Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note_edit);

        initView();
        saveButton.setOnClickListener(view -> onClickButtonSave());
    }


    private void initView() {
        titleEditText = findViewById(R.id.title_edit_text);
        detailEditText = findViewById(R.id.detail_edit_text);
        saveButton = findViewById(R.id.save_button);
    }


    private void onClickButtonSave() {

        String title = titleEditText.getText().toString();
        String detail = detailEditText.getText().toString();

        if (title != null && detail != null) {
            NoteEntity noteEntity = new NoteEntity(title, detail);

            Intent returnResultIntent = new Intent();
            returnResultIntent.putExtra(RETURN_RESULT_NEW_NOTE_KEY, noteEntity);
            setResult(Activity.RESULT_OK, returnResultIntent);
            finish();

        }
    }
}
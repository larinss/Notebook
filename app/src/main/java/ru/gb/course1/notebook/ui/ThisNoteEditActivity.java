package ru.gb.course1.notebook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import ru.gb.course1.notebook.R;
import ru.gb.course1.notebook.domain.Constant;
import ru.gb.course1.notebook.domain.NoteEntity;

public class ThisNoteEditActivity extends AppCompatActivity implements Constant {

    private EditText titleEditText;
    private EditText detailEditText;
    private Button saveButton;
    private NoteEntity noteEntity = new NoteEntity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_note_edit);

        initView();
        fillView();

        saveButton.setOnClickListener(view -> onClickButtonSave());
    }

    private void fillView() {
        Intent resultFromNotesListActivity = getIntent();
        noteEntity = resultFromNotesListActivity.getParcelableExtra(RETURN_RESULT_THIS_NOTE_KEY);
        if (noteEntity != null) {

            Integer id = noteEntity.getId();
            String title = noteEntity.getTitle();
            String detail = noteEntity.getDetail();

            titleEditText.setText(title);
            detailEditText.setText(detail);
        }
    }

    private void initView() {

        titleEditText = findViewById(R.id.title_edit_text);
        detailEditText = findViewById(R.id.detail_edit_text);
        saveButton = findViewById(R.id.save_button);


    }


    private void onClickButtonSave() {

        noteEntity.setTitle(titleEditText.getText().toString());
        noteEntity.setDetail(detailEditText.getText().toString());

        Intent resultForNoteEditListActivityIntent = new Intent();
        resultForNoteEditListActivityIntent.putExtra(RETURN_RESULT_THIS_NOTE_KEY, noteEntity);
        setResult(Activity.RESULT_OK, resultForNoteEditListActivityIntent);

        finish();

    }
}


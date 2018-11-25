package com.example.evilsay.ormlitedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.evilsay.ormlitedemo.Bean.NoteDataBase;
import com.example.evilsay.ormlitedemo.DAO.IDaoServiceImpl;
import com.example.evilsay.ormlitedemo.DAO.IdaoService;
import com.example.evilsay.ormlitedemo.Fragment.FragmentALLNote;

import java.sql.SQLException;

public class updateActivity extends AppCompatActivity {
    protected EditText editText_title,editText_content;
    protected ImageButton button;
    private int id;
    private IdaoService<NoteDataBase,Integer> idaoService = new IDaoServiceImpl<>(this,NoteDataBase.class);
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.framgnetadd);
        InitView();
        ReceiveData();
        Query();
    }
    public void ReceiveData(){
        if (getIntent() != null){
            id = getIntent().getIntExtra(FragmentALLNote.LIST_SUBSCRIPT,0);
            Log.e("Test","id"+id);
        }
    }
    public void InitView(){
        editText_content = findViewById(R.id.note_content);
        editText_title   = findViewById(R.id.note_title);
        button = findViewById(R.id.add_note_img);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update();
                editText_title.setText(" ");
                editText_content.setText(" ");
                startActivity(new Intent(updateActivity.this,MainActivity.class));
            }
        });
    }
    public void Update(){
        try {
            NoteDataBase base = idaoService.queryByid(id);
            base.setTitle(editText_title.getText().toString());
            base.setContent(editText_content.getText().toString());
            idaoService.update(base);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Query(){
        try {
            NoteDataBase base = idaoService.queryByid(id);
            editText_title.setText(base.getTitle());
            editText_content.setText(base.getContent());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

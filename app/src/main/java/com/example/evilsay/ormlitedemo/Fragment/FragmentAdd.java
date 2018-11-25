package com.example.evilsay.ormlitedemo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.evilsay.ormlitedemo.Bean.NoteDataBase;
import com.example.evilsay.ormlitedemo.DAO.IDaoServiceImpl;
import com.example.evilsay.ormlitedemo.DAO.IdaoService;
import com.example.evilsay.ormlitedemo.MainActivity;
import com.example.evilsay.ormlitedemo.R;

import java.sql.SQLException;

public class FragmentAdd extends Fragment {
    protected EditText editText_title,editText_content;
    protected ImageButton button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgnetadd,container,false);
        InitView(view);
        return view;
    }
    public void InitView(View view){
        editText_content = view.findViewById(R.id.note_content);
        editText_title   = view.findViewById(R.id.note_title);
        button = view.findViewById(R.id.add_note_img);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "添加成功", Toast.LENGTH_SHORT).show();
                insert();
                editText_content.setText("");
                editText_title.setText("");
            }
        });
    }
    public void insert(){
        final Intent intent = new Intent(getActivity(), MainActivity.class);
        IdaoService<NoteDataBase,Integer> idaoService = new IDaoServiceImpl<>(getContext(),NoteDataBase.class);
        NoteDataBase base  = new NoteDataBase();
        base.setTitle(editText_title.getText().toString());
        base.setContent(editText_content.getText().toString());
        try {
            idaoService.save(base);
            startActivity(intent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


package com.example.evilsay.ormlitedemo.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.evilsay.ormlitedemo.Bean.Author;
import com.example.evilsay.ormlitedemo.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentME extends Fragment {
    protected ExpandableListView expandableListView;
    private List<Author> authors = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgnetme,container,false);
        InitView(view);
        return view;
    }
    public void InitView(View view){

    }
}

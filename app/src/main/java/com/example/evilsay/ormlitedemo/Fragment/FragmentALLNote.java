package com.example.evilsay.ormlitedemo.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.evilsay.ormlitedemo.Adapter.ALLNoteAdapter;
import com.example.evilsay.ormlitedemo.Bean.NoteDataBase;
import com.example.evilsay.ormlitedemo.DAO.IDaoServiceImpl;
import com.example.evilsay.ormlitedemo.DAO.IdaoService;
import com.example.evilsay.ormlitedemo.MainActivity;
import com.example.evilsay.ormlitedemo.R;
import com.example.evilsay.ormlitedemo.updateActivity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FragmentALLNote extends Fragment {
    private static final String TAG = "FragmentALLNote";
    private List<NoteDataBase> baseList;
    protected ListView listView;
    private ALLNoteAdapter allNoteAdapter;
    private IdaoService<NoteDataBase, Integer> idaoService;
    public static final String LIST_SUBSCRIPT = "subscript";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgnet_allnote, container, false);
        InitAll(view);
        InitListener();
        return view;
    }
    public List<NoteDataBase> Query(Context context){
        idaoService = new IDaoServiceImpl<>(context, NoteDataBase.class);
        try {
            return idaoService.queryAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void InitAll(View view){
        baseList = new ArrayList<>();
        baseList.clear();
        baseList.addAll(Query(getContext()));
        listView = view.findViewById(R.id.AllNote_list);
        allNoteAdapter = new ALLNoteAdapter(baseList,getContext());
        allNoteAdapter.notifyDataSetChanged();
        allNoteAdapter.CheckChanges(baseList);
        listView.setAdapter(allNoteAdapter);
    }
    public void InitListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NoteDataBase base = baseList.get(i);
                idaoService = new IDaoServiceImpl<>(getContext(),NoteDataBase.class);
                TransferData(base.getId());
                Log.e(TAG, "onItemClick: " + base.getId());
            }
        });
    }
    public void TransferData(Integer i){
        final Intent intent = new Intent(getActivity(),updateActivity.class);
        intent.putExtra(LIST_SUBSCRIPT,i);
        startActivity(intent);
    }
}

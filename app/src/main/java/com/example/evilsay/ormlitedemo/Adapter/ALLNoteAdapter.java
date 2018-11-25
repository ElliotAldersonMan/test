package com.example.evilsay.ormlitedemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.evilsay.ormlitedemo.Bean.NoteDataBase;
import com.example.evilsay.ormlitedemo.R;

import java.util.List;

public class ALLNoteAdapter extends BaseAdapter {
    private List<NoteDataBase> baseList;
    private LayoutInflater inflater;
    private Context context;

    public ALLNoteAdapter(List<NoteDataBase> noteDataBases,Context context) {
        this.baseList = noteDataBases;
        this.inflater = LayoutInflater.from(context);
    }
    public void CheckChanges(List<NoteDataBase> noteDataBases){
        this.baseList = noteDataBases;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return baseList.size();
    }

    @Override
    public Object getItem(int i) {
        return baseList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = inflater.inflate(R.layout.layout_note_content,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.title = view.findViewById(R.id.note_title);
            viewHolder.content= view.findViewById(R.id.note_content);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(baseList.get(i).getTitle());
        viewHolder.content.setText(baseList.get(i).getContent());
        return view;
    }
    class ViewHolder{
        TextView title,content;
    }
}

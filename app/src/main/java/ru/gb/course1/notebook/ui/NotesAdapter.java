package ru.gb.course1.notebook.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.gb.course1.notebook.R;
import ru.gb.course1.notebook.domain.NoteEntity;

public class NotesAdapter extends RecyclerView.Adapter<NoteVh> {
    private List<NoteEntity> data = new ArrayList<>();
    OnItemClickListener clickListener = null;

    public void setData(List<NoteEntity> data) {
        this.data = data;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public NoteVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteVh holder, int position) {
        NoteEntity note = getItem(position);
        holder.itemView.setOnClickListener(view -> clickListener.onItemClick(note));
        holder.titleTextView.setText(note.getTitle());
        holder.detailTextView.setText(note.getDetail());
    }

    private NoteEntity getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        else return 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        clickListener = listener;
    }

    interface OnItemClickListener {
        void onItemClick(NoteEntity item);
    }


}

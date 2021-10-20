package com.example.krant.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krant.Model.DataNoteModel;
import com.example.krant.Model.NoteModel;
import com.example.krant.R;

import org.w3c.dom.Text;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<DataNoteModel> list_note;
    private Callback callback;

    public interface Callback{
        void onClick(int position);
    }

    public NoteAdapter(List<DataNoteModel> note_list, Callback callback) {
        this.list_note = note_list;
        this.callback = callback;
    }

    @NonNull
    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.note_list, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder holder, int position) {
        holder.title.setText(list_note.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return (list_note != null) ? list_note.size() : 0;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private CardView cardView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txt_note_title);
            cardView = itemView.findViewById(R.id.id_cardview_note);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(getLayoutPosition());
                }
            });
        }
    }
}

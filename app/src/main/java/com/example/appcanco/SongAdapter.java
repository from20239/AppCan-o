package com.example.appcanco;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private List<Song> songList;
    private Context context;

    public SongAdapter(List<Song> songList, Context context) {
        this.songList = songList;
        this.context = context;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.idTextView.setText(song.getId());
        holder.singerTextView.setText(song.getSinger());
        holder.titleTextView.setText(song.getTitle());

        // 点击修改按钮，跳转到编辑页面
        holder.modifyButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, cada_canco.class);
            intent.putExtra("songId", song.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView, singerTextView, titleTextView;
        Button modifyButton;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.idTextView);
            singerTextView = itemView.findViewById(R.id.singerTextView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            modifyButton = itemView.findViewById(R.id.modifyButton);
        }
    }
}
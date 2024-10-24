package com.example.appcanco;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class cada_canco extends AppCompatActivity {

    private EditText idEditText, singerEditText, titleEditText;
    private Button modifyButton, deleteButton;
    private String songId; // 用于保存当前编辑的歌曲ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cada_canco);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        idEditText = findViewById(R.id.idEditText);
        singerEditText = findViewById(R.id.singerEditText);
        titleEditText = findViewById(R.id.titleEditText);
        modifyButton = findViewById(R.id.modifyButton);
        deleteButton = findViewById(R.id.deleteButton);

        // 接收传递过来的歌曲ID，并获取歌曲详细信息
        Intent intent = getIntent();
        songId = intent.getStringExtra("songId");

        // 修改歌曲信息
        modifyButton.setOnClickListener(v -> {
            Song song = new Song(idEditText.getText().toString(), singerEditText.getText().toString(), titleEditText.getText().toString());
            modifySong(song);
        });

        // 删除歌曲
        deleteButton.setOnClickListener(v -> deleteSong(songId));
    }

    private void modifySong(Song song) {
        SongService service = RetrofitClient.getInstance().create(SongService.class);
        service.modifySong(songId, song).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // 修改成功后，返回主界面并刷新列表
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // 处理修改失败
            }
        });
    }

    private void deleteSong(String songId) {
        SongService service = RetrofitClient.getInstance().create(SongService.class);
        service.deleteSong(songId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // 删除成功后，返回主界面并刷新列表
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // 处理删除失败
            }
        });
    }
}
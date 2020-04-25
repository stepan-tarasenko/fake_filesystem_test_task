package com.example.fakefilesystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private JSONObject jsonObject;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

package com.example.fakefilesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.fakefilesystem.fragments.ListFragment.CONTENT_EXTRA;
import static com.example.fakefilesystem.fragments.ListFragment.NAME_EXTRA;

public class FileViewerActivity extends AppCompatActivity {

    public static final String TAG = "FileViewerActivity";
    private TextView fileName;
    private TextView fileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_viewer);

        findAndSetupViews();
        displayFileContent(getIntent());
    }

    private void displayFileContent(Intent data) {
        if(isInputDataValid(data)){
            fileName.setText(data.getStringExtra(NAME_EXTRA));
            fileContent.setText(data.getStringExtra(CONTENT_EXTRA));
        } else {
            throw new IllegalArgumentException("Invalid data " + TAG);
        }
    }

    private boolean isInputDataValid(Intent data) {
        return data.getStringExtra(NAME_EXTRA) != null
                && data.getStringExtra(CONTENT_EXTRA) != null;
    }

    private void findAndSetupViews() {
        fileContent = findViewById(R.id.file_content);
        fileName = findViewById(R.id.file_name);
    }
}

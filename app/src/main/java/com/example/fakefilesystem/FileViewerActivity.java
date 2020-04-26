package com.example.fakefilesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.fakefilesystem.utils.StringConstants.JSON_CONTENT_KEY;
import static com.example.fakefilesystem.utils.StringConstants.JSON_NAME_KEY;

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
            fileName.setText(data.getStringExtra(JSON_NAME_KEY));
            fileContent.setText(data.getStringExtra(JSON_CONTENT_KEY));
        } else {
            throw new IllegalArgumentException("Invalid data " + TAG);
        }
    }

    private boolean isInputDataValid(Intent data) {
        return data.getStringExtra(JSON_NAME_KEY) != null
                && data.getStringExtra(JSON_CONTENT_KEY) != null;
    }

    private void findAndSetupViews() {
        fileContent = findViewById(R.id.file_content);
        fileName = findViewById(R.id.file_name);
    }
}

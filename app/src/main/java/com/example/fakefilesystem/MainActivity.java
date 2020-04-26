package com.example.fakefilesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FrameLayout fragmentContainer;
    private JSONObject inputJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findAndSetupViews();
        try {
            initializeFakeFilesystemJSON();
        } catch (JSONException e) {
            throw new IllegalArgumentException("Invalid JSON file " + TAG);
        }
    }

    private void initializeFakeFilesystemJSON() throws JSONException {
        inputJSON = new JSONObject(getResources().openRawResource(R.raw.sample).toString());
    }

    private void findAndSetupViews() {
        fragmentContainer = findViewById(R.id.fragment_container);
    }


}

package com.example.fakefilesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.fakefilesystem.fragments.ListFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FrameLayout fragmentContainer;
    private JSONObject inputJSON;

    public JSONObject getInputJSON() {
        return inputJSON;
    }

    private String createValidJson(String inputString){
        StringBuilder validJson = new StringBuilder();
        String[] lineByLine =  inputString.split(",");
        for(int i =0; i< lineByLine.length; i++){
            String[] lineSplit = lineByLine[i].split(":");
            validJson.append('"').append(lineSplit[0].trim()).append('"').append(":").append('"').append(lineSplit[1].trim()).append('"').append(i==lineByLine.length-1?"}":",");
        }
        String finishedJSON = validJson.toString();
        System.out.println(finishedJSON);
        return finishedJSON;
    }

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

        try {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, ListFragment.newInstance(getInputJSON().getJSONArray("items").toString()))
                    .commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initializeFakeFilesystemJSON() throws JSONException {
        inputJSON = new JSONObject(readTextFile(getResources().openRawResource(R.raw.sample)));
    }

    private void findAndSetupViews() {
        fragmentContainer = findViewById(R.id.fragment_container);
    }

    private String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
        return outputStream.toString();
    }
}

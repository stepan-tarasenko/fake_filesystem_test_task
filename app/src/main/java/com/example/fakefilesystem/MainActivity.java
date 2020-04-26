package com.example.fakefilesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.fakefilesystem.fragments.ListFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.example.fakefilesystem.utils.StringConstants.JSON_ITEMS_KEY;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private JSONObject inputJSON;

    public JSONObject getInputJSON() {
        return inputJSON;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            initializeFakeFilesystemJSON();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, ListFragment.newInstance(
                            getInputJSON().getJSONArray(JSON_ITEMS_KEY).toString())
                    )
                    .commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initializeFakeFilesystemJSON() throws JSONException {
        inputJSON = new JSONObject(readTextFile(getResources().openRawResource(R.raw.sample)));
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

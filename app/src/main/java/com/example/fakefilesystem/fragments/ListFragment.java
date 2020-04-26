package com.example.fakefilesystem.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fakefilesystem.FileViewerActivity;
import com.example.fakefilesystem.MainActivity;
import com.example.fakefilesystem.R;
import com.example.fakefilesystem.adapters.DisplayModelAdapter;
import com.example.fakefilesystem.models.DisplayModel;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListFragment extends Fragment implements DisplayModelAdapter.OnNoteListener {

    private static final String ARG_PARAM = "JSON_DATA";
    public static final String NAME_EXTRA = "name";
    public static final String CONTENT_EXTRA = "content";

    private String stringJsonData;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private DisplayModelAdapter adapter;
    private List<DisplayModel> data;

    public ListFragment() {
    }

    public static ListFragment newInstance(String jsonData) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        Log.e("HELLO OOOO", jsonData);
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
        } catch (JSONException e) {
            Log.e("ERROR", "ERROR");
        }
        args.putString(ARG_PARAM, jsonData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            stringJsonData = getArguments().getString(ARG_PARAM);
        }
        JSONArray jsonData = null;
        try {
            //jsonData = ((MainActivity) getActivity()).getInputJSON().getJSONArray("items");

            //jsonData = new JSONObject(stringJsonData).getJSONArray("items");
            jsonData = new JSONArray(stringJsonData);

            data = Arrays.asList( new Gson().fromJson(jsonData.toString(), DisplayModel[].class));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new DisplayModelAdapter(data, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onNoteClick(int position) {
        DisplayModel model = data.get(position);
        if (model.getType().equals("FOLDER")) {
            Log.e("HELLO 111", stringJsonData);
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, ListFragment.newInstance(((ArrayList<LinkedTreeMap>)data.get(position).getItems()).toString()))
                    .commit();

        } else {
            startActivityWithData(model);
        }
    }

    private void startActivityWithData(DisplayModel model) {
        Intent data = new Intent(getActivity(), FileViewerActivity.class);
        data.putExtra(NAME_EXTRA, model.getName());
        data.putExtra(CONTENT_EXTRA, model.getContent());
        startActivity(data);
    }
}

package com.example.fakefilesystem.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fakefilesystem.FileViewerActivity;
import com.example.fakefilesystem.R;
import com.example.fakefilesystem.adapters.DisplayModelAdapter;
import com.example.fakefilesystem.models.DisplayModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.Arrays;
import java.util.List;

import static com.example.fakefilesystem.utils.StringConstants.JSON_CONTENT_KEY;
import static com.example.fakefilesystem.utils.StringConstants.JSON_NAME_KEY;
import static com.example.fakefilesystem.utils.StringConstants.TYPE_FOLDER;

public class ListFragment extends Fragment implements DisplayModelAdapter.OnNoteListener {

    private static final String ARG_PARAM = "JSON_DATA";
    private static final String FRAGMENT_BACK_STACK = "BS";

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
        if (model.getType().equals(TYPE_FOLDER)) {
            if (isItemsArrayEmpty(model)){
                Toast.makeText(getActivity(), getString(R.string.folder_empty), Toast.LENGTH_SHORT).show();
            } else {
                addFragmentToBackStack(position);
            }
        } else {
            startActivityWithData(model);
        }
    }

    private void addFragmentToBackStack(int position) {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .replace(R.id.fragment_container, ListFragment.newInstance(new Gson().toJson(data.get(position).getItems())))
                .addToBackStack(FRAGMENT_BACK_STACK)
                .commit();
    }

    private boolean isItemsArrayEmpty(DisplayModel model) {
        return model.getItems().size() == 0;
    }

    private void startActivityWithData(DisplayModel model) {
        Intent data = new Intent(getActivity(), FileViewerActivity.class);
        data.putExtra(JSON_NAME_KEY, model.getName());
        data.putExtra(JSON_CONTENT_KEY, model.getContent());
        startActivity(data);
    }
}

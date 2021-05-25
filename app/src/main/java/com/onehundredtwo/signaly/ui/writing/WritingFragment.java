package com.onehundredtwo.signaly.ui.writing;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.onehundredtwo.signaly.ConsonantsListActivity;
import com.onehundredtwo.signaly.HanjaListActivity;
import com.onehundredtwo.signaly.HistoryActivity;
import com.onehundredtwo.signaly.R;
import com.onehundredtwo.signaly.SyllablesListActivity;
import com.onehundredtwo.signaly.VowelsListActivity;

public class WritingFragment extends Fragment {

    private ImageView consonantsImageView;
    private ImageView vowelsImageView;
    private ImageView syllablesImageView;
    private ImageView historyImageView;
    private ImageView hanjaImageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_writing, container, false);

        consonantsImageView = root.findViewById(R.id.consonantsImageView);
        consonantsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ConsonantsListActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                requireActivity().finish();
            }
        });

        vowelsImageView = root.findViewById(R.id.vowelsImageView);
        vowelsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VowelsListActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                requireActivity().finish();
            }
        });

        syllablesImageView = root.findViewById(R.id.syllablesImageView);
        syllablesImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SyllablesListActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                requireActivity().finish();
            }
        });

        historyImageView = root.findViewById(R.id.historyImageView);
        historyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HistoryActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                requireActivity().finish();
            }
        });

        hanjaImageView = root.findViewById(R.id.hanjaImageView);
        hanjaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HanjaListActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                requireActivity().finish();
            }
        });



        return root;
    }
}
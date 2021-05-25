package com.onehundredtwo.signaly.ui.pronunciation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.onehundredtwo.signaly.R;
import com.onehundredtwo.signaly.WordsListActivity;

public class PronunciationFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pronunciation, container, false);

        ImageView greetingsImageView = root.findViewById(R.id.greetingsImageView);
        greetingsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WordsListActivity.class);
                intent.putExtra("wordsPosition", 0);
                intent.putExtra("wordsTitle", getResources().getString(R.string.greetings));
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity())
                        .toBundle());
                requireActivity().finish();
            }
        });

        ImageView countriesImageView = root.findViewById(R.id.countriesImageView);
        countriesImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WordsListActivity.class);
                intent.putExtra("wordsPosition", 1);
                intent.putExtra("wordsTitle", getResources().getString(R.string.countries));
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity())
                        .toBundle());
                requireActivity().finish();
            }
        });

        ImageView numbersImageView = root.findViewById(R.id.numbersImageView);
        numbersImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WordsListActivity.class);
                intent.putExtra("wordsPosition", 2);
                intent.putExtra("wordsTitle", getResources().getString(R.string.numbers));
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity())
                        .toBundle());
                requireActivity().finish();
            }
        });

        ImageView familyImageView = root.findViewById(R.id.familyImageView);
        familyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WordsListActivity.class);
                intent.putExtra("wordsPosition", 3);
                intent.putExtra("wordsTitle", getResources().getString(R.string.family));
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity())
                        .toBundle());
                requireActivity().finish();
            }
        });

        ImageView colorsImageView = root.findViewById(R.id.colorsImageView);
        colorsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WordsListActivity.class);
                intent.putExtra("wordsPosition", 4);
                intent.putExtra("wordsTitle", getResources().getString(R.string.colors));
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity())
                        .toBundle());
                requireActivity().finish();
            }
        });

        ImageView animalsImageView = root.findViewById(R.id.animalsImageView);
        animalsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WordsListActivity.class);
                intent.putExtra("wordsPosition", 5);
                intent.putExtra("wordsTitle", getResources().getString(R.string.animals));
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity())
                        .toBundle());
                requireActivity().finish();
            }
        });

        ImageView foodImageView = root.findViewById(R.id.foodImageView);
        foodImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WordsListActivity.class);
                intent.putExtra("wordsPosition", 6);
                intent.putExtra("wordsTitle", getResources().getString(R.string.food));
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity())
                        .toBundle());
                requireActivity().finish();
            }
        });



        return root;
    }
}
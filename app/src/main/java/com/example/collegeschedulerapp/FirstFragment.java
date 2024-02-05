package com.example.collegeschedulerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.collegeschedulerapp.databinding.FragmentFirstBinding;
import com.example.collegeschedulerapp.databinding.FragmentSecondBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private FragmentSecondBinding binding2;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        //binding = FragmentFirstBinding.inflate(inflater, container, false);
        binding2 = FragmentSecondBinding.inflate(inflater, container, false);
        //return binding.getRoot();
        return binding2.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_CourseDetailsFragment);
            }
        });

        binding2.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_TodoListFragment);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
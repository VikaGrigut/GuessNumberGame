package com.example.guessnumberfragments2.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.guessnumberfragments2.R;
import com.example.guessnumberfragments2.databinding.DevelopersFragmentBinding;
import com.example.guessnumberfragments2.R;
import com.example.guessnumberfragments2.databinding.DevelopersFragmentBinding;

public class DevelopersFragment extends Fragment {
    public DevelopersFragment(){
        super(R.layout.developers_fragment);
    }
    private DevelopersFragmentBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DevelopersFragmentBinding.bind(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
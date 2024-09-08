package com.example.guessnumberfragments2.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.guessnumberfragments2.R;
import com.example.guessnumberfragments2.databinding.ChangePlayerFragmentBinding;

public class ChangePlayerFragment extends Fragment {
    public ChangePlayerFragment(){
        super(R.layout.change_player_fragment);
    }
    private ChangePlayerFragmentBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = ChangePlayerFragmentBinding.bind(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}

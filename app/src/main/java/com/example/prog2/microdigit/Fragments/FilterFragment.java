package com.example.prog2.microdigit.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.example.prog2.microdigit.R;

public class FilterFragment extends Fragment {

    private View rootView;
    private Switch switch1,switch2,switch3,switch4;


    public FilterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_filter, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        switch1 = rootView.findViewById(R.id.switchCarretera);
        switch2 = rootView.findViewById(R.id.switchCarretera2);
        switch3 = rootView.findViewById(R.id.switchCarretera3);
        switch4 = rootView.findViewById(R.id.switchCarretera4);

        switch2.setChecked(true);
        switch3.setChecked(true);

    };

}
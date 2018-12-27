package com.example.prog2.microdigit.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.prog2.microdigit.R;
import com.marcoscg.materialtoast.MaterialToast;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {
// instancio botones
    Button btnAdd,btnCheck;
//instancio la view para poder usarla abajo
    private View rootView;

    public AddFragment() {
        // Required empty public constructor
    }

// aqui creo la view (inflandola )necesaria para poder trabajar con ella y la devuelvo para que se pueda usar en OnviewCreated.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_add, container, false);
        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAdd = (Button) rootView.findViewById(R.id.btnAdd);
        btnCheck = (Button) rootView.findViewById(R.id.btnCheck);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(rootView.getContext(), "Gracias! Propuesta de ruta añadida", Toast.LENGTH_SHORT).show();
                MaterialToast.makeText(rootView.getContext(), "Gracias! Propuesta de ruta añadida", Toast.LENGTH_SHORT)
                        .setBackgroundColor(Color.GREEN)
                        .show();
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialToast.makeText(rootView.getContext(), "Valida rutas propuestas por la comunidad BLIKE", Toast.LENGTH_SHORT)
                        .setBackgroundColor(Color.GREEN)
                        .show();
            }
        });


    }
}

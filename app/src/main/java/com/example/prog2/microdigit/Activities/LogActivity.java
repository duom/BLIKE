package com.example.prog2.microdigit.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prog2.microdigit.R;

public class LogActivity extends AppCompatActivity {

    Button btnSign;
    Button btnLog;
    EditText etNombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        btnSign = findViewById(R.id.btnSign);
        btnLog = findViewById(R.id.btnLog);
        etNombre = findViewById(R.id.etNombre);

        final String nombreTexto = etNombre.getText().toString();


        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogActivity.this,MainActivity.class);
                intent.putExtra("nombre",nombreTexto);
                startActivity(intent);
            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}

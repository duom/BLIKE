package com.example.prog2.microdigit.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prog2.microdigit.R;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LogActivity extends AppCompatActivity {

    private String[] userName;

    Button btnSign;
    Button btnLog;

    EditText et1Sign;
    EditText et2Sign;

    EditText et1Log;
    EditText et2Log;
    //recojo usuario (array xq hago split despues de @)
    String[] usuarioSign;
    String[] usuarioLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        //permite almacenar datos pero peta java.lang.IllegalStateException:`Parse#enableLocalDatastore(Context)` must be invoked before `Parse#initialize(Context)`

        //Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .enableLocalDataStore()
                .build()
        );

        ParseInstallation.getCurrentInstallation().saveInBackground();


        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(LogActivity.this,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.xml.zoom_back_in, R.xml.zoom_back_out);

            Toast.makeText(this, "Logeado como "+ParseUser.getCurrentUser().getUsername(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
        }

        
        btnLog = findViewById(R.id.btnLog);
        btnSign = findViewById(R.id.btnSign);

        et1Sign = findViewById(R.id.et1Sign);
        et2Sign = findViewById(R.id.et2Sign);

        et1Log = findViewById(R.id.et1Log);
        et2Log = findViewById(R.id.et2Log);


        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }

    public void goToMain(){
        Intent intent = new Intent(LogActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void signUp(){

        ParseUser user = new ParseUser();
// Set the user's username and password, which can be obtained by a forms
        user.setUsername(et1Sign.getText().toString());
        user.setPassword(et2Sign.getText().toString());
        userName = ParseUser.getCurrentUser().getUsername().split("@");
        final String nombre = userName[0].toString().toUpperCase();
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(LogActivity.this, "Registrado con exito "+nombre.toUpperCase(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LogActivity.this,MainActivity.class);
                    //envio nombre del Sign
                    intent.putExtra("nombreSign", et1Sign.getText().toString());
                    startActivity(intent);
                    overridePendingTransition(R.xml.zoom_back_in, R.xml.zoom_back_out);

                } else {
                    ParseUser.logOut();
                    Toast.makeText(LogActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void logIn(){

        ParseUser.logInInBackground(et1Log.getText().toString(), et2Log.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    userName = ParseUser.getCurrentUser().getUsername().split("@");
                    final String nombre = userName[0].toString().toUpperCase();

                    Toast.makeText(LogActivity.this, "Bienvenido "+nombre, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LogActivity.this,MainActivity.class);
                    //envio nombre del login
                    intent.putExtra("nombreLogin", et1Log.getText().toString());
                    startActivity(intent);
                    overridePendingTransition(R.xml.zoom_back_in, R.xml.zoom_back_out);

                } else {
                    ParseUser.logOut();
                    Toast.makeText(LogActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}

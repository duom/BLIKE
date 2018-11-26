package com.example.prog2.microdigit.Activities;

import android.content.Intent;
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

    Button btnSign;
    Button btnLog;

    EditText et1Sign;
    EditText et2Sign;

    EditText et1Log;
    EditText et2Log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        ///////////**********AUTOLOGIN DONDE VA?************////////

//        ParseUser currentUser = ParseUser.getCurrentUser();
//        if (currentUser != null) {
//            // do stuff with the user
//        } else {
//            // show the signup or login screen
//        }


        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );

        ParseInstallation.getCurrentInstallation().saveInBackground();


        btnLog = findViewById(R.id.btnLog);
        btnSign = findViewById(R.id.btnSign);

        et1Sign = findViewById(R.id.et1Sign);
        et2Sign = findViewById(R.id.et2Sign);

        et1Log = findViewById(R.id.et1Log);
        et2Log = findViewById(R.id.et2Log);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(et1Log.getText().toString(), et2Log.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser != null) {
                            Toast.makeText(LogActivity.this, "Bienvenido de nuevo "+et1Log.getText().toString(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LogActivity.this,MainActivity.class);
                            startActivity(intent);
                        } else {
                            ParseUser.logOut();
                            Toast.makeText(LogActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
// Set the user's username and password, which can be obtained by a forms
                user.setUsername(et1Sign.getText().toString());
                user.setPassword(et2Sign.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(LogActivity.this, "Registrado con exito", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LogActivity.this,MainActivity.class);
                            startActivity(intent);

                        } else {
                            ParseUser.logOut();
                            Toast.makeText(LogActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

    }
}

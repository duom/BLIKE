package com.example.prog2.microdigit.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.prog2.microdigit.Fragments.AlertsFragment;
import com.example.prog2.microdigit.Fragments.EmailFragment;
import com.example.prog2.microdigit.Fragments.InfoFragment;
import com.example.prog2.microdigit.Fragments.MapFragment;
import com.example.prog2.microdigit.R;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        //recogemos aqui el drawer y el navigation

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView=(NavigationView)findViewById(R.id.navview);




        //seleccionar elementos de un navigationView

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                boolean fragmentTransaction= false;
                Fragment fragment = null;

                switch (menuItem.getItemId()){

                    case R.id.menu_mail:
                        fragment=new EmailFragment();
                        fragmentTransaction=true;
                        break;

                    case R.id.menu_alert:
                        fragment=new AlertsFragment();
                        fragmentTransaction=true;
                        break;

                    case R.id.menu_info:
                        fragment=new InfoFragment();
                        fragmentTransaction=true;
                        break;

                    case R.id.menu_map:
                        fragment=new MapFragment();
                        fragmentTransaction=true;
                        break;

                    case R.id.menu_mapActivity:

                        Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.menu_enduro:
                        Toast.makeText(MainActivity.this, "Lo mismo hermano", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_downhill:
                        Toast.makeText(MainActivity.this, "En desarrollo tt", Toast.LENGTH_SHORT).show();
                        break;

                    }

//aqui usamos el metodo creado abajo para cambiar de fragment (changeFRagment)

                    if (fragmentTransaction){

                       changeFragment(fragment, menuItem);
                        drawerLayout.closeDrawers();

                    }

                return true;
            }
        });

    }

    private void setToolbar(){

        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    //como cambiar de fragment

    private void changeFragment(Fragment fragment, MenuItem menuItem){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame,fragment)
                .commit();
        menuItem.setChecked(true);
        getSupportActionBar().setTitle(menuItem.getTitle());

    }

    //boton burguer y ya

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:

                //ABRIR MENU LATERAL
                drawerLayout.openDrawer(Gravity.START);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}

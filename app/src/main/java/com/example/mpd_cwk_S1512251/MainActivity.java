// Update the package name to include your Student Identifier
package com.example.mpd_cwk_S1512251;
// Name                 Gavin Walter Jaap
// Student ID           S1512251
// Programme of Study   Computing
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set up the raw links to the graphical components

        // Navbar code
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(0);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        //  Intent intenthome = new Intent(MainActivity.this, MainActivity.class);
                        // startActivity(intenthome);
                        break;
                    case R.id.nav_list:
                        Intent intentlist = new Intent(MainActivity.this, ListActivity.class);
                        startActivity(intentlist);
                        break;
                    case R.id.nav_maps:
                        Intent intentmap = new Intent(MainActivity.this, MapsActivity.class);
                        startActivity(intentmap);
                        break;

                }

                return false;
            }
        });

    }


}
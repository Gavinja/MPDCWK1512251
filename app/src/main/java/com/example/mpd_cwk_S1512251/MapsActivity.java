package com.example.mpd_cwk_S1512251;
// Name                 Gavin Walter Jaap
// Student ID           S1512251
// Programme of Study   Computing

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mpd_cwk_S1512251.DataObject.Article;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Article article;
    private String urlAddress="http://quakes.bgs.ac.uk/feeds/MhSeismology.xml";

//    public void getArticle( Article article) {
//        return article;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Intent intenthome = new Intent(MapsActivity.this, MainActivity.class);
                        startActivity(intenthome);
                        break;
                    case R.id.nav_list:
                        Intent intentlist = new Intent(MapsActivity.this, ListActivity.class);
                        startActivity(intentlist);
                        break;
                    case R.id.nav_maps:
                      //  Intent intentmap = new Intent(MapsActivity.this, MapsActivity.class);
                      //  startActivity(intentmap);
                        break;

                }

                return false;
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng glasgow = new LatLng(55.8642, -4.2518);
        mMap.addMarker(new MarkerOptions().position(glasgow).title("Glasgow"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(glasgow));

        String b = "xd";
        if (b == null) {
            LatLng coordinates = new LatLng(MapsActivity.this.article.getLatitude(), MapsActivity.this.article.getLongitude());
            mMap.addMarker(new MarkerOptions().position(coordinates));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 12));
        }
        }




}

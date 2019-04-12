package com.example.mpd_cwk_S1512251;

// Name                 Gavin Walter Jaap
// Student ID           S1512251
// Programme of Study   Computing


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mpd_cwk_S1512251.DataObject.Article;
import com.example.mpd_cwk_S1512251.RSS.Downloader;
import com.example.mpd_cwk_S1512251.UI.FilterAdapter;
import com.example.mpd_cwk_S1512251.UI.MyAdapter;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnTouchListener {


    ArrayList<Article> articles = new ArrayList<>();
    private Button refreshButton;
    private Button filterLocation;
    private Button filterDepth;
    private Button filterDate;
    private Button filterMagnitude;
    private String urlAddress="http://quakes.bgs.ac.uk/feeds/MhSeismology.xml";
    private Context c;

    public ListActivity() {
    }
        protected void onCreate( Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list);

            refreshButton =(Button)findViewById(R.id.refreshButton);
            refreshButton.setOnTouchListener(this);
            filterLocation = (Button)findViewById(R.id.setFilterLocation);
            filterLocation.setOnTouchListener(this);
            filterDate= (Button)findViewById(R.id.setFilterDate);
            filterDate.setOnTouchListener( this);
            filterDepth = (Button)findViewById(R.id.setFilterDepth);
            filterDepth.setOnTouchListener(this);
            filterMagnitude = (Button)findViewById(R.id.setFilterMagnitude);
            filterMagnitude.setOnTouchListener( this);


        final RecyclerView rv= (RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new FilterAdapter(this,articles));

        new Downloader(ListActivity.this,urlAddress, rv).execute();

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Intent intenthome = new Intent(ListActivity.this, MainActivity.class);
                        startActivity(intenthome);
                        break;
                    case R.id.nav_list:
                       // Intent intentlist = new Intent(ListActivity.this, ListActivity.class);//
                        // startActivity(intentlist);
                        break;
                    case R.id.nav_maps:
                        Intent intentmap = new Intent(ListActivity.this, MapsActivity.class);
                        startActivity(intentmap);
                        break;
                }

                return false;
            }
        });


    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
//        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String filteredText) {
        MyAdapter rv = new MyAdapter(c, articles);
        filteredText = filteredText.toLowerCase();
        ArrayList<Article> filterList = new ArrayList<>();
        for (Article article : articles) {
            String location = article.getLocation().toLowerCase();
            if (location.contains(filteredText))
                filterList.add(article);

        }
        rv.setFilter(filterList);
        return false;


    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {

            v.setPressed(false);
            switch (v.getId()){
                case R.id.refreshButton:
                    final RecyclerView frv= (RecyclerView)findViewById(R.id.rv);
                    frv.setLayoutManager(new LinearLayoutManager(this));
                    frv.setAdapter(new FilterAdapter(this,articles));
                    new Downloader(ListActivity.this,urlAddress, frv).execute();

                    break;
                case R.id.setFilterLocation:
                    Toast.makeText(this,"Location Not Implemented", Toast.LENGTH_SHORT).show();
                    v.setPressed(true);
                    break;
                case R.id.setFilterDepth:
                    Toast.makeText(this,"Depth Not Implemented", Toast.LENGTH_SHORT).show();
                    v.setPressed(true);
                    break;
                case R.id.setFilterDate:
                    Toast.makeText(this,"Date Not Implemented", Toast.LENGTH_SHORT).show();
                    v.setPressed(true);
                    break;
                case R.id.setFilterMagnitude:
                    Toast.makeText(this,"Magnitude Not Implemented", Toast.LENGTH_SHORT).show();
                    v.setPressed(true);
                    break;
            }
        }
        return true;//Return true, so there will be no onClick-event
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.refreshButton:
//                final RecyclerView frv= (RecyclerView)findViewById(R.id.rv);
//                frv.setLayoutManager(new LinearLayoutManager(this));
//                frv.setAdapter(new FilterAdapter(this,articles));
//                new Downloader(ListActivity.this,urlAddress, frv).execute();
//                break;
//            case R.id.setFilterLocation:
//                Toast.makeText(this,"Location Not Implemented", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.setFilterDepth:
//                Toast.makeText(this,"Depth Not Implemented", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.setFilterDate:
//                Toast.makeText(this,"Date Not Implemented", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.setFilterMagnitude:
//                Toast.makeText(this,"Magnitude Not Implemented", Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }
}

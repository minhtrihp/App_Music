package com.example.appmusic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import adapter.ListViewAdapter;
import fragment.Fragment_Trinh_Phat;

public class PlayListActivity extends AppCompatActivity {
    public ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    private ArrayList<HashMap<String, String>> songsListData = new ArrayList<HashMap<String, String>>();
    private SimpleAdapter adapter;
    private ListView lv;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Playlist");

        lv = (ListView) findViewById(R.id.listview);
        songsListData.addAll(songsList);
        adapter = new SimpleAdapter(PlayListActivity.this, songsListData,R.layout.playlist_item, new String[] { "songTitle" }, new int[] {R.id.songTitle });
        //adapter = new ListViewAdapter(getApplicationContext(),songsListData);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(getApplicationContext(), Fragment_Trinh_Phat.class);
                in.putExtra("songIndex", position);
                setResult(100, in);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search);
        final SearchView searchView =(SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.filter(newText);
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
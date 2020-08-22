package activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.R;

import java.util.ArrayList;

import adapter.DanhSachBaiHatPlaylistAdapter;
import model.Playlist;

public class DanhSachBaiHatPlayListActivity extends AppCompatActivity {
    Toolbar toolBarDanhSachPlaylist;
    RecyclerView recyclerViewDSBaiHatPlayList;

    ArrayList<Playlist> playlistArrayList;

    DanhSachBaiHatPlaylistAdapter danhSachBaiHatPlaylistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat_play_list);
        
        anhXa();
        
        initActionBar();
        getData();
    }

    private void getData() {
        playlistArrayList = new ArrayList<>();

        playlistArrayList.add(new Playlist("Nhạc hot", R.drawable.boyfriend, R.drawable.stay));
        playlistArrayList.add(new Playlist("Ca sĩ Việt Nam", R.drawable.hong_kong_1, R.drawable.amee));
        playlistArrayList.add(new Playlist("EDM ", R.drawable.edm, R.drawable.inthenameoflove));
        playlistArrayList.add(new Playlist("Top 100", R.drawable.havana, R.drawable.havana));
        playlistArrayList.add(new Playlist("Chill", R.drawable.chill, R.drawable.loi_yeu_ngay_dai));
        playlistArrayList.add(new Playlist("Thanh Xuân", R.drawable.thanhxuan, R.drawable.thangdien));


        danhSachBaiHatPlaylistAdapter = new DanhSachBaiHatPlaylistAdapter(DanhSachBaiHatPlayListActivity.this,
                this.playlistArrayList);
        recyclerViewDSBaiHatPlayList.setLayoutManager(new GridLayoutManager(DanhSachBaiHatPlayListActivity.this,
                2));
        recyclerViewDSBaiHatPlayList.setAdapter(danhSachBaiHatPlaylistAdapter);
    }

    private void initActionBar() {
        setSupportActionBar(toolBarDanhSachPlaylist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playlist");
        toolBarDanhSachPlaylist.setTitleTextColor(Color.rgb(140, 25, 85));

        toolBarDanhSachPlaylist.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhXa() {
        toolBarDanhSachPlaylist = (Toolbar) findViewById(R.id.toolBarDanhSachPlaylist);
        recyclerViewDSBaiHatPlayList = (RecyclerView) findViewById(R.id.recyclerViewDSBaiHatPlayList);
    }
}
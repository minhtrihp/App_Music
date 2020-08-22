package activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.appmusic.R;

import java.util.ArrayList;

import adapter.DanhSachTatCaAlbumAdapter;
import model.Album;

public class DanhSachTatCaAlbumActivity extends AppCompatActivity {
    Toolbar toolBarDanhSachTatCaAlbum;
    RecyclerView recyclerViewDanhSachTatCaAlbum;

    ArrayList<Album> albumArrayList;
    DanhSachTatCaAlbumAdapter danhSachTatCaAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tat_ca_album);

        anhXa();
        init();

        getData();
    }

    private void getData() {
        albumArrayList = new ArrayList<>();

        albumArrayList.add(new Album("Nữ hoàng Ariana Grande", "Arriana Grande", R.drawable.arianagrande_album));
        albumArrayList.add(new Album("Vpop", "Vpop", R.drawable.vpop_album));
        albumArrayList.add(new Album("Young", "Young", R.drawable.tuoitre_album));


        danhSachTatCaAlbumAdapter = new DanhSachTatCaAlbumAdapter(DanhSachTatCaAlbumActivity.this,
                this.albumArrayList);

        recyclerViewDanhSachTatCaAlbum.setLayoutManager(new GridLayoutManager(DanhSachTatCaAlbumActivity.this,
                2));
        recyclerViewDanhSachTatCaAlbum.setAdapter(danhSachTatCaAlbumAdapter);
    }

    private void init() {
        setSupportActionBar(toolBarDanhSachTatCaAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả các Album");
        toolBarDanhSachTatCaAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhXa() {
        toolBarDanhSachTatCaAlbum = (Toolbar) findViewById(R.id.toolBarDanhSachTatCaAlbum);
        recyclerViewDanhSachTatCaAlbum = (RecyclerView) findViewById(R.id.recyclerViewDanhSachTatCaAlbum);
    }
}
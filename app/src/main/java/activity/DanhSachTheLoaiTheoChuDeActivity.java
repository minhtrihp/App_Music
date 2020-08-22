package activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appmusic.R;

import java.util.ArrayList;

import adapter.DanhSachTatCaChuDeAdapter;
import adapter.DanhSachTheLoaiTheoChuDeAdapter;
import model.ChuDe;
import model.TheLoai;

import static adapter.DanhSachTatCaChuDeAdapter.*;

public class DanhSachTheLoaiTheoChuDeActivity extends AppCompatActivity {
    ChuDe chuDe;
    TheLoai theLoai;

    RecyclerView recyclerViewDSTheLoaiTheChuDe;
    Toolbar toolBarDanhSachTheLoaiTheChuDe;

    ArrayList<TheLoai> theLoaiArrayList;

    DanhSachTheLoaiTheoChuDeAdapter danhSachTheLoaiTheoChuDeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_the_loai_theo_chu_de);
        GetIntent();

        anhXa();
        init();
        
        getData();

    }

    private void getData() {
        theLoaiArrayList = new ArrayList<>();

        Intent intent = getIntent();
//chỉnh hình ảnh 2 tab
        if (intent.hasExtra("item")) {
            if (intent.getStringExtra("item").equals("Ca sĩ hot")) {
                theLoaiArrayList.add(theLoai = new TheLoai("Sơn Tùng MTP", R.drawable.vpop_album));
                theLoaiArrayList.add(theLoai = new TheLoai("Melanie Martinez", R.drawable.melaniemartinez));
                theLoaiArrayList.add(theLoai = new TheLoai("Billie Eilish", R.drawable.billie_eilish));
            } else if (intent.getStringExtra("item").equals("Cover")) {
                theLoaiArrayList.add(theLoai = new TheLoai("Jfla", R.drawable.cover));
            } else if (intent.getStringExtra("item").equals("Âm nhạc buồn")) {
                theLoaiArrayList.add(theLoai = new TheLoai("Nỗi buồn cuối chiều", R.drawable.cuoichieu));
                theLoaiArrayList.add(theLoai = new TheLoai("Ngày chia tay", R.drawable.haitugio));
            } else if (intent.getStringExtra("item").equals("Giai điệu bất hủ")) {
                theLoaiArrayList.add(theLoai = new TheLoai("Mưa hát", R.drawable.ngaymua));
                theLoaiArrayList.add(theLoai = new TheLoai("Ngày yêu", R.drawable.sangnaymua));
            } else if (intent.getStringExtra("item").equals("Yêu là chân ái")) {
                theLoaiArrayList.add(theLoai = new TheLoai("Yêu không kiểm soát", R.drawable.yeukokiemsoat));
                theLoaiArrayList.add(theLoai = new TheLoai("Một cú lừa", R.drawable.motculua));
            } else if (intent.getStringExtra("item").equals("Dance Pop")) {
                theLoaiArrayList.add(theLoai = new TheLoai("Yêu không kiểm soát", R.drawable.yeukokiemsoat));
                theLoaiArrayList.add(theLoai = new TheLoai("Một cú lừa", R.drawable.motculua));
            } else if (intent.getStringExtra("item").equals("Thập niên")) {
                theLoaiArrayList.add(theLoai = new TheLoai("Mưa hát", R.drawable.ngaymua));
                theLoaiArrayList.add(theLoai = new TheLoai("Ngày yêu", R.drawable.sangnaymua));
            } else if (intent.getStringExtra("item").equals("Music for love")) {
                theLoaiArrayList.add(theLoai = new TheLoai("Nỗi buồn cuối chiều", R.drawable.cuoichieu));
                theLoaiArrayList.add(theLoai = new TheLoai("Ngày chia tay", R.drawable.haitugio));
            }
        }
        danhSachTheLoaiTheoChuDeAdapter = new DanhSachTheLoaiTheoChuDeAdapter(DanhSachTheLoaiTheoChuDeActivity.this,
                theLoaiArrayList);

        recyclerViewDSTheLoaiTheChuDe.setLayoutManager(new GridLayoutManager(DanhSachTheLoaiTheoChuDeActivity.this,
                2));
        recyclerViewDSTheLoaiTheChuDe.setAdapter(danhSachTheLoaiTheoChuDeAdapter);
    }

    private void anhXa() {
        recyclerViewDSTheLoaiTheChuDe = (RecyclerView) findViewById(R.id.recyclerViewDSTheLoaiTheChuDe);
        toolBarDanhSachTheLoaiTheChuDe = (Toolbar) findViewById(R.id.toolBarDanhSachTheLoaiTheChuDe);
    }

    private void init() {
        setSupportActionBar(toolBarDanhSachTheLoaiTheChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());

        toolBarDanhSachTheLoaiTheChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();

        if (intent.hasExtra("chuDe")) {
            chuDe = (ChuDe) intent.getSerializableExtra("chuDe");
        }
    }
}
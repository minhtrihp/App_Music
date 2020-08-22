package activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.appmusic.R;

import java.util.ArrayList;

import adapter.DanhSachTatCaChuDeAdapter;
import model.ChuDe;

public class DanhSachTatCaChuDeActivity extends AppCompatActivity {
    Toolbar toolBarDanhSachTatCaChuDe;
    RecyclerView recyclerViewDSTatCaChuDe;

    ChuDe chuDe;
    ArrayList<ChuDe> chuDeArrayList;
    DanhSachTatCaChuDeAdapter danhSachTatCaChuDeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tat_ca_chu_de);
        
        anhXa();
        init();
        
        getData();
    }

    private void getData() {
        chuDeArrayList = new ArrayList<>();

        chuDeArrayList.add(chuDe = new ChuDe("Ca sĩ hot", R.drawable.casihot));
        chuDeArrayList.add(chuDe = new ChuDe("Âm nhạc buồn", R.drawable.giaidieubuon));
        chuDeArrayList.add(chuDe = new ChuDe("Giai điệu bất hủ", R.drawable.thapnien_chude));
        chuDeArrayList.add(chuDe = new ChuDe("Yêu là chân ái", R.drawable.chanai));

        danhSachTatCaChuDeAdapter = new DanhSachTatCaChuDeAdapter(DanhSachTatCaChuDeActivity.this,
                chuDeArrayList);

        recyclerViewDSTatCaChuDe.setLayoutManager(new GridLayoutManager(DanhSachTatCaChuDeActivity.this,
                1));
        recyclerViewDSTatCaChuDe.setAdapter(danhSachTatCaChuDeAdapter);
    }

    private void init() {
        setSupportActionBar(toolBarDanhSachTatCaChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả chủ đề");

        toolBarDanhSachTatCaChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhXa() {
        toolBarDanhSachTatCaChuDe = (Toolbar) findViewById(R.id.toolBarDanhSachTatCaChuDe);
        recyclerViewDSTatCaChuDe = (RecyclerView) findViewById(R.id.recyclerViewDSTatCaChuDe);
    }
}
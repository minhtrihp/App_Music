package activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusic.R;
import com.google.android.material.tabs.TabLayout;

import adapter.MainViewFragmentAdapter;
import fragment.Fragment_Ca_Nhan;
import fragment.Fragment_Trang_Chu;
import fragment.Fragment_Trinh_Phat;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class HomePage extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        andXa();
        init();
        requestPermission();
    }

    private void init() {
        MainViewFragmentAdapter adapter = new MainViewFragmentAdapter(getSupportFragmentManager());

        adapter.addFragment(new Fragment_Trang_Chu(), "Home");
        adapter.addFragment(new Fragment_Ca_Nhan(), "Personal");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.personal);
    }

    private void andXa() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }
    /**
     * Hàm xin cấp quyền truy cập SDcard
     **/
    private void requestPermission(){
        int requestPermissionCode = 1;
        ActivityCompat.requestPermissions(HomePage.this, new
                String[]{READ_EXTERNAL_STORAGE}, requestPermissionCode);
    }
}

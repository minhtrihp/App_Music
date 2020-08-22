package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusic.R;

import java.util.ArrayList;

import adapter.BannerAdapter;
import me.relex.circleindicator.CircleIndicator;
import model.songBanner;

public class Fragment_Banner extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;

    BannerAdapter bannerAdapter;
    ArrayList<songBanner> songBanners;

    Handler handler;
    Runnable runnable;

    /**
     * biến kiểm tra item hiện tại trong danh sách banner.
     */
    int currentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        andXa();
        getData();

        return view;
    }

    /**
     * Lấy dữ liệu, đưa lên banner quảng cáo.
     */
    private void getData() {
        songBanners = new ArrayList<>();
        bannerAdapter = new BannerAdapter(getActivity(), songBanners);
        bannerAdapter.addSong();

        viewPager.setAdapter(bannerAdapter);
        circleIndicator.setViewPager(viewPager);

        /**
         * handler để xử lý.
         */
        handler = new Handler();
        /**
         * runnable sẽ được gọi khi handler cần.
         */
        runnable = new Runnable() {
            @Override
            public void run() {
                currentItem = viewPager.getCurrentItem();
                currentItem++;
                if (currentItem >= viewPager.getAdapter().getCount()) {
                    currentItem = 0;
                }
                viewPager.setCurrentItem(currentItem, true);
                handler.postDelayed(runnable,10000);
            }
        };
        handler.postDelayed(runnable, 10000);
    }

    private void andXa() {
        viewPager = view.findViewById(R.id.viewPager);
        circleIndicator = view.findViewById(R.id.indicatorDefault);
    }


}

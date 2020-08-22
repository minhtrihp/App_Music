package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import activity.DanhSachBaiHatActivity;
import model.songBanner;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<songBanner> songBanners;

    public BannerAdapter(Context context, ArrayList<songBanner> songBanners) {
        this.context = context;
        this.songBanners = songBanners;
    }

    public void addSong() {
        songBanners.add(new songBanner("Nếu anh không phiền", "Kaity Nguyễn", R.drawable.pic1_banner, R.drawable.pic1_banner_1, R.raw.neu_anh_khong_phien));
        songBanners.add(new songBanner("Sweet But Psycho", "Ava Max", R.drawable.pic2_banner, R.drawable.pic2_banner_2, R.raw.sweet_but_psycho));
        songBanners.add(new songBanner("Crush", "Tessa Violet", R.drawable.pic3_banner, R.drawable.pic3_banner_3, R.raw.crush));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.banner, null);

        ImageView imgBannerBg = view.findViewById(R.id.imgViewBannerBackGround);
        ImageView imgBanner = view.findViewById(R.id.imgViewBanner);
        TextView txtViewTitle = view.findViewById(R.id.txtViewTitle);
        TextView txtViewDescript = view.findViewById(R.id.txtViewDescription);

        Picasso.with(context)
                .load(songBanners.get(position).getBanner())
                .into(imgBannerBg);
        Picasso.with(context)
                .load(songBanners.get(position).getPicture())
                .into(imgBanner);
        txtViewTitle.setText(songBanners.get(position).getTitle());
        txtViewDescript.setText(songBanners.get(position).getDescription());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("banner", songBanners.get(position));

                context.startActivity(intent);
            }
        });

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return songBanners.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}

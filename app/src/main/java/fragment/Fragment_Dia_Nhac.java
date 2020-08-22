package fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmusic.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Dia_Nhac extends Fragment {
    View view;
    CircleImageView circleImgViewDiaNhac;
    ObjectAnimator objectAnimator;

    Animation animation;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dia_nhac, container, false);
        circleImgViewDiaNhac = view.findViewById(R.id.circleImgViewDiaNhac);

        objectAnimator = ObjectAnimator.ofFloat(circleImgViewDiaNhac, "rotation", 0f, 360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());

        animation = AnimationUtils.loadAnimation(this.view.getContext(), R.anim.disc_rotate);

        return view;
    }

    public void changeRecordBackGround(int picture) {
        circleImgViewDiaNhac.setImageResource(picture);
    }

    public void rotateRecord(boolean isRotated) {
        if (isRotated == true)  {
            circleImgViewDiaNhac.startAnimation(animation);
        } else {
            circleImgViewDiaNhac.clearAnimation();
        }
    }
}

package fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.appmusic.PlayListActivity;
import com.example.appmusic.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Random;

public class Fragment_Trinh_Phat extends Fragment implements MediaPlayer.OnCompletionListener{
    private static final int MY_PERMISSIONS_REQUEST_READ_MEDIA = 1 ;
    private View view;
    private ImageView cdDisc;
    private Animation animation;
    private ImageButton btnPlay;
    private ImageButton  btnForward;
    private ImageButton  btnBackward;
    private ImageButton  btnNext;
    private ImageButton  btnPrevious;
    private ImageButton  btnPlaylist;
    private ImageButton  btnRepeat;
    private ImageButton  btnShuffle;
    private SeekBar songProgressBar;
    private TextView songTitleLabel;
    private TextView     songCurrentDurationLabel;
    private TextView     songTotalDurationLabel;
    private boolean isRepeat        = false;
    private boolean isShuffle       = false;
    private int position            = 0;
    private int forwardTime         = 10000;
    private int backwardTime        = 10000;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_player, container, false);


        mediaPlayer.setOnCompletionListener(this);
        SetDataSource(position);
        anhXa();


        /**
         * Bắt đầu phát nhạc
         **/
        btnPlayOnClickListener();

        /**
         * Chuyển bài hát tiếp theo
         * Nếu đi đến cuối danh sách phát thì phát bài hát đầu tiên
         **/
        btnNextOnClickListener();

        /**
         * Quay lại bài hát trước đó
         * Nếu đi đến đầu danh sách phát thì phát bài hát cuối cùng
         **/
        btnPreviousOnClickListener();

        /**
         * Lặp lại bài hát đang phát
         **/
        btnRepeatOnClickListener();

        /**
         * Phát ngẫu nhiên bài hát
         **/
        btnShuffleOnClickListener();

        /**
         * Tua nhanh 10s
         **/
        btnForwardOnClickListener();

        /**
         * Tua lùi 10s
         **/
        btnBackwardOnClickListener();

        /**
         * Mở danh sách bài
         **/
        btnPlaylistOnClickListener();

        /**
         * Tiến trình chạy bài hát
         **/
        songProgressBarOnSeekBarChangeListener();

        return view;
    }


    /**
     * Hàm lấy ID của các phần tử
     **/
    @SuppressLint("ResourceType")
    private void anhXa() {
        cdDisc                   = (ImageView) view.findViewById(R.id.cdDisc);
        btnPlay                  = (ImageButton) view.findViewById(R.id.btnPlay);
        btnForward               = (ImageButton) view.findViewById(R.id.btnForward);
        btnBackward              = (ImageButton) view.findViewById(R.id.btnBackward);
        btnNext                  = (ImageButton) view.findViewById(R.id.btnNext);
        btnPrevious              = (ImageButton) view.findViewById(R.id.btnPrevious);
        btnPlaylist              = (ImageButton) view.findViewById(R.id.btnPlaylist);
        btnRepeat                = (ImageButton) view.findViewById(R.id.btnRepeat);
        btnShuffle               = (ImageButton) view.findViewById(R.id.btnShuffle);
        songProgressBar          = (SeekBar) view.findViewById(R.id.songProgressBar);
        songTitleLabel           = (TextView) view.findViewById(R.id.songTitle);
        songCurrentDurationLabel = (TextView) view.findViewById(R.id.songCurrentDurationLabel);
        songTotalDurationLabel   = (TextView) view.findViewById(R.id.songTotalDurationLabel);
        animation                = AnimationUtils.loadAnimation(view.getContext(),R.anim.disc_rotate);
    }

    /**
     * Thiết lập độ dài của bài hát
     **/
    private void SetTimeTotal() {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        songTotalDurationLabel.setText(format.format(mediaPlayer.getDuration()));
        songProgressBar.setMax(mediaPlayer.getDuration());
    }

    /**
     * Hiển thị tiến trình của bài hát đang phát
     **/
    private void UpdateTimeSong() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("mm:ss");
                songCurrentDurationLabel.setText(format.format(mediaPlayer.getCurrentPosition()));
                songProgressBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,500);
            }
        },100);
    }

    private void btnPlayOnClickListener() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    if(mediaPlayer.isPlaying()) {
                        if(mediaPlayer != null)
                        {
                            mediaPlayer.pause();
                            btnPlay.setImageResource(R.drawable.btn_play);
                            cdDisc.clearAnimation();
                        }
                    }
                    else {
                        if(mediaPlayer != null) {
                            mediaPlayer.start();
                            cdDisc.startAnimation(animation);
                            btnPlay.setImageResource(R.drawable.btn_pause);
                            SetTimeTotal();
                            UpdateTimeSong();
                        }
                    }
                }
                else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_MEDIA);
                }
            }
        });
    }

    private void btnBackwardOnClickListener() {
        btnBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                if (currentPosition - backwardTime >= 0){
                    mediaPlayer.seekTo(currentPosition - backwardTime);
                }else {
                    mediaPlayer.seekTo(0);
                }
            }
        });
    }

    private void btnForwardOnClickListener() {
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                if (currentPosition + forwardTime <= mediaPlayer.getDuration()){
                    mediaPlayer.seekTo(currentPosition + forwardTime);
                }else {
                    mediaPlayer.seekTo(mediaPlayer.getDuration());
                }
            }
        });
    }

    private void songProgressBarOnSeekBarChangeListener() {
        songProgressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(songProgressBar.getProgress());
            }
        });
    }

    private void btnShuffleOnClickListener() {
        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShuffle) {
                    isShuffle = false;
                    btnShuffle.setImageResource(R.drawable.btn_shuffle);
                } else {
                    isShuffle= true;
                    isRepeat = false;
                    btnShuffle.setImageResource(R.drawable.shuffle_press);
                    Toast.makeText(getContext(), "SHUFFLE IS ON", Toast.LENGTH_SHORT).show();
                    btnRepeat.setImageResource(R.drawable.repeat);
                }
                if(isRepeat){
                    Toast.makeText(getContext(), "SHUFFLE IS ON", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "SHUFFLE IS OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void btnRepeatOnClickListener() {
        btnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRepeat) {
                    isRepeat = false;
                    btnRepeat.setImageResource(R.drawable.btn_repeat);
                } else {
                    isRepeat = true;
                    isShuffle = false;
                    btnRepeat.setImageResource(R.drawable.repeat_press);

                    btnShuffle.setImageResource(R.drawable.shuffle);
                }
                if(isRepeat){
                    Toast.makeText(getContext(), "REPEAT IS ON", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "REPEAT IS OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void btnPreviousOnClickListener() {
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position < 0) {

                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                SetDataSource(position);
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.btn_pause);
                SetTimeTotal();
            }
        });
    }

    private void btnNextOnClickListener() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                SetDataSource(position);
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.btn_pause);
                SetTimeTotal();
            }
        });
    }

    private void btnPlaylistOnClickListener() {
        btnPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), PlayListActivity.class);
                startActivityForResult(i, 100);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 100){
            position = data.getExtras().getInt("songIndex");
            // play selected song
            SetDataSource(position);
            mediaPlayer.start();
            cdDisc.startAnimation(animation);
            btnPlay.setImageResource(R.drawable.btn_pause);
            SetTimeTotal();
            UpdateTimeSong();
        }
    }


    /**
     * Hàm lấy vị trí phát nhạc và setDataSource cho MediaPlayer
     **/
    public void  SetDataSource(int songIndex){
        try{
            mediaPlayer.reset();
            mediaPlayer.prepare();
        } catch (IllegalArgumentException | IllegalStateException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onCompletion(MediaPlayer arg0) {
        if(isRepeat){
            SetDataSource(position);
            mediaPlayer.start();
        }else if(isShuffle){
            Random rand = new Random();
            SetDataSource(position);
            mediaPlayer.start();
            SetTimeTotal();
        }else {
            position++;
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            SetDataSource(position);
            mediaPlayer.start();
            SetTimeTotal();
        }
    }
    @Override
    public void onDestroy() {
        mediaPlayer.release();
        super.onDestroy();
    }
}
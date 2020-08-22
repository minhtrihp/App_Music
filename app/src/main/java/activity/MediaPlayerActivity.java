package activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmusic.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import adapter.ViewPagerMediaPlayer;
import fragment.Fragment_Dia_Nhac;
import fragment.Fragment_MediaPlayer_Playlist;
import model.Song;

public class MediaPlayerActivity extends AppCompatActivity {
    Song song;
    ArrayList<Song> songArrayList = new ArrayList<>();
    public static ArrayList<Song> mediaPlayerArrayList = new ArrayList<>();

    Toolbar toolBarMediaPlayer;
    ViewPager viewPagerMediaPlayer;

    ImageButton btnRepeat, btnShuffle;

    TextView txtViewCurrentDuration, txtViewTotalDuration;
    SeekBar seekBarSong;

    ImageButton btnPrevious, btnBackward, btnPlay, btnForward, btnNext;

    public static ViewPagerMediaPlayer viewPagerMediaPlayerAdapter;

    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_MediaPlayer_Playlist fragment_mediaPlayer_playlist;

    MediaPlayer mediaPlayer = new MediaPlayer();

    boolean isRepeat = false;
    boolean isShuffle = false;
    boolean next = false;
    int position = 0;
    int forwardTime = 10000;
    int backwardTime = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        getDataFromIntent();

        anhXa();
        init();

        eventClick();
    }

    private void init() {
        setSupportActionBar(toolBarMediaPlayer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarMediaPlayer.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                mediaPlayerArrayList.clear();
                songArrayList.clear();
            }
        });
        toolBarMediaPlayer.setTitleTextColor(Color.BLACK);

        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_mediaPlayer_playlist = new Fragment_MediaPlayer_Playlist();

        viewPagerMediaPlayerAdapter = new ViewPagerMediaPlayer(getSupportFragmentManager());
        viewPagerMediaPlayerAdapter.addFragment(fragment_mediaPlayer_playlist);
        viewPagerMediaPlayerAdapter.addFragment(fragment_dia_nhac);

        viewPagerMediaPlayer.setAdapter(viewPagerMediaPlayerAdapter);

        fragment_dia_nhac = (Fragment_Dia_Nhac) viewPagerMediaPlayerAdapter.getItem(1);
        if (mediaPlayerArrayList.size() > 0) {
            getSupportActionBar().setTitle(mediaPlayerArrayList.get(0).getSongName());


            new playMp3().execute(mediaPlayerArrayList.get(0).getSongLink());
            btnPlay.setImageResource(R.drawable.btn_pause);
        }
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPagerMediaPlayerAdapter.getItem(1) != null) {
                    if (mediaPlayerArrayList.size() > 0) {
                        fragment_dia_nhac.changeRecordBackGround(mediaPlayerArrayList.get(0).getPicture());
                        fragment_dia_nhac.rotateRecord(true);
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.btn_play);
                    fragment_dia_nhac.rotateRecord(false);
                } else {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.btn_pause);
                    fragment_dia_nhac.rotateRecord(true);
                }
            }
        });

        btnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRepeat == false) {
                    if (isShuffle == true) {
                        isShuffle = false;
                        btnRepeat.setImageResource(R.drawable.repeat_press);
                        btnShuffle.setImageResource(R.drawable.shuffle);
                    }
                    btnRepeat.setImageResource(R.drawable.repeat_press);
                    isRepeat = true;
                } else {
                    btnRepeat.setImageResource(R.drawable.repeat);
                    isRepeat = false;
                }
            }
        });

        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShuffle == false) {
                    if (isRepeat == true) {
                        isRepeat = false;
                        btnShuffle.setImageResource(R.drawable.shuffle_press);
                        btnRepeat.setImageResource(R.drawable.repeat);
                    }
                    btnShuffle.setImageResource(R.drawable.shuffle_press);
                    isShuffle = true;
                } else {
                    btnShuffle.setImageResource(R.drawable.shuffle);
                    isShuffle = false;
                }
            }
        });

        seekBarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayerArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();

                        mediaPlayer = null;
                    }
                    if (position < mediaPlayerArrayList.size()) {
                        btnPlay.setImageResource(R.drawable.pause);
                        position++;
                        if (isRepeat == true) {
                            if (position == 0) {
                                position = mediaPlayerArrayList.size();
                            }
                            position -= 1;
                        }
                        if (isShuffle == true) {
                            Random random = new Random();
                            int index = random.nextInt(mediaPlayerArrayList.size());

                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mediaPlayerArrayList.size() - 1)) {
                            position = 0;
                        }
                        new playMp3().execute(mediaPlayerArrayList.get(position).getSongLink());
                        fragment_dia_nhac.changeRecordBackGround(mediaPlayerArrayList.get(position).getPicture());
                        getSupportActionBar().setTitle(mediaPlayerArrayList.get(position).getSongName());
                        updateTime();
                    }
                }
                btnPrevious.setClickable(false);
                btnNext.setClickable(false);

                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnPrevious.setClickable(true);
                        btnNext.setClickable(true);
                    }
                }, 500);
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayerArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();

                        mediaPlayer = null;
                    }
                    if (position < mediaPlayerArrayList.size()) {
                        btnPlay.setImageResource(R.drawable.pause);
                        position--;
                        if (position < 0) {
                            position = mediaPlayerArrayList.size() - 1;
                        }
                        if (isRepeat == true) {
                            position += 1;
                        }
                        if (isShuffle == true) {
                            Random random = new Random();
                            int index = random.nextInt(mediaPlayerArrayList.size());

                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        new playMp3().execute(mediaPlayerArrayList.get(position).getSongLink());
                        fragment_dia_nhac.changeRecordBackGround(mediaPlayerArrayList.get(position).getPicture());
                        getSupportActionBar().setTitle(mediaPlayerArrayList.get(position).getSongName());
                        updateTime();
                    }
                }
                btnPrevious.setClickable(false);
                btnNext.setClickable(false);

                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnPrevious.setClickable(true);
                        btnNext.setClickable(true);
                    }
                }, 500);
            }
        });

        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                if (currentPosition + forwardTime <= mediaPlayer.getDuration()) {
                    mediaPlayer.seekTo(currentPosition + forwardTime);
                } else {
                    mediaPlayer.seekTo(mediaPlayer.getDuration());
                }
            }
        });

        btnBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                if (currentPosition - backwardTime >= 0) {
                    mediaPlayer.seekTo(currentPosition - backwardTime);
                } else {
                    mediaPlayer.seekTo(0);
                }
            }
        });
    }

    private void updateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBarSong.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat format = new SimpleDateFormat("mm:ss");
                    txtViewCurrentDuration.setText(format.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 500);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (position < mediaPlayerArrayList.size()) {
                        btnPlay.setImageResource(R.drawable.pause);
                        position++;
                        if (isRepeat == true) {
                            if (position == 0) {
                                position = mediaPlayerArrayList.size();
                            }
                            position -= 1;
                        }
                        if (isShuffle == true) {
                            Random random = new Random();
                            int index = random.nextInt(mediaPlayerArrayList.size());

                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mediaPlayerArrayList.size() - 1)) {
                            position = 0;
                        }
                        new playMp3().execute(mediaPlayerArrayList.get(position).getSongLink());
                        fragment_dia_nhac.changeRecordBackGround(mediaPlayerArrayList.get(position).getPicture());
                        getSupportActionBar().setTitle(mediaPlayerArrayList.get(position).getSongName());
                    }

                    btnPrevious.setClickable(false);
                    btnNext.setClickable(false);

                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnPrevious.setClickable(true);
                            btnNext.setClickable(true);
                        }
                    }, 500);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

    class playMp3 extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... integers) {
            return integers[0];
        }

        @Override
        protected void onPostExecute(Integer song) {
            super.onPostExecute(song);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer = MediaPlayer.create(getApplicationContext(), song);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }

            mediaPlayer.start();
            SetTimeTotal();
            updateTime();
        }
    }

    private void SetTimeTotal() {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        txtViewTotalDuration.setText(format.format(mediaPlayer.getDuration()));
        seekBarSong.setMax(mediaPlayer.getDuration());
    }

    private void getDataFromIntent() {

        Intent intent = getIntent();
        mediaPlayerArrayList.clear();

        if (intent != null) {
            if (intent.hasExtra("song")) {
                song = intent.getParcelableExtra("song");
                mediaPlayerArrayList.add(song);
            }

            if (intent.hasExtra("allSong")) {
                songArrayList = intent.getParcelableArrayListExtra("allSong");

                mediaPlayerArrayList = songArrayList;
            }
        }
    }

    private void anhXa() {
        toolBarMediaPlayer = (Toolbar) findViewById(R.id.toolBarMediaPlayer);
        viewPagerMediaPlayer = (ViewPager) findViewById(R.id.viewPagerMediaPlayer);

        btnRepeat = (ImageButton) findViewById(R.id.btnRepeat);
        btnShuffle = (ImageButton) findViewById(R.id.btnShuffle);

        txtViewCurrentDuration = (TextView) findViewById(R.id.txtViewCurrentDuration);
        txtViewTotalDuration = (TextView) findViewById(R.id.txtViewTotalDuration);

        seekBarSong = (SeekBar) findViewById(R.id.seekBarSong);

        btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
        btnBackward = (ImageButton) findViewById(R.id.btnBackward);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnForward = (ImageButton) findViewById(R.id.btnForward);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
    }
}
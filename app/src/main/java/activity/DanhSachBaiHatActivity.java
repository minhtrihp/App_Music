package activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import adapter.DanhSachBaiHatAdapter;
import model.Album;
import model.Playlist;
import model.Song;
import model.TheLoai;
import model.songBanner;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    songBanner songBanner;

    CoordinatorLayout coordinatorLayoutDsBaiHat;
    CollapsingToolbarLayout collapsingToolbarLayout;
    androidx.appcompat.widget.Toolbar toolBarDanhSach;
    ImageView imgViewDanhSachCaKhuc;
    FloatingActionButton floatingActionButtonDsBaiHat;
    RecyclerView recyclerViewDSBaiHat;

    ArrayList<Song> songArrayList;

    DanhSachBaiHatAdapter danhSachBaiHatAdapter;
    Playlist playlist;

    TheLoai theLoai;
    Album album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);

        anhXa();
        DataIntent();

        init();

        if (songBanner != null && !songBanner.getTitle().equals("")) {
            setValueView(songBanner.getTitle(), songBanner.getPicture());
            getDataBanner(songBanner.getTitle(), songBanner.getPicture(), songBanner.getDescription(), songBanner.getLinkSong());
        }


        if (playlist != null && !playlist.getName().equals("")) {
            setValueView(playlist.getName(), playlist.getIcon());

            getDataPlaylist(playlist.getName());
        }

        if (theLoai != null && !theLoai.getTenTheLoai().equals("")) {
            setValueView(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            getDataTheLoai(theLoai.getTenTheLoai());
        }

        if (album != null && !album.getTenAlbum().equals("")) {
            setValueView(album.getTenAlbum(), album.getHinhAlbum());
            getDataAlbum(album.getTenAlbum());
        }
    }

    private void getDataAlbum(String tenAlbum) {
        songArrayList = new ArrayList<>();

        if (tenAlbum.equals("Blank Space")) {
            songArrayList.add(new Song("Blank Space", R.drawable.pic1_album, "Taylor Swift", R.raw.blank_space));
            songArrayList.add(new Song("Look what you made me do", R.drawable.lookwhatyoumademedo, "Taylor Swift", R.raw.look_what_you_made_me_do));
            songArrayList.add(new Song("Red", R.drawable.red, "Taylor Swift", R.raw.red));
            songArrayList.add(new Song("22", R.drawable.twenty, "Taylor Swift", R.raw.twenty));
            songArrayList.add(new Song("Gorgeous", R.drawable.gorgeous, "Taylor Swift", R.raw.gorgeous));
        } else if (tenAlbum.equals("Something just kike this")) {
            songArrayList.add(new Song("Something just kike this", R.drawable.pic1_album, "The Chainsmokers", R.raw.something_just_like_this));
            songArrayList.add(new Song("Dont let me down", R.drawable.pic1_album, "The Chainsmokers", R.raw.dont_let_me_down));
            songArrayList.add(new Song("Sick boy", R.drawable.sickboy, "The Chainsmokers", R.raw.sick_boy));
            songArrayList.add(new Song("Closer", R.drawable.closer, "The Chainsmokers", R.raw.closer));
            songArrayList.add(new Song("Paris", R.drawable.paris, "The Chainsmokers", R.raw.paris));
            songArrayList.add(new Song("Roses", R.drawable.roses, "The Chainsmokers", R.raw.roses));
            songArrayList.add(new Song("All we know", R.drawable.allweknow, "The Chainsmokers", R.raw.all_we_know));
        } else if (tenAlbum.equals("Wolves")) {
            songArrayList.add(new Song("Boyfriend", R.drawable.boyfriend, "Selena Gomez", R.raw.boyfriend));
            songArrayList.add(new Song("Come and get it", R.drawable.comeandgetit, "Selena Gomez", R.raw.come_get_it));
            songArrayList.add(new Song("Back to you", R.drawable.backtou, "Selena Gomez", R.raw.back_to_you));
            songArrayList.add(new Song("Wolves", R.drawable.pic1_album, "Selena Gomez", R.raw.wolves));
            songArrayList.add(new Song("Slow down", R.drawable.slowdown, "Selena Gomez", R.raw.slow_down));
        }else if (tenAlbum.equals("Nữ hoàng Ariana Grande")) {
            songArrayList.add(new Song("Seven rings", R.drawable.sevenring, "Ariana Grande", R.raw.seven_rings));
            songArrayList.add(new Song("Blank Space", R.drawable.pic1_album, "Taylor Swift", R.raw.blank_space));
            songArrayList.add(new Song("Look what you made me do", R.drawable.lookwhatyoumademedo, "Taylor Swift", R.raw.look_what_you_made_me_do));
            songArrayList.add(new Song("Red", R.drawable.red, "Taylor Swift", R.raw.red));
            songArrayList.add(new Song("22", R.drawable.twenty, "Taylor Swift", R.raw.twenty));
            songArrayList.add(new Song("Gorgeous", R.drawable.gorgeous, "Taylor Swift", R.raw.gorgeous));
        }else if (tenAlbum.equals("Vpop")) {
            songArrayList.add(new Song("Như lời đồn", R.drawable.nhuloidon, "Bảo Anh", R.raw.nhu_loi_don));
            songArrayList.add(new Song("Vì ai vì anh", R.drawable.viaivianh, "Đông Nhi", R.raw.vi_ai_vi_anh));
        }else if (tenAlbum.equals("Young")) {
            songArrayList.add(new Song("Something just kike this", R.drawable.pic1_album, "The Chainsmokers", R.raw.something_just_like_this));
            songArrayList.add(new Song("Dont let me down", R.drawable.pic1_album, "The Chainsmokers", R.raw.dont_let_me_down));
            songArrayList.add(new Song("Sick boy", R.drawable.sickboy, "The Chainsmokers", R.raw.sick_boy));
            songArrayList.add(new Song("Closer", R.drawable.closer, "The Chainsmokers", R.raw.closer));
            songArrayList.add(new Song("Paris", R.drawable.paris, "The Chainsmokers", R.raw.paris));
            songArrayList.add(new Song("Roses", R.drawable.roses, "The Chainsmokers", R.raw.roses));
            songArrayList.add(new Song("All we know", R.drawable.allweknow, "The Chainsmokers", R.raw.all_we_know));
        }

        danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this,
                this.songArrayList);

        recyclerViewDSBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
        recyclerViewDSBaiHat.setAdapter(danhSachBaiHatAdapter);

        eventClick();
    }

    private void getDataTheLoai(String name) {
        songArrayList = new ArrayList<>();

        if (name.equals("R & B")) {
            songArrayList.add(new Song("Symphony", R.drawable.symphony, "Clean Bandit", R.raw.symphony));
            songArrayList.add(new Song("Oh my oh", R.drawable.ohmyoh, "Camila Cabello", R.raw.my_oh_my));
            songArrayList.add(new Song("Vì ai vì anh", R.drawable.viaivianh, "Đông Nhi", R.raw.vi_ai_vi_anh));
            songArrayList.add(new Song("Pink girl", R.drawable.pinkgirl, "Đông Nhi", R.raw.pink_girl));
            songArrayList.add(new Song("Như lời đồn", R.drawable.nhuloidon, "Bảo Anh", R.raw.nhu_loi_don));
            songArrayList.add(new Song("In the night", R.drawable.inthenight, "Bảo Anh", R.raw.in_the_night));
        } else if (name.equals("Acoustic Pop")) {
            songArrayList.add(new Song("Người lạ ơi", R.drawable.nguoilaoi, "Orange, Karik", R.raw.nguoi_la_oi));
            songArrayList.add(new Song("Thằng điên", R.drawable.thangdien, "Phương Ly, JustaTee", R.raw.thang_dien));
            songArrayList.add(new Song("Sóng gió", R.drawable.songgio, "Jack", R.raw.song_gio));
            songArrayList.add(new Song("Tình đơn phương", R.drawable.tinhdonphuong, "Dương Edward", R.raw.tinh_don_phuong));
            songArrayList.add(new Song("Sao cũng được", R.drawable.saocungduoc, "Binz", R.raw.sao_cung_duoc));
            songArrayList.add(new Song("Ok", R.drawable.ok, "Binz", R.raw.ok));

        } else if (name.equals("Dance Pop")) {
            songArrayList.add(new Song("As long as you love me", R.drawable.aslongasyouloveme, "Justin Bieber", R.raw.as_long_as_you_love_me));
            songArrayList.add(new Song("Love yourseft", R.drawable.loveyourself, "Justin Bieber", R.raw.love_yourself));
            songArrayList.add(new Song("Let me love you", R.drawable.letmeloveyou, "Justin Bieber", R.raw.let_me_love_you));
            songArrayList.add(new Song("There's nothing holding me back", R.drawable.therenothingholdmeback, "Shawn Mendes", R.raw.there_s_nothing_holdin_me_back));
            songArrayList.add(new Song("Treat you better", R.drawable.treatubetter, "Shawn Mendes", R.raw.treat_you_better));
            songArrayList.add(new Song("Maps", R.drawable.maps, "Maroon 5", R.raw.maps));
            songArrayList.add(new Song("I like me better", R.drawable.ilikemebetter, "Lauv", R.raw.i_like_me_better));
            songArrayList.add(new Song("Seven rings", R.drawable.sevenring, "Ariana Grande", R.raw.seven_rings));
            songArrayList.add(new Song("Blank spaces", R.drawable.pic1_album, "Taylor Swift", R.raw.blank_space));
        } else if (name.equals("Sơn Tùng MTP")) {
            songArrayList.add(new Song("Buông đôi tay nhau ra", R.drawable.aslongasyouloveme, "Sơn Tùng MTP", R.raw.buong_doi_tay_nhau_ra));
            songArrayList.add(new Song("Cơn mưa ngang qua", R.drawable.aslongasyouloveme, "Sơn Tùng MTP", R.raw.con_mua_ngang_qua));
            songArrayList.add(new Song("Em của ngày hôm qua", R.drawable.aslongasyouloveme, "Sơn Tùng MTP", R.raw.em_cua_ngay_hom_qua));
            songArrayList.add(new Song("Khuôn mặt đáng thương", R.drawable.aslongasyouloveme, "Sơn Tùng MTP", R.raw.khuon_mat_dang_thuong));

        } else if (name.equals("Melanie Martinez")) {
            songArrayList.add(new Song("Cry baby", R.drawable.crybaby, "Melanie Martinez", R.raw.cry_baby));
            songArrayList.add(new Song("Dollhouse", R.drawable.dollhouse, "Melanie Martinez", R.raw.dollhouse));
            songArrayList.add(new Song("Pity party", R.drawable.pityparty, "Melanie Martinez", R.raw.pity_party));
            songArrayList.add(new Song("Mrs. Potato head", R.drawable.potatohead, "Melanie Martinez", R.raw.mrs_potato_head));

        } else if (name.equals("Billie Eilish")) {
            songArrayList.add(new Song("Copycat", R.drawable.copycat, "Billie Eilish", R.raw.copycat));
            songArrayList.add(new Song("idontwannabeyouanymore", R.drawable.idontwannabeyouanymore, "Billie Eilish", R.raw.idontwannabeyouanymore));
            songArrayList.add(new Song("Bellyache", R.drawable.bellyache, "Billie Eilish", R.raw.bellyache));
            songArrayList.add(new Song("My boy", R.drawable.myboy, "Billie Eilish", R.raw.my_boy));
            songArrayList.add(new Song("Ocean Eyes", R.drawable.oceaneyes, "Billie Eilish", R.raw.ocean_eyes));
            songArrayList.add(new Song("Watch", R.drawable.watch, "Billie Eilish", R.raw.watch));
            songArrayList.add(new Song("I wish you were gay", R.drawable.idontwannabeyouanymore, "Billie Eilish", R.raw.wish_you_were_gay));
        } else if (name.equals("Nỗi buồn cuối chiều")) {
            songArrayList.add(new Song("I know you", R.drawable.iknowu, "Skylar Grey", R.raw.i_know_you));
            songArrayList.add(new Song("Sóng gió", R.drawable.songgio, "Jack", R.raw.song_gio));
            songArrayList.add(new Song("Tình đơn phương", R.drawable.tinhdonphuong, "Dương Edward", R.raw.tinh_don_phuong));
        } else if (name.equals("Ngày chia tay")) {
            songArrayList.add(new Song("As long as you love me", R.drawable.aslongasyouloveme, "Justin Bieber", R.raw.as_long_as_you_love_me));
            songArrayList.add(new Song("Nước mắt em lau bằng tình yêu mới", R.drawable.nuocmatem, "Tóc Tiên", R.raw.nuoc_mat_em_lau_bang_tinh_yeu_moi));
            songArrayList.add(new Song("Cry on my shoulder", R.drawable.cryonmyshoulder, "Deutschland sucht den Superstar", R.raw.cry_on_my_shoulder));
            songArrayList.add(new Song("Em gái mưa", R.drawable.cryonmyshoulder, "Hương Tràm", R.raw.em_gai_mua));

        } else if (name.equals("Mưa hát")) {
            songArrayList.add(new Song("Trái tim em cũng biết đau", R.drawable.traitimem, "Bảo Anh", R.raw.trai_tim_em_cung_biet_dau));
            songArrayList.add(new Song("Even", R.drawable.even, "Chirs Brown", R.raw.even));
            songArrayList.add(new Song("Symphony", R.drawable.symphony, "Clean Bandit", R.raw.symphony));
            songArrayList.add(new Song("Sao cũng được", R.drawable.saocungduoc, "Binz", R.raw.sao_cung_duoc));

        } else if (name.equals("Ngày yêu")) {
            songArrayList.add(new Song("Closer", R.drawable.closer, "The Chainsmokers", R.raw.closer));
            songArrayList.add(new Song("Paris", R.drawable.paris, "The Chainsmokers", R.raw.paris));
            songArrayList.add(new Song("Hông kông 1", R.drawable.hong_kong_1, "Nguyễn Trọng Tài", R.raw.hong_kong_1));
            songArrayList.add(new Song("Simple love", R.drawable.simplelove, "Seachains", R.raw.simple_love));
        } else if (name.equals("Yêu không kiểm soát")) {
            songArrayList.add(new Song("Vì yêu cứ đâm đầu", R.drawable.viyeucudamdau, "Min", R.raw.vi_yeu_cu_dam_dau));
            songArrayList.add(new Song("Đếm cừu", R.drawable.demcuu, "Han Sara", R.raw.dem_cuu));
            songArrayList.add(new Song("Thằng điên", R.drawable.thangdien, "Phương Ly, JustaTee", R.raw.thang_dien));
            songArrayList.add(new Song("Boyfriend", R.drawable.boyfriend, "Selena Gomez", R.drawable.boyfriend));

        } else if (name.equals("Một cú lừa")) {
            songArrayList.add(new Song("Yêu em dại khờ", R.drawable.yeudemdaikho, "Lou Hoàng", R.raw.yeu_em_dai_kho));
            songArrayList.add(new Song("Put your head on my shoulder", R.drawable.putyouhead, "Paul Anka", R.raw.put_your_head_on_my_shoulder));
            songArrayList.add(new Song("Call me maybe", R.drawable.call_me_maybe, "Carly Rae Jepsen", R.raw.call_me_maybe));
        } else if (name.equals("Acoustic")) {
            songArrayList.add(new Song("Yêu em dại khờ", R.drawable.yeudemdaikho, "Lou Hoàng", R.raw.yeu_em_dai_kho));
            songArrayList.add(new Song("Put your head on my shoulder", R.drawable.putyouhead, "Paul Anka", R.raw.put_your_head_on_my_shoulder));
            songArrayList.add(new Song("Call me maybe", R.drawable.call_me_maybe, "Carly Rae Jepsen", R.raw.call_me_maybe));
        } else if (name.equals("EDM")) {
            songArrayList.add(new Song("Yêu em dại khờ", R.drawable.yeudemdaikho, "Lou Hoàng", R.raw.yeu_em_dai_kho));
            songArrayList.add(new Song("Put your head on my shoulder", R.drawable.putyouhead, "Paul Anka", R.raw.put_your_head_on_my_shoulder));
            songArrayList.add(new Song("Call me maybe", R.drawable.call_me_maybe, "Carly Rae Jepsen", R.raw.call_me_maybe));
        }

        danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this,
                this.songArrayList);

        recyclerViewDSBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
        recyclerViewDSBaiHat.setAdapter(danhSachBaiHatAdapter);

        eventClick();

    }

    private void getDataPlaylist(String position) {
        songArrayList = new ArrayList<>();

        if (position.equals("Hôm nay nghe gì?")) {
            songArrayList.add(new Song("Anh nhà ở đâu thế?", R.drawable.anhnhaodauthe, "Amee", R.raw.anh_nha_o_dau_the));
            songArrayList.add(new Song("Chân ái", R.drawable.chanai_song, "Khói", R.raw.chan_ai));
            songArrayList.add(new Song("Trời giấu trời mang đi", R.drawable.troigiautroimangdi, "Amee", R.raw.troi_giau_troi_mang_di));
            songArrayList.add(new Song("Yêu rồi", R.drawable.yeuroi, "Tino", R.raw.yeu_roi));
            songArrayList.add(new Song("Intentions", R.drawable.intentions, "Justin Bieber", R.raw.intentions));
            songArrayList.add(new Song("Look what you made me do", R.drawable.intentions, "Taylor Swift", R.raw.intentions));
            songArrayList.add(new Song("Love me like you do", R.drawable.lovemelikeyoudo, "Ellie Goulding", R.raw.love_me_like_you_do));
        } else if (position.equals("Tiệc tùng cuối tuần")) {
            songArrayList.add(new Song("Dance Monkey", R.drawable.dancemonkey, "Tones And I", R.raw.dance_monkey));
            songArrayList.add(new Song("Love me harder", R.drawable.lovemeharder, "Ariana Grande", R.raw.love_me_harder));
            songArrayList.add(new Song("New rules", R.drawable.newrules, "Dua Lipa", R.raw.new_rules));
            songArrayList.add(new Song("Side to side", R.drawable.arianagrande, "Ariana Grande", R.raw.side_to_side));
            songArrayList.add(new Song("Thank you, next", R.drawable.thankyounext, "Ariana Grande", R.raw.thank_u_next));
            songArrayList.add(new Song("The river", R.drawable.theriver, "Axel Johansson", R.raw.the_river));
            songArrayList.add(new Song("2+3=5", R.drawable.haicongba, "T.R.I", R.raw.hai_cong_ba_bang_nam));
        } else if (position.equals("Âm nhạc độc quyền")) {
            songArrayList.add(new Song("Havana", R.drawable.havana, "Camila Cabello", R.raw.havana));
            songArrayList.add(new Song("Him & I", R.drawable.himandi, "Halsey, G-Eazy", R.raw.him_and_i));
            songArrayList.add(new Song("Say so", R.drawable.sayso, "Doja Cat", R.raw.say_so));
            songArrayList.add(new Song("Senorita", R.drawable.senorita, "Camila Cabello", R.raw.senorita));
            songArrayList.add(new Song("Cheap thrill", R.drawable.senorita, "Sia", R.raw.cheap_thrills));
            songArrayList.add(new Song("Faded", R.drawable.senorita, "Alan Walker", R.raw.faded));
            songArrayList.add(new Song("Boyfriend", R.drawable.boyfriend, "Selena Gomez", R.drawable.boyfriend));
        } else if (position.equals("Nhạc hot")) {
            songArrayList.add(new Song("In the name of love", R.drawable.inthenameoflove, "Bebe Rexha, Martin Garrix", R.raw.in_the_name_of_love));
            songArrayList.add(new Song("Sugar", R.drawable.sugar, "Maroon 5", R.raw.sugar));
            songArrayList.add(new Song("Chân ái", R.drawable.chanai_song, "Khói", R.raw.chan_ai));
            songArrayList.add(new Song("Without me", R.drawable.wihoutme, "Halsey", R.raw.without_me));
            songArrayList.add(new Song("Yêu em dại khờ", R.drawable.yeudemdaikho, "Lou Hoàng", R.raw.yeu_em_dai_kho));
            songArrayList.add(new Song("Let me love you", R.drawable.letmeloveyou, "Justin Bieber", R.raw.let_me_love_you));
            songArrayList.add(new Song("Lời yêu ngây dại", R.drawable.loi_yeu_ngay_dai, "Kha", R.raw.loi_yeu_ngay_dai));
        }else if (position.equals("Ca sĩ Việt Nam")) {
            songArrayList.add(new Song("Hông kông 1", R.drawable.hong_kong_1, "Nguyễn Trọng Tài", R.raw.hong_kong_1));
            songArrayList.add(new Song("Simple love", R.drawable.simplelove, "Seachains", R.raw.simple_love));
            songArrayList.add(new Song("Nếu ngày ấy", R.drawable.neungayay, "Soobin Hoàng Sơn", R.raw.neu_ngay_ay));
            songArrayList.add(new Song("Lười yêu", R.drawable.luoiyeu, "Bảo Anh", R.raw.luoi_yeu));
            songArrayList.add(new Song("Ngài ngùng", R.drawable.luoiyeu, "Hương Tràm", R.raw.ngai_ngung));
            songArrayList.add(new Song("Sao chẳng thể vì em", R.drawable.viaivianh, "Đông Nhi", R.raw.sao_chang_the_vi_em));
            songArrayList.add(new Song("Nước mắt em lau bằng tình yêu mới", R.drawable.nuocmatem, "Tóc Tiên", R.raw.nuoc_mat_em_lau_bang_tinh_yeu_moi));
        }else if (position.equals("EDM")) {
            songArrayList.add(new Song("Bad liar", R.drawable.badliar, "Imagine Dragons", R.raw.bad_liar));
            songArrayList.add(new Song("Work from home", R.drawable.workfromhome, "Fifth Harmony", R.raw.work_from_home));
            songArrayList.add(new Song("Havana", R.drawable.havana, "Camila", R.raw.wolves));
            songArrayList.add(new Song("Wolves", R.drawable.pic3_album, "Camila", R.raw.without_me));
            songArrayList.add(new Song("Slow Down", R.drawable.slowdown, "Selena Gomez", R.raw.slow_down));
            songArrayList.add(new Song("Ai cần ai", R.drawable.aicanai, "Bảo Anh", R.raw.ai_can_ai));
            songArrayList.add(new Song("Dont let me down", R.drawable.donletmedown, "Justin Bieber", R.raw.dont_let_me_down));
        }else if (position.equals("Top 100")) {
            songArrayList.add(new Song("Cause I love you", R.drawable.causeiloveu, "Nôp Phước Thịnh", R.raw.cause_i_love_you));
            songArrayList.add(new Song("Một bước vạn dặm đau", R.drawable.motbuocvandam, "Mr.Siro", R.raw.mot_buoc_yeu_van_dam_dau));
            songArrayList.add(new Song("Hơn cả yêu", R.drawable.honcayeu, "Đức Phúc", R.raw.hon_ca_yeu));
            songArrayList.add(new Song("shh chỉ biết ta thôi", R.drawable.shhchibiet, "Chi Pu", R.raw.shh_chi_ta_biet_thoi));
            songArrayList.add(new Song("Trái tim em cũng biết đau", R.drawable.traitimem, "Bảo Anh", R.raw.trai_tim_em_cung_biet_dau));
        }else if (position.equals("Chill")) {
            songArrayList.add(new Song("Chắc anh đang", R.drawable.chacanhdang, "Tiên Tiên", R.raw.chac_anh_dang));
            songArrayList.add(new Song("Một đêm say", R.drawable.motdemsay, "Thịnh Suy", R.raw.mot_dem_say));
            songArrayList.add(new Song("24h", R.drawable.haitugio, "Lyly", R.raw.hai_muoi_tu_gio));
            songArrayList.add(new Song("Đếm cừu", R.drawable.demcuu, "Han Sara", R.raw.dem_cuu));
            songArrayList.add(new Song("2+3=5", R.drawable.haicongba, "T.R.I", R.raw.hai_cong_ba_bang_nam));
            songArrayList.add(new Song("Lời yêu ngây dại", R.drawable.loi_yeu_ngay_dai, "Kha", R.raw.loi_yeu_ngay_dai));
            songArrayList.add(new Song("Một điều mà anh rất ngại nói ra", R.drawable.motdieuma, "Vũ", R.raw.mot_dieu_ma_anh_rat_ngai_noi_ra));
            songArrayList.add(new Song("I need your love", R.drawable.ineedyourlove, "Madilyn Bailey", R.raw.i_need_your_love));
        }else if (position.equals("Thanh Xuân")) {
            songArrayList.add(new Song("Thanh xuân", R.drawable.thanhxuanda, "Da Lab", R.raw.thanh_xuan));
            songArrayList.add(new Song("Suýt nữa thì", R.drawable.suytnuathi, "Andiez", R.raw.suyt_nua_thi));
            songArrayList.add(new Song("Young dumb broke", R.drawable.youngdumb, "Khalid", R.raw.young_dumb_broke));
            songArrayList.add(new Song("Stuck with you", R.drawable.arianagrande, "Ariana Grande", R.raw.stuck_with_u));
            songArrayList.add(new Song("The river", R.drawable.theriver, "Axel Johansson", R.raw.the_river));
            songArrayList.add(new Song("Stay", R.drawable.stay, "Zed", R.raw.stay));
            songArrayList.add(new Song("Vì yêu cứ đâm đầu", R.drawable.viyeucudamdau, "Min", R.raw.vi_yeu_cu_dam_dau));
        }

        danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this,
                this.songArrayList);

        recyclerViewDSBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
        recyclerViewDSBaiHat.setAdapter(danhSachBaiHatAdapter);

        eventClick();
    }

    private void getDataBanner(String title, int picture, String singerName, int linkSong) {
        songArrayList = new ArrayList<>();
        songArrayList.add(new Song(title, picture, singerName, linkSong));

        danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this,
                this.songArrayList);

        recyclerViewDSBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
        recyclerViewDSBaiHat.setAdapter(danhSachBaiHatAdapter);

        eventClick();
    }

    private void setValueView(String name, int picture) {
        collapsingToolbarLayout.setTitle(name);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), picture);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);

        collapsingToolbarLayout.setBackground(bitmapDrawable);

        Picasso.with(this).load(picture).into(imgViewDanhSachCaKhuc);
    }

    private void init() {
        setSupportActionBar(toolBarDanhSach);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarDanhSach.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

        floatingActionButtonDsBaiHat.setEnabled(false);
    }

    private void anhXa() {
        coordinatorLayoutDsBaiHat = (CoordinatorLayout) findViewById(R.id.coordinatorLayoutDsBaiHat);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        toolBarDanhSach = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolBarDanhSach);
        imgViewDanhSachCaKhuc = (ImageView) findViewById(R.id.imgViewDanhSachCaKhuc);
        floatingActionButtonDsBaiHat = (FloatingActionButton) findViewById(R.id.floatingActionButtonDsBaiHat);
        recyclerViewDSBaiHat = (RecyclerView) findViewById(R.id.recyclerViewDSBaiHat);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                songBanner = (songBanner)  intent.getSerializableExtra("banner");
            }

            if (intent.hasExtra("itemPlaylist")) {
                playlist = (Playlist) intent.getSerializableExtra("itemPlaylist");
            }

            if (intent.hasExtra("theLoai")) {
                theLoai = (TheLoai) intent.getSerializableExtra("theLoai");
            }

            if (intent.hasExtra("album")) {
                album = (Album) intent.getSerializableExtra("album");
            }
        }
    }

    private void eventClick() {
        floatingActionButtonDsBaiHat.setEnabled(true);
        floatingActionButtonDsBaiHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachBaiHatActivity.this, MediaPlayerActivity.class);
                intent.putExtra("allSong", songArrayList);
                startActivity(intent);
            }
        });
    }
}
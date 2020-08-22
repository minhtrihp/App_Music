package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.R;

import java.util.ArrayList;

import activity.DanhSachTatCaAlbumActivity;
import adapter.AlbumAdapter;
import model.Album;

public class Fragment_Album extends Fragment {
    View view;

    RecyclerView recycleViewAlbum;
    TextView txtViewXemThemAlbum;

    ArrayList<Album> albumArrayList;
    AlbumAdapter albumAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album, container, false);
        anhXa();
        getData();
        txtViewXemThemAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachTatCaAlbumActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void getData() {
        albumArrayList = new ArrayList<>();
        albumArrayList.add(new Album("Blank Space", "Taylor Swift", R.drawable.pic1_album));
        albumArrayList.add(new Album("Something just kike this", "The Chainsmokers", R.drawable.pic2_album));
        albumArrayList.add(new Album("Wolves", "Selena Gomez", R.drawable.pic3_album));

        albumAdapter = new AlbumAdapter(getActivity(), albumArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        recycleViewAlbum.setLayoutManager(linearLayoutManager);
        recycleViewAlbum.setAdapter(albumAdapter);
    }

    private void anhXa() {
        recycleViewAlbum = view.findViewById(R.id.recycleViewAlbum);
        txtViewXemThemAlbum = view.findViewById(R.id.txtViewXemThemAlbum);
    }
}

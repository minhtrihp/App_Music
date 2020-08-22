package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmusic.R;

import java.util.ArrayList;

import activity.DanhSachBaiHatActivity;
import activity.DanhSachBaiHatPlayListActivity;
import adapter.PlaylistAdapter;
import model.Playlist;

public class Fragment_Playlist extends Fragment {
    View view;
    ListView listViewPlaylist;
    TextView txtViewPlaylistTitle;
    TextView txtViewMorePlaylist;

    PlaylistAdapter playlistAdapter;
    ArrayList<Playlist> playlists;

    public static final String itemPlaylist = "itemPlaylist";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);

        anhXa();
        getData();

        txtViewMorePlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachBaiHatPlayListActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void getData() {
        playlists = new ArrayList<>();
        playlistAdapter = new PlaylistAdapter(getActivity(), android.R.layout.simple_list_item_1, playlists);

        playlists.add(new Playlist("Hôm nay nghe gì?", R.drawable.pic1_playlist, R.drawable.pic1_playlist_1));
        playlists.add(new Playlist("Tiệc tùng cuối tuần", R.drawable.pic2_playlist, R.drawable.pic2_playlist_2));
        playlists.add(new Playlist("Âm nhạc độc quyền", R.drawable.pic3_playlist, R.drawable.pic3_playlist_3));

        listViewPlaylist.setAdapter(playlistAdapter);

        setListViewHeightBasedOnChildren(listViewPlaylist);

        listViewPlaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                intent.putExtra(itemPlaylist, playlists.get(position));

                startActivity(intent);
            }
        });
    }

    private void anhXa() {
        listViewPlaylist = view.findViewById(R.id.listviewPlaylist);
        txtViewPlaylistTitle = view.findViewById(R.id.txtViewPlaylistTitle);
        txtViewMorePlaylist = view.findViewById(R.id.txtViewMorePlaylist);
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}

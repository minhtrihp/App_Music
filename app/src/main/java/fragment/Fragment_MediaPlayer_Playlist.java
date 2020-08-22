package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.PlayerActivity;
import com.example.appmusic.R;

import activity.MediaPlayerActivity;
import adapter.MediaPlayerPlaylistAdapter;

public class Fragment_MediaPlayer_Playlist extends Fragment {
    View view;
    RecyclerView recycleViewMediaPlayerPlaylist;
    MediaPlayerPlaylistAdapter mediaPlayerPlaylistAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_media_play_playlist, container, false);
        recycleViewMediaPlayerPlaylist = view.findViewById(R.id.recycleViewMediaPlayerPlaylist);

        if (MediaPlayerActivity.mediaPlayerArrayList.size() > 0 ) {
            mediaPlayerPlaylistAdapter = new MediaPlayerPlaylistAdapter(getActivity(), MediaPlayerActivity.mediaPlayerArrayList);

            recycleViewMediaPlayerPlaylist.setLayoutManager(new LinearLayoutManager(getActivity()));
            recycleViewMediaPlayerPlaylist.setAdapter(mediaPlayerPlaylistAdapter);
        }

        return view;
    }
}

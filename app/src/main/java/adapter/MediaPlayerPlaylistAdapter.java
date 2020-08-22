package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.R;

import java.util.ArrayList;

import model.Song;

public class MediaPlayerPlaylistAdapter extends RecyclerView.Adapter<MediaPlayerPlaylistAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> songArrayList;

    public MediaPlayerPlaylistAdapter(Context context, ArrayList<Song> songArrayList) {
        this.context = context;
        this.songArrayList = songArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.media_player_playlist_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songArrayList.get(position);
        holder.txtViewTenCaSiMediaPlayer.setText(song.getSinger());
        holder.txtViewMediaPlayerIndex.setText(position + 1 + "");
        holder.txtViewTenMediaPlayer.setText(song.getSongName());
    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewMediaPlayerIndex, txtViewTenMediaPlayer, txtViewTenCaSiMediaPlayer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewMediaPlayerIndex = itemView.findViewById(R.id.txtViewMediaPlayerIndex);
            txtViewTenMediaPlayer = itemView.findViewById(R.id.txtViewTenMediaPlayer);
            txtViewTenCaSiMediaPlayer = itemView.findViewById(R.id.txtViewTenCaSiMediaPlayer);
        }
    }
}

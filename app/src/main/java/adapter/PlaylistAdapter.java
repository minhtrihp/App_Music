package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import model.Playlist;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    Playlist playlist;
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }

    class ViewHolder {
        TextView textViewPlaylist;
        ImageView imageViewBackground, imageViewPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null ) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.playlist_on_homepage, null);

            viewHolder = new ViewHolder();
            viewHolder.textViewPlaylist = convertView.findViewById(R.id.txtViewPlaylist);
            viewHolder.imageViewBackground = convertView.findViewById(R.id.imgViewBackgroundPlaylist);
            viewHolder.imageViewPlaylist = convertView.findViewById(R.id.imgViewPlaylist);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        playlist = getItem(position);
        Picasso.with(getContext())
                .load(playlist.getBackGroundImage())
                .into(viewHolder.imageViewBackground);
        Picasso.with(getContext())
                .load(playlist.getIcon())
                .into(viewHolder.imageViewPlaylist);
        viewHolder.textViewPlaylist.setText(playlist.getName());

        return convertView;
    }
}

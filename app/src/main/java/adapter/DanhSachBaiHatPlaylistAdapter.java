package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import activity.DanhSachBaiHatActivity;
import model.Playlist;

public class DanhSachBaiHatPlaylistAdapter extends RecyclerView.Adapter<DanhSachBaiHatPlaylistAdapter.ViewHolder>{
    Context context;
    ArrayList<Playlist> playlistArrayList;

    public DanhSachBaiHatPlaylistAdapter(Context context, ArrayList<Playlist> playlistArrayList) {
        this.context = context;
        this.playlistArrayList = playlistArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.danh_sach_bai_hat_playlist_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = playlistArrayList.get(position);
        Picasso.with(context).load(playlist.getBackGroundImage()).into(holder.imgViewDanhSachBaiHatPlaylist);
        holder.txtViewTenPlaylist.setText(playlist.getName());
    }

    @Override
    public int getItemCount() {
        return playlistArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgViewDanhSachBaiHatPlaylist;
        TextView txtViewTenPlaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewDanhSachBaiHatPlaylist  = itemView.findViewById(R.id.imgViewDanhSachBaiHatPlaylist);
            txtViewTenPlaylist  = itemView.findViewById(R.id.txtViewTenPlaylist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemPlaylist", playlistArrayList.get(getPosition()));

                    context.startActivity(intent);
                }
            });
        }
    }
}

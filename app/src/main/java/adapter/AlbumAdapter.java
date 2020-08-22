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
import model.Album;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    Context context;
    Album album;
    ArrayList<Album> albumArrayList;

    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.album_on_homepage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        album = albumArrayList.get(position);

        holder.txtViewAlbum.setText(album.getTenAlbum());
        holder.txtViewTenCaSiAlbum.setText(album.getTenCaSiAlbum());

        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgViewAlbum);
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgViewAlbum;
        TextView txtViewAlbum, txtViewTenCaSiAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgViewAlbum = itemView.findViewById(R.id.imgViewAlbum);

            txtViewAlbum = itemView.findViewById(R.id.txtViewAlbum);
            txtViewTenCaSiAlbum = itemView.findViewById(R.id.txtViewTenCaSiAlbum);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("album", albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

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

public class DanhSachTatCaAlbumAdapter extends RecyclerView.Adapter<DanhSachTatCaAlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<Album> albumArrayList;
    Album album;

    public DanhSachTatCaAlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.danh_sach_tat_ca_album_adapter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        album = albumArrayList.get(position);
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgViewDanhSachTatCaAlbum);
        holder.txtViewTenAlbum.setText(album.getTenCaSiAlbum());
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgViewDanhSachTatCaAlbum;
        TextView txtViewTenAlbum, txtViewTenCaSiTrongAlbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgViewDanhSachTatCaAlbum = itemView.findViewById(R.id.imgViewDanhSachTatCaAlbum);
            txtViewTenAlbum = itemView.findViewById(R.id.txtViewTenAlbum);
            txtViewTenCaSiTrongAlbum = itemView.findViewById(R.id.txtViewTenCaSiTrongAlbum);

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

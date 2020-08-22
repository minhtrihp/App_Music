package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.R;

import java.util.ArrayList;

import activity.MediaPlayerActivity;
import model.Song;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> songArrayList;

    public DanhSachBaiHatAdapter(Context context, ArrayList<Song> songArrayList) {
        this.context = context;
        this.songArrayList = songArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.danh_sach_bai_hat_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songArrayList.get(position);
        holder.txtViewTenCaSi.setText(song.getSinger());
        holder.txtViewTenBaiHat.setText(song.getSongName());
        holder.txtViewDanhSachIndex.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewDanhSachIndex, txtViewTenBaiHat, txtViewTenCaSi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtViewDanhSachIndex = itemView.findViewById(R.id.txtViewDanhSachIndex);
            txtViewTenBaiHat = itemView.findViewById(R.id.txtViewTenBaiHat);
            txtViewTenCaSi = itemView.findViewById(R.id.txtViewTenCaSi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MediaPlayerActivity.class);
                    intent.putExtra("song", songArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

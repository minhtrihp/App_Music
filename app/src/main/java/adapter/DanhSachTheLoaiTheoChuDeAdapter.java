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
import model.TheLoai;

public class DanhSachTheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<DanhSachTheLoaiTheoChuDeAdapter.ViewHolder>{
    Context context;
    ArrayList<TheLoai> theLoaiArrayList;

    public DanhSachTheLoaiTheoChuDeAdapter(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.danh_sach_the_loai_theo_chu_de_adapter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = theLoaiArrayList.get(position);

        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imgViewTheLoaiTheoChuDe);
        holder.txtViewTenTheLoaiTheoChuDe.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return theLoaiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgViewTheLoaiTheoChuDe;
        TextView txtViewTenTheLoaiTheoChuDe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgViewTheLoaiTheoChuDe = itemView.findViewById(R.id.imgViewTheLoaiTheoChuDe);
            txtViewTenTheLoaiTheoChuDe = itemView.findViewById(R.id.txtViewTenTheLoaiTheoChuDe);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("theLoai", theLoaiArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

package adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import activity.DanhSachTheLoaiTheoChuDeActivity;
import model.ChuDe;

public class DanhSachTatCaChuDeAdapter extends RecyclerView.Adapter<DanhSachTatCaChuDeAdapter.ViewHolder> {
    Context context;
    ArrayList<ChuDe> chuDeArrayList;
    public static final String chuDe = "chuDe";

    public DanhSachTatCaChuDeAdapter(Context context, ArrayList<ChuDe> chuDeArrayList) {
        this.context = context;
        this.chuDeArrayList = chuDeArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.danh_sach_tat_ca_chu_de_adapter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe = chuDeArrayList.get(position);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(holder.imgViewTatCaChuDe);
    }

    @Override
    public int getItemCount() {
        return chuDeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgViewTatCaChuDe;

        public ViewHolder(View itemView) {
            super(itemView);
            imgViewTatCaChuDe = itemView.findViewById(R.id.imgViewTatCaChuDe);

            imgViewTatCaChuDe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachTheLoaiTheoChuDeActivity.class);
                    intent.putExtra(chuDe, chuDeArrayList.get(getPosition()));
                    intent.putExtra("item", chuDeArrayList.get(getPosition()).getTenChuDe());
                    context.startActivity(intent);
                }
            });
        }
    }
}

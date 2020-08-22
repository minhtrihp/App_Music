package adapter;

import android.content.Context;
import android.net.sip.SipSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appmusic.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> data;
    private int resource;
    private String[] from;
    private int[] to;
    private ArrayList<HashMap<String, String>> temp;
    private SipSession.Listener listener;



    public ListViewAdapter(Context context,  ArrayList<HashMap<String, String>> data) {
        this.data = data;
        this.context = context;
        this.temp = new ArrayList<HashMap<String, String>>();
        this.temp.addAll(data);
        inflater = LayoutInflater.from(context);
    }



    public static class ViewHolder{
        TextView title;
    }

    @Override
    public int getCount() { return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).get("songIndex");
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.playlist_item,null);

            holder.title = view.findViewById(R.id.songTitle);

            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }
        holder.title.setText(data.get(position).get("songTitle"));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return view;
    }


    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if (charText.length() == 0){
            data.addAll(temp);
        }
        else {
            for(HashMap<String, String> item: temp){
                if(Objects.requireNonNull(item.get("songTitle")).toLowerCase(Locale.getDefault()).contains(charText)){
                    data.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}

package com.example.tamakitty20;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    public static List<String> Name;
    public static List<String> Sdesc;
    public static List<String> PriceEu;
    public static List<Bitmap> imgid;


    public CustomAdapter(List<Bitmap> _imgid, List<String> _names, List<String> _desc, List<String> _prices){
        Log.i("debug", "constructor");
        this.imgid = _imgid;
        this.Name = _names;
        this.Sdesc = _desc;
        this.PriceEu = _prices;
        Log.i("debug", "constructor end");
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("debug","oncreate" + viewType);
        Log.i("debug",String.valueOf(viewType));
        LinearLayout linearlayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycle_view,parent, false);
        Log.i("debug",String.valueOf(viewType));
        CustomAdapter.MyViewHolder View = new CustomAdapter.MyViewHolder(linearlayout);
        Log.i("debug","fini");
        return View;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        Log.i("debug","onBind");
        LinearLayout VersionName;
        ImageView iv = (ImageView) holder.VersionName.findViewById(R.id.iv);
        TextView tvn = (TextView) holder.VersionName.findViewById(R.id.KittyName);
        TextView tvd = (TextView) holder.VersionName.findViewById(R.id.KittySSesc);
        TextView tvp = (TextView) holder.VersionName.findViewById(R.id.KittyPrice);


        iv.setImageBitmap(imgid.get(position));
        tvn.setText(Name.get(position));
        tvd.setText(Sdesc.get(position));
        tvp.setText(PriceEu.get(position));
        Log.i("debug","fini2");
    }

    @Override
    public int getItemCount() {

        return 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout VersionName;
        Intent intent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.VersionName = (LinearLayout) itemView;

            Log.i("debug","MyViewHolder");
        }


        @Override
        public void onClick(View v) {
            //intent = new Intent(, infopage.class);
        }
    }
}

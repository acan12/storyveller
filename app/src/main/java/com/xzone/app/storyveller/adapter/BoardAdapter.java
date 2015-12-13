package com.xzone.app.storyveller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xzone.app.storyveller.R;
import com.xzone.app.storyveller.model.NatureItemDummy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arysuryawan on 9/23/15.
 */
public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    List<NatureItemDummy> mItems;

    public BoardAdapter(){
        super();
        mItems = new ArrayList<NatureItemDummy>();
        NatureItemDummy nature = new NatureItemDummy();
        nature.setName("Escape to Bali 2016");
        nature.setDes("IDR 2.000K");
        nature.setThumbnail(R.drawable.great_barrier_reef);
        mItems.add(nature);

        nature = new NatureItemDummy();
        nature.setName("Escape to Lombok 2016");
        nature.setDes("IDR 800.000");
        nature.setThumbnail(R.drawable.grand_canyon);
        mItems.add(nature);

        nature = new NatureItemDummy();
        nature.setName("Hiking Gn. Lawu");
        nature.setDes("IDR 350.000");
        nature.setThumbnail(R.drawable.baltoro_glacier);
        mItems.add(nature);

        nature = new NatureItemDummy();
        nature.setName("Escape to Bromo 2016");
        nature.setDes("IDR 250.000");
        nature.setThumbnail(R.drawable.ig);
        mItems.add(nature);


        nature = new NatureItemDummy();
        nature.setName("Bandung Village 2015");
        nature.setDes("IDR 1.500K");
        nature.setThumbnail(R.drawable.aurora_borealis);
        mItems.add(nature);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView tripTitle;
        public TextView tripDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tripTitle = (TextView)itemView.findViewById(R.id.trip_title);
            tripDescription = (TextView)itemView.findViewById(R.id.trip_description);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NatureItemDummy nature = mItems.get(position);
        holder.tripTitle.setText(nature.getName());
        holder.tripDescription.setText(nature.getDes());
        holder.imgThumbnail.setImageResource(nature.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}

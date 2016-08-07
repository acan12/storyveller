package core.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xzone.app.storyveller.R;
import core.factory.NatureItemDummy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arysuryawan on 10/10/15.
 */
public class ItienaryAdapter extends RecyclerView.Adapter<ItienaryAdapter.ViewHolder> implements View.OnClickListener{

    List<NatureItemDummy> mItems;

    public ItienaryAdapter(){
        super();
        mItems = new ArrayList<NatureItemDummy>();
        NatureItemDummy nature = new NatureItemDummy();
        nature.setName("The Great Barrier Reef");
        nature.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam.");
        nature.setThumbnail(R.drawable.great_barrier_reef);
        mItems.add(nature);

        nature = new NatureItemDummy();
        nature.setName("Grand Canyon");
        nature.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua.");
        nature.setThumbnail(R.drawable.grand_canyon);
        mItems.add(nature);

        nature = new NatureItemDummy();
        nature.setName("Baltoro Glacier");
        nature.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis.");
        nature.setThumbnail(R.drawable.baltoro_glacier);
        mItems.add(nature);

        nature = new NatureItemDummy();
        nature.setName("Iguazu Falls");
        nature.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam.");
        nature.setThumbnail(R.drawable.ig);
        mItems.add(nature);


        nature = new NatureItemDummy();
        nature.setName("Aurora Borealis");
        nature.setDes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud.");
        nature.setThumbnail(R.drawable.aurora_borealis);
        mItems.add(nature);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_itienary_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NatureItemDummy nature = mItems.get(position);
        holder.infoTitle.setText(nature.getName());
        holder.infoDescription.setText(nature.getDes());
//        holder.imgThumbnail.setImageResource(nature.getThumbnail());

        // cut line on top list
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.verticalLine.getLayoutParams();
        if(position == 0){
            params.setMargins(params.leftMargin, 20, 0, 0);
            holder.verticalLine.setLayoutParams(params);
        }
        if(position == mItems.size()-1){
            params.setMargins(params.leftMargin, params.topMargin, 0, 370);
            holder.verticalLine.setLayoutParams(params);
        }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onClick(View v) {
        Log.d("onClick ", "----- clicked ---");
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView infoTitle;
        public TextView infoDescription;
        public View verticalLine;

        public ViewHolder(View itemView) {
            super(itemView);
//            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            infoTitle = (TextView)itemView.findViewById(R.id.item_title);
            infoDescription = (TextView)itemView.findViewById(R.id.item_description);
            verticalLine = (View) itemView.findViewById(R.id.vertical_line);
        }
    }
}

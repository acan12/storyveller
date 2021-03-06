package core.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xzone.app.storyveller.R;
import core.factory.NavDrawerItemDummy;

import java.util.Collections;
import java.util.List;

/**
 * Created by arysuryawan on 9/18/15.
 */
public class AdapterNavigationDrawer extends RecyclerView.Adapter<AdapterNavigationDrawer.MyViewHolder> {

    List<NavDrawerItemDummy> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public AdapterNavigationDrawer(Context context, List<NavDrawerItemDummy> data){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItemDummy current = data.get(position);
        holder.title.setText(current.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyViewHolder(View itemView){
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}

package br.ufc.quixada.myapplicationnn.recycles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.R;

public class ChildRecyclerViewAdapter extends RecyclerView.Adapter<ChildRecyclerViewAdapter.MyViewHolder> {
    public ArrayList<ChildModel> childModelArrayList;
    public ItemClick itemClick;
    Context cxt;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView txt;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgViewCRI1);
            txt = itemView.findViewById(R.id.txtCRI1);
        }
    }

    public ChildRecyclerViewAdapter(ArrayList<ChildModel> arrayList, Context mContext, ItemClick itemClick1) {
        this.cxt = mContext;
        this.childModelArrayList = arrayList;
        this.itemClick = itemClick1;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_recyclerview_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ChildModel currentItem = childModelArrayList.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.txt.setText(currentItem.getTexto1());
        holder.itemView.setOnClickListener(view -> {
            itemClick.onItemClick(childModelArrayList.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return childModelArrayList.size();
    }

    public interface ItemClick{
        void onItemClick(ChildModel childModel);

    }
}

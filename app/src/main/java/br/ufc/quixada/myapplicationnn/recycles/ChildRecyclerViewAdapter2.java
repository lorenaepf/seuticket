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

public class ChildRecyclerViewAdapter2 extends RecyclerView.Adapter<ChildRecyclerViewAdapter2.MyViewHolder> {
    public ArrayList<ChildModel2> childModelArrayList;
    Context cxt;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView txt;
        public TextView subtxt;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageEDRI2);
            txt = itemView.findViewById(R.id.nomeEvento);
            subtxt = itemView.findViewById(R.id.dataEvento);
        }
    }

    public ChildRecyclerViewAdapter2(ArrayList<ChildModel2> arrayList, Context mContext) {
        this.cxt = mContext;
        this.childModelArrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_recyclerview_items2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ChildModel2 currentItem = childModelArrayList.get(position);
        holder.imageView.setImageResource(currentItem.getImageview1());
        holder.txt.setText(currentItem.getText1());
        holder.subtxt.setText(currentItem.getText2());

    }

    @Override
    public int getItemCount() {
        return childModelArrayList.size();
    }
}

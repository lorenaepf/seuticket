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

public class ChildRecyclerViewAdapter3 extends RecyclerView.Adapter<ChildRecyclerViewAdapter3.MyViewHolder> {
    public ArrayList<ChildModel3> childModelArrayList;
    Context cxt;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView txt;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgView1);
            txt = itemView.findViewById(R.id.estado);
        }
    }

    public ChildRecyclerViewAdapter3(ArrayList<ChildModel3> arrayList, Context mContext) {
        this.cxt = mContext;
        this.childModelArrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_recyclerview_items3, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ChildModel3 currentItem = childModelArrayList.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.txt.setText(currentItem.getTexto1());

    }

    @Override
    public int getItemCount() {
        return childModelArrayList.size();
    }
}

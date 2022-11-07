package br.ufc.quixada.myapplicationnn.recycles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.R;

public class ParentRecyclerViewAdapter extends RecyclerView.Adapter<ParentRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<ParentModel> parentModelArrayList;

    public Context cxt;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView category;
        public RecyclerView childRecyclerView;

        public MyViewHolder(View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.tipoLista);
            childRecyclerView = itemView.findViewById(R.id.Child_RV);
        }
    }

    public ParentRecyclerViewAdapter(ArrayList<ParentModel> exampleList, Context context) {
        this.parentModelArrayList = exampleList;
        this.cxt = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_recyclerview_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return parentModelArrayList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ParentModel currentItem = parentModelArrayList.get(position);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(cxt, LinearLayoutManager.HORIZONTAL, false);
        holder.childRecyclerView.setLayoutManager(layoutManager);
        holder.childRecyclerView.setHasFixedSize(true);


        holder.category.setText(currentItem.listaEvento());
        ArrayList<ChildModel> arrayList = new ArrayList<>();
        ArrayList<ChildModel2> arrayList1 = new ArrayList<>();
        ArrayList<ChildModel3> arrayList2 = new ArrayList<>();

        // added the first child row
        if (parentModelArrayList.get(position).listaEvento().equals("O que você procura?")) {
            arrayList.add(new ChildModel(R.drawable.festa1,"Festivais"));
            arrayList.add(new ChildModel(R.drawable.palestra,"Palestras"));
            arrayList.add(new ChildModel( R.drawable.infantil,"Infantil"));
            arrayList.add(new ChildModel( R.drawable.teatro,"Teatros"));
            arrayList.add(new ChildModel(R.drawable.festa3,"Ano novo"));
            ChildRecyclerViewAdapter childRecyclerViewAdapter = new ChildRecyclerViewAdapter(arrayList,holder.childRecyclerView.getContext());
            holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);

        }

        // added in second child row
        if (parentModelArrayList.get(position).listaEvento().equals("Eventos em destaque")) {
            arrayList1.add(new ChildModel2(R.drawable.lolla,"Lollapalooza","Sab, 13 OUT - 16H"));
            arrayList1.add(new ChildModel2(R.drawable.rir,"Rock in Rio","Sab, 09 JAN - 20H"));
            arrayList1.add(new ChildModel2(R.drawable.fortal,"Fortal","Sex, 29 MAR - 19H"));

            ChildRecyclerViewAdapter2 childRecyclerViewAdapter = new ChildRecyclerViewAdapter2(arrayList1,holder.childRecyclerView.getContext());
            holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);
        }

        // added in third child row
        if(parentModelArrayList.get(position).listaEvento().equals("O melhor de cada lugar")) {
            arrayList2.add(new ChildModel3(R.drawable.rj,"Rio de Janeiro"));
            arrayList2.add(new ChildModel3(R.drawable.sp,"São Paulo"));
            arrayList2.add(new ChildModel3( R.drawable.rs,"Rio Grande do Sul"));
            arrayList2.add(new ChildModel3( R.drawable.bahia,"Bahia"));
            arrayList2.add(new ChildModel3( R.drawable.sc,"Santa Catarina"));

            ChildRecyclerViewAdapter3 childRecyclerViewAdapter = new ChildRecyclerViewAdapter3(arrayList2,holder.childRecyclerView.getContext());
            holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);
        }



    }
}
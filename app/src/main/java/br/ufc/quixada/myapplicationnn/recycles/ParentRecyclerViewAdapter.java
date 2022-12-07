package br.ufc.quixada.myapplicationnn.recycles;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.CrudUser.Cadastro;
import br.ufc.quixada.myapplicationnn.MainActivity;
import br.ufc.quixada.myapplicationnn.MainActivityHome;
import br.ufc.quixada.myapplicationnn.R;
import br.ufc.quixada.myapplicationnn.TelaEventos;
import br.ufc.quixada.myapplicationnn.fragments.Home;

public class ParentRecyclerViewAdapter extends RecyclerView.Adapter<ParentRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<ParentModel> parentModelArrayList;
    RecyclerViewClickListener listener;

    public Context cxt;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView category;
        public RecyclerView childRecyclerView;

        public MyViewHolder(View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.tipoLista);
            childRecyclerView = itemView.findViewById(R.id.Child_RV);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(itemView,getAdapterPosition());
        }
    }

    public ParentRecyclerViewAdapter(ArrayList<ParentModel> exampleList, Context context,RecyclerViewClickListener listener) {
        this.parentModelArrayList = exampleList;
        this.cxt = context;
        this.listener = listener;

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
            ChildRecyclerViewAdapter childRecyclerViewAdapter = new ChildRecyclerViewAdapter(arrayList, holder.childRecyclerView.getContext(), new ChildRecyclerViewAdapter.ItemClick() {
                @Override
                public void onItemClick(ChildModel childModel) {
                    showToast(childModel.getTexto1() + " clicado!");
                    Intent intent = new Intent(cxt, TelaEventos.class);
                    cxt.startActivity(intent);
                }
            });

            holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);

        }


        // added in second child row
        if (parentModelArrayList.get(position).listaEvento().equals("Eventos em destaque")) {
            arrayList1.add(new ChildModel2(R.drawable.lolla,"Lollapalooza","Sab, 13 OUT - 16H"));
            arrayList1.add(new ChildModel2(R.drawable.rir,"Rock in Rio","Sab, 09 JAN - 20H"));
            arrayList1.add(new ChildModel2(R.drawable.fortal,"Fortal","Sex, 29 MAR - 19H"));

            ChildRecyclerViewAdapter2 childRecyclerViewAdapter = new ChildRecyclerViewAdapter2(arrayList1, holder.childRecyclerView.getContext(), new ChildRecyclerViewAdapter2.ItemClick() {
                @Override
                public void onItemClick(ChildModel2 childModel) {
                    showToast(childModel.getText1() + " clicado!");
                    Intent intent = new Intent(cxt, TelaEventos.class);
//                    intent.putExtra("Evento",childModel.getText1());
                    cxt.startActivity(intent);
                }
            });
            holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);
        }

        // added in third child row
        if(parentModelArrayList.get(position).listaEvento().equals("O melhor de cada lugar")) {
            arrayList2.add(new ChildModel3(R.drawable.rj,"Rio de Janeiro"));
            arrayList2.add(new ChildModel3(R.drawable.sp,"São Paulo"));
            arrayList2.add(new ChildModel3( R.drawable.rs,"Rio Grande do Sul"));
            arrayList2.add(new ChildModel3( R.drawable.bahia,"Bahia"));
            arrayList2.add(new ChildModel3( R.drawable.sc,"Santa Catarina"));

            ChildRecyclerViewAdapter3 childRecyclerViewAdapter = new ChildRecyclerViewAdapter3(arrayList2, holder.childRecyclerView.getContext(), new ChildRecyclerViewAdapter3.ItemClick() {
                @Override
                public void onItemClick(ChildModel3 childModel3) {
                    showToast(childModel3.getTexto1() + " clicado!");
                    Intent intent = new Intent(cxt, TelaEventos.class);
                    cxt.startActivity(intent);

                }
            });
            holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);
        }

    }
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);

    }

    public void showToast(String message){
        Toast.makeText(cxt, message, Toast.LENGTH_SHORT).show();

    }
}

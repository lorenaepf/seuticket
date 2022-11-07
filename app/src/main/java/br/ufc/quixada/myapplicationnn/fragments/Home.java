package br.ufc.quixada.myapplicationnn.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.R;
import br.ufc.quixada.myapplicationnn.recycles.ParentModel;
import br.ufc.quixada.myapplicationnn.recycles.ParentRecyclerViewAdapter;


public class Home extends Fragment {
    private RecyclerView parentRecyclerView;
    private RecyclerView.Adapter ParentAdapter;
    ArrayList<ParentModel> parentModelArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager parentLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        inf(v);


        return v;
    }

    public void inf(View v){
        parentModelArrayList.add(new ParentModel("O que você procura?"));
        parentModelArrayList.add(new ParentModel("Eventos em destaque"));
        parentModelArrayList.add(new ParentModel("O melhor de cada lugar"));
        parentRecyclerView = v.findViewById(R.id.Parent_recyclerView);
        parentRecyclerView.setHasFixedSize(true);
        parentLayoutManager = new LinearLayoutManager(v.getContext());
        ParentAdapter = new ParentRecyclerViewAdapter(parentModelArrayList, v.getContext());
        parentRecyclerView.setLayoutManager(parentLayoutManager);
        parentRecyclerView.setAdapter(ParentAdapter);
        ParentAdapter.notifyDataSetChanged();
    }


}
package br.ufc.quixada.myapplicationnn.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.CrudEvento.CadEvento;
import br.ufc.quixada.myapplicationnn.R;
import br.ufc.quixada.myapplicationnn.TelaEventos;
import br.ufc.quixada.myapplicationnn.recycles.ParentModel;
import br.ufc.quixada.myapplicationnn.recycles.ParentRecyclerViewAdapter;


public class Home extends Fragment {
    private RecyclerView parentRecyclerView;
    private RecyclerView.Adapter ParentAdapter;
    ArrayList<ParentModel> parentModelArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager parentLayoutManager;

    private RecyclerView recyclerView;
    private ParentRecyclerViewAdapter.RecyclerViewClickListener listener;

    private static final String ARG_PARAM1 = "email";
    private static final String ARG_PARAM2 = "senha";
    private static final String ARG_PARAM3 = "nome";

    Button button;

    TextView textView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    public static Home newInstance(String param1, String param2,String param3) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

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

        setOnClickListener();
        parentRecyclerView = v.findViewById(R.id.Parent_recyclerView);
        parentRecyclerView.setHasFixedSize(true);
        parentLayoutManager = new LinearLayoutManager(v.getContext());
        ParentAdapter = new ParentRecyclerViewAdapter(parentModelArrayList, v.getContext(),listener);
        parentRecyclerView.setLayoutManager(parentLayoutManager);
        parentRecyclerView.setAdapter(ParentAdapter);
        ParentAdapter.notifyDataSetChanged();
    }
    public void setOnClickListener(){
        listener = new ParentRecyclerViewAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getActivity(), TelaEventos.class);
                startActivity(intent);
            }
        };
    }

}
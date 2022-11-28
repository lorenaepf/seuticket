package br.ufc.quixada.myapplicationnn.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.DAO.DAOFavoritos;
import br.ufc.quixada.myapplicationnn.Entidades.Evento;
import br.ufc.quixada.myapplicationnn.R;
import br.ufc.quixada.myapplicationnn.TelaEventos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Favoritos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Favoritos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    DAOFavoritos daoFavoritos = new DAOFavoritos();
    ArrayList<Evento> favoritos = new ArrayList<>();
    ListView listFavoritos;
    ArrayAdapter adapter;


    // TODO: Rename and change types of parameters
    private Evento mParam1;

    public Favoritos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment Favoritos.
     */
    // TODO: Rename and change types and number of parameters
    public static Favoritos newInstance(Evento evento) {
        Favoritos fragment = new Favoritos();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, evento);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Evento)getArguments().getSerializable(ARG_PARAM1);
            favoritos.add(mParam1);
            daoFavoritos.addFavoritos(mParam1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favoritos, container, false);
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,favoritos);
        listFavoritos = v.findViewById(R.id.listFav);
        listFavoritos.setAdapter(adapter);

        return v;
    }
}
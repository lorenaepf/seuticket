package br.ufc.quixada.myapplicationnn.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.DAO.DAOFavoritos;
import br.ufc.quixada.myapplicationnn.Entidades.Evento;
import br.ufc.quixada.myapplicationnn.R;

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
    ArrayList<String> lau = new ArrayList<>();

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String idUser = FirebaseAuth.getInstance().getCurrentUser().getUid();

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
        listFavoritos = v.findViewById(R.id.listFav);

        recuperaDados();

        return v;
    }

    private void recuperaDados() {
        Evento scrr = new Evento();

        db.collection("usuarios").document(idUser).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value != null){
                    String teste[] = value.get("favoritos").toString().split(",");
                    for(String p : teste){
                        if(p.contains("nomeEvento")){
                            scrr.setNomeEvento(p.replaceAll("nomeEvento=",""));
                            favoritos.add(scrr);
                        }
                    }
                    atualizaAdapter();
                }
            }
//a@gmail.com
        });

    }
    public void atualizaAdapter(){
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,favoritos);
        listFavoritos.setAdapter(adapter);

    }
}
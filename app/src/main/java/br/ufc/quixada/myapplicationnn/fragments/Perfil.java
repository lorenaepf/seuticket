package br.ufc.quixada.myapplicationnn.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.CrudEvento.CadEvento;
import br.ufc.quixada.myapplicationnn.CrudUser.Cadastro;
import br.ufc.quixada.myapplicationnn.CrudUser.EditProfile;
import br.ufc.quixada.myapplicationnn.CrudUser.EditarSenha;
import br.ufc.quixada.myapplicationnn.DAO.DAOEvento;
import br.ufc.quixada.myapplicationnn.DAO.DAOUsuario;
import br.ufc.quixada.myapplicationnn.Entidades.Evento;
import br.ufc.quixada.myapplicationnn.Entidades.Usuario;
import br.ufc.quixada.myapplicationnn.MainActivity;
import br.ufc.quixada.myapplicationnn.Maps;
import br.ufc.quixada.myapplicationnn.R;
import br.ufc.quixada.myapplicationnn.TelaEventos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Perfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Perfil extends Fragment implements Serializable{

    TextView textEmail,textNome,textSenha;
    TextView btnEditUser,btnCadEvento,btnEditEvento,btnDeleteUser,sair;
    ImageView position;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "user";


    // TODO: Rename and change types of parameters
    private Usuario mParam1;
    DAOUsuario daoUsuario = new DAOUsuario();
    ArrayList<Usuario> usuarios = new ArrayList<>();
    int id;

    static FirebaseAuth mAuth;
    static FirebaseFirestore db;

    public Perfil(){

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *er 2.
     * @return A new instance of fragment Perfil.
     */
    // TODO: Rename and change types and number of parameters
    public static Perfil newInstance(FirebaseFirestore db,FirebaseAuth mAuth,Usuario usuario) {
        Perfil fragment = new Perfil();
        Bundle args = new Bundle();
        Perfil.mAuth = mAuth;
        Perfil.db = db;

        args.putSerializable(ARG_PARAM1, usuario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Usuario) getArguments().getSerializable(ARG_PARAM1);
            daoUsuario.addUsuario(mParam1);
            usuarios.add(mParam1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        btnEditUser = v.findViewById(R.id.edtDU);
        btnCadEvento = v.findViewById(R.id.edtCadEvento);
        btnEditEvento = v.findViewById(R.id.btnEditEvento);
        //btnDeleteUser = v.findViewById(R.id.btnDeleteUser);

        textEmail = v.findViewById(R.id.emailPerfil);
        textNome = v.findViewById(R.id.nomePerfil);
        textSenha = v.findViewById(R.id.texTelaSenha);
        sair = v.findViewById(R.id.logout);
        position = v.findViewById(R.id.gps);


        textEmail.setText(mParam1.getEmail());
        textNome.setText(mParam1.getNome());

        mudarSenha();

        editUser();
        cadEvento();
        editEvento();
        gps();
        logOut();
       // deleteUser();

        return v;
    }

    private void gps() {
        position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Maps.class);
                intent.putExtra("latitude",mParam1.getLatitude());
                intent.putExtra("longitude",mParam1.getLongitude());
                startActivity(intent);
            }
        });
    }


    public void editUser(){//editar user
        btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < usuarios.size();i++){
                    if(usuarios.get(i).getNome().equals(mParam1.getNome())){
                        id = i;

                        Intent intent = new Intent(getActivity(), EditProfile.class);
                        intent.putExtra("nome",mParam1.getNome());
                        intent.putExtra("email",mParam1.getEmail());

                        startActivityForResult(intent,201);
                    }
                }

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 201){
            if(resultCode == getActivity().RESULT_OK){
                mParam1.setNome(data.getStringExtra("nomeModificado"));

                usuarios.set(id,mParam1);
                daoUsuario.setUsuarios(usuarios);

                db.collection("usuarios").document(mParam1.getuId()).update("nome",mParam1.getNome())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getActivity(), "Atualizado com sucesso",Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(),"Falha ao atualizar",Toast.LENGTH_SHORT).show();
                            }
                        });

                textNome.setText(mParam1.getNome());

            }

        }
        if(requestCode == 202){
            if(resultCode == getActivity().RESULT_OK){

            }
        }
        if(requestCode == 501){
            if(resultCode == getActivity().RESULT_OK){
            }
        }
        if(requestCode == 601){
            if(resultCode == getActivity().RESULT_OK){

            }
        }
    }
    public void mudarSenha(){
        textSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < usuarios.size();i++){
                    if(usuarios.get(i).getNome().equals(mParam1.getNome())){
                        id = i;

                        Intent intent = new Intent(getActivity(), EditarSenha.class);
//                        intent.putExtra("senha",mParam1.getSenha());

                        startActivityForResult(intent,202);

                    }
                }

            }
        });
    }

    public void cadEvento() {
        if(mParam1.getEmail().equals("a@gmail.com")){
            btnCadEvento.setVisibility(View.VISIBLE);
        }else{
            btnCadEvento.setVisibility(View.INVISIBLE);
        }

        btnCadEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CadEvento.class);
//                intent.putExtra("adm",mParam1);
                startActivityForResult(intent,501);
            }
        });
    }


    public void editEvento(){
        if(mParam1.getEmail().equals("a@gmail.com")){
            btnEditEvento.setVisibility(View.VISIBLE);
            btnEditEvento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), TelaEventos.class);
                    startActivityForResult(intent,601);
                }
            });

        }else{
            btnEditEvento.setVisibility(View.INVISIBLE);
        }


    }
    public void logOut(){
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                if(firebaseUser != null){
                    mAuth.signOut();
                    Toast.makeText(getActivity(),"Deslogado com sucesso",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(),"Ningu??m logado",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
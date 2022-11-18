package br.ufc.quixada.myapplicationnn.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.CrudEvento.CadEvento;
import br.ufc.quixada.myapplicationnn.CrudUser.Cadastro;
import br.ufc.quixada.myapplicationnn.CrudUser.EditProfile;
import br.ufc.quixada.myapplicationnn.CrudUser.EditarSenha;
import br.ufc.quixada.myapplicationnn.DAO.DAOEvento;
import br.ufc.quixada.myapplicationnn.DAO.DAOUsuario;
import br.ufc.quixada.myapplicationnn.Entidades.Evento;
import br.ufc.quixada.myapplicationnn.Entidades.Usuario;
import br.ufc.quixada.myapplicationnn.R;
import br.ufc.quixada.myapplicationnn.TelaEventos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Perfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Perfil extends Fragment {

    TextView textEmail,textNome,textSenha;
    TextView btnEditUser,btnCadEvento,btnEditEvento,btnDeleteUser;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "user";


    // TODO: Rename and change types of parameters
    private Usuario mParam1;
    DAOEvento daoEvento = new DAOEvento();
    DAOUsuario daoUsuario = new DAOUsuario();
    ArrayList<Usuario> usuarios = new ArrayList<>();
    int id;

    public Perfil(){

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *er 2.
     * @return A new instance of fragment Perfil.
     */
    // TODO: Rename and change types and number of parameters
    public static Perfil newInstance(Usuario usuario) {
        Perfil fragment = new Perfil();
        Bundle args = new Bundle();
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


        textEmail.setText(mParam1.getEmail());
        textNome.setText(mParam1.getNome());

        mudarSenha();

        transfere();
        cadEvento();
        editEvento();
       // deleteUser();

        return v;
    }


    public void transfere(){//editar user
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
                mParam1.setEmail(data.getStringExtra("emailModificado"));

                usuarios.set(id,mParam1);
                daoUsuario.setUsuarios(usuarios);

                textEmail.setText(mParam1.getEmail());
                textNome.setText(mParam1.getNome());

            }

        }
        if(requestCode == 202){
            if(resultCode == getActivity().RESULT_OK){
                mParam1.setSenha(data.getStringExtra("senhaModificada"));

                usuarios.set(id,mParam1);
                daoUsuario.setUsuarios(usuarios);

                System.out.println("amongus: "+usuarios.get(id).getSenha());
            }
        }
        if(requestCode == 501){
            if(resultCode == getActivity().RESULT_OK){
                daoEvento.setEventos((ArrayList<Evento>) data.getSerializableExtra("DAO"));
            }
        }
        if(requestCode == 601){
            if(resultCode == getActivity().RESULT_OK){
                daoEvento.setEventos((ArrayList<Evento>) data.getSerializableExtra("DAO"));
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
                        intent.putExtra("senha",mParam1.getSenha());

                        startActivityForResult(intent,202);

                    }
                }

            }
        });
    }

    public void cadEvento() {
        if(mParam1.getEmail().equals("adm")){
            btnCadEvento.setVisibility(View.VISIBLE);
        }else{
            btnCadEvento.setVisibility(View.INVISIBLE);
        }

        btnCadEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CadEvento.class);
                intent.putExtra("original",daoEvento);
                intent.putExtra("adm",mParam1);
                startActivityForResult(intent,501);
            }
        });
    }


    public void editEvento(){
        if(mParam1.getEmail().equals("adm")){
            btnEditEvento.setVisibility(View.VISIBLE);
            System.out.println("adm3"+mParam1);
            btnEditEvento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), TelaEventos.class);
                    intent.putExtra("adm",mParam1);
                    intent.putExtra("DAOEvento",daoEvento);
                    startActivityForResult(intent,601);
                }
            });

        }else{
            btnEditEvento.setVisibility(View.INVISIBLE);
        }


    }
    public void deleteUser(){
        btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mParam1 = null;
                textEmail.setText("null");
                textNome.setText("null");

                Intent intent = new Intent(getActivity(), Cadastro.class);
                startActivity(intent);

            }
        });
        //mandar para um outro fragment ou pedindo para criar
    }
}
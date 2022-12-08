package br.ufc.quixada.myapplicationnn.fragments;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import br.ufc.quixada.myapplicationnn.Entidades.Usuario;
import br.ufc.quixada.myapplicationnn.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Carteira#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Carteira extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Usuario mParam1;
    TextView addValor, valor;
    ImageView credit,debit,eye;
    float saldoAtual = 0;
    static FirebaseFirestore db;

    public Carteira() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Carteira newInstance(FirebaseFirestore db,Usuario usuario) {
        Carteira fragment = new Carteira();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, usuario);
        Carteira.db = db;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Usuario) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         View v = inflater.inflate(R.layout.fragment_carteira, container, false);

         addValor = v.findViewById(R.id.addSaldo);
         credit = v.findViewById(R.id.credito);
         debit = v.findViewById(R.id.debito);
         eye = v.findViewById(R.id.olho);
         valor = v.findViewById(R.id.value);
         String saldo = String.valueOf(mParam1.getConta().getSaldo());
         valor.setText(saldo);

         adicionaSaldo();
         escondeSaldo();

        return v;
    }
    boolean visible = true;
    private void escondeSaldo() {
        eye.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(visible){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        valor.setForeground(new ColorDrawable(getResources().getColor(R.color.black)));
                    }
                    eye.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    visible = false;
                }else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        valor.setForeground(null);
                    }
                    visible = true;
                    eye.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
                }

            }
        });
    }

    public void adicionaSaldo(){
            credit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mParam1.getConta().getSaldo() > 0.0){//se houver saldo na conta
                        saldoAtual = mParam1.getConta().getSaldo();
                        saldoAtual += Integer.parseInt(addValor.getText().toString());

                        String saldo = String.valueOf(saldoAtual);

                        valor.setText(saldo);
                        db.collection("usuarios").document(mParam1.getuId()).update("saldo",valor.getText().toString());
                        addValor.setText("");
                    }else if(mParam1.getConta().getSaldo() == 0){//se não houver nada na conta
                            valor.setText(addValor.getText());
                            db.collection("usuarios").document(mParam1.getuId()).update("saldo",valor.getText().toString());
                            addValor.setText("");
                    }

                }
            });
            debit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mParam1.getConta().getSaldo() > 0.0){//se houver saldo na conta
                        saldoAtual = mParam1.getConta().getSaldo();
                        saldoAtual += Integer.parseInt(addValor.getText().toString());

                        String saldo = String.valueOf(saldoAtual);

                        valor.setText(saldo);
                        db.collection("usuarios").document(mParam1.getuId()).update("saldo",valor.getText().toString());
                        addValor.setText("");
                    }else if(mParam1.getConta().getSaldo() == 0){//se não houver nada na conta
                        valor.setText(addValor.getText());
                        db.collection("usuarios").document(mParam1.getuId()).update("saldo",valor.getText().toString());
                        addValor.setText("");
                    }

                }
            });

    }
}
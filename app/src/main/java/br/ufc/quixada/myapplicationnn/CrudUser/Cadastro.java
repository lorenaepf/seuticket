package br.ufc.quixada.myapplicationnn.CrudUser;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import br.ufc.quixada.myapplicationnn.Entidades.Usuario;
import br.ufc.quixada.myapplicationnn.Interfaces.UsuarioDB;
import br.ufc.quixada.myapplicationnn.LoginST;
import br.ufc.quixada.myapplicationnn.R;

public class Cadastro extends AppCompatActivity implements UsuarioDB {
    EditText email, senha,nome;
    TextView Cad;
    String nomeText,emailTxt,senhaTxt;
    String usuarioID;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        iniciaComponente();

    }

    public void iniciaComponente(){
        email = findViewById(R.id.logEmail);
        senha = findViewById(R.id.logSenha);
        nome = findViewById(R.id.edtCadNome);
        Cad = findViewById(R.id.textCad);

        Cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                emailTxt = email.getText().toString();
                senhaTxt = senha.getText().toString();
                nomeText = nome.getText().toString();

                if(emailTxt.isEmpty() || senhaTxt.isEmpty() || nomeText.isEmpty()){
                    Toast.makeText(Cadastro.this, "Preencha todos os campos!",Toast.LENGTH_SHORT).show();
                }else{
                    criaUsuarioFB();
                }

            }
        });
    }

    @Override
    public void criaUsuarioFB() {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailTxt,senhaTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    salvaDadosUsuario();

                    Intent intent = new Intent(Cadastro.this, LoginST.class);
                    intent.putExtra("nomeUser",nomeText);
                    intent.putExtra("id",usuarioID);
                    Toast.makeText(Cadastro.this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

                    startActivity(intent);
                }else{
                    try {
                        throw task.getException();

                    }catch(FirebaseAuthWeakPasswordException e) {
                        Toast.makeText(Cadastro.this, "Insira uma senha de no mínimo 6 caracteres", Toast.LENGTH_SHORT).show();
                    }catch(FirebaseAuthUserCollisionException e) {
                        Toast.makeText(Cadastro.this, "Email já cadastrado", Toast.LENGTH_SHORT).show();
                    }catch(FirebaseAuthInvalidCredentialsException e){
                        Toast.makeText(Cadastro.this, "Email inválido", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        Toast.makeText(Cadastro.this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void salvaDadosUsuario() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> usuarios = new HashMap<>();
        usuarios.put("nome",nomeText);
        usuarios.put("email",emailTxt);

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("usuarios").document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("db","Sucesso ao salvar os dados");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("db_error","Erro ao salvar os dados: "+e.toString());
                    }
                });

    }

}
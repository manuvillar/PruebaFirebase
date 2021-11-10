package es.iesoretania.pruebafirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import es.iesoretania.pruebafirebase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("Pantalla de Autenticación");

        binding.botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.EditTextEmail.getText().toString().isEmpty()
                        && !binding.EditTextPassword.getText().toString().isEmpty()){
                    FirebaseAuth.getInstance()
                            .createUserWithEmailAndPassword(binding.EditTextEmail.getText().toString(),
                                    binding.EditTextPassword.getText().toString())
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intentHome = new Intent(v.getContext(), HomeActivity.class);
                                        intentHome.putExtra("email", binding.EditTextEmail.getText().toString());
                                        intentHome.putExtra("proveedor", "EMAIL-PASSWORD");

                                        startActivity(intentHome);
                                    } else {
                                        muestraAlerta("Error en la autenticación");
                                    }
                                }
                            });
                }else{
                    muestraAlerta("Introduce valores en todos los campos");
                }
            }
        });

        binding.botonAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.EditTextEmail.getText().toString().isEmpty()
                        && !binding.EditTextPassword.getText().toString().isEmpty()){
                    FirebaseAuth.getInstance()
                            .signInWithEmailAndPassword(binding.EditTextEmail.getText().toString(),
                                    binding.EditTextPassword.getText().toString())
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intentHome = new Intent(v.getContext(), HomeActivity.class);
                                        intentHome.putExtra("email", binding.EditTextEmail.getText().toString());
                                        intentHome.putExtra("proveedor", "EMAIL-PASSWORD");

                                        startActivity(intentHome);
                                    } else {
                                        muestraAlerta("Error de autenticación");
                                    }
                                }
                            });
                }else {
                    muestraAlerta("Introduce valores en todos los campos");
                }
            }
        });
    }

    private void muestraAlerta(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage(s);
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialogo = builder.create();
        dialogo.show();
    }
}
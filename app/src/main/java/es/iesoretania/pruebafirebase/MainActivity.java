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

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    EditText email, contraseña;
    Button botonregistrar, botonacceder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Pantalla de Autenticación");

        email = findViewById(R.id.EditTextEmail);
        contraseña = findViewById(R.id.EditTextPassword);
        botonregistrar = findViewById(R.id.botonRegistrar);
        botonacceder = findViewById(R.id.botonAcceder);

        botonregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email.getText().toString().isEmpty() && !contraseña.getText().toString().isEmpty()){
                    FirebaseAuth.getInstance()
                            .createUserWithEmailAndPassword(email.getText().toString(),contraseña.getText().toString())
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intentHome = new Intent(v.getContext(), HomeActivity.class);
                                        intentHome.putExtra("email", email.getText().toString());
                                        intentHome.putExtra("proveedor", "EMAIL-PASSWORD");

                                        startActivity(intentHome);
                                    } else {
                                        muestraAlerta();
                                    }
                                }
                            });
                }
            }
        });

        botonacceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email.getText().toString().isEmpty() && !contraseña.getText().toString().isEmpty()){
                    FirebaseAuth.getInstance()
                            .signInWithEmailAndPassword(email.getText().toString(),contraseña.getText().toString())
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intentHome = new Intent(v.getContext(), HomeActivity.class);
                                        intentHome.putExtra("email", email.getText().toString());
                                        intentHome.putExtra("proveedor", "EMAIL-PASSWORD");

                                        startActivity(intentHome);
                                    } else {
                                        muestraAlerta();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void muestraAlerta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Se ha producido un error autenticando al usuario");
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialogo = builder.create();
        dialogo.show();
    }
}
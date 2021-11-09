package es.iesoretania.pruebafirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    TextView emailTextView, proveedorTextView;
    Button botonCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        emailTextView = findViewById(R.id.TextViewEmail);
        proveedorTextView = findViewById(R.id.TextViewProveedor);
        botonCerrar = findViewById(R.id.botonCerrar);

        setTitle("Pantalla Home");

        Bundle datos = getIntent().getExtras();
        String mail = datos.getString("email");
        String proveedor = datos.getString("proveedor");

        emailTextView.setText(mail);
        proveedorTextView.setText(proveedor);

        botonCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                onBackPressed();
            }
        });
    }
}
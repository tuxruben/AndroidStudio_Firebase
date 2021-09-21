package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
    DatabaseReference mensajeRef=ref.child("mensaje");
    private TextView tvMensaje;
    private EditText txtEnviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMensaje=(TextView) findViewById(R.id.tvMensaje);
        txtEnviar=(EditText)findViewById(R.id.txtEnviar);
        mensajeRef.setValue("Hola Mundo2");
    }
    @Override
    protected void onStart(){
        super.onStart();
        mensajeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                tvMensaje.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    public void modificar(View view){
        String mensaje=txtEnviar.getText().toString();
        mensajeRef.setValue(mensaje);
    }
}

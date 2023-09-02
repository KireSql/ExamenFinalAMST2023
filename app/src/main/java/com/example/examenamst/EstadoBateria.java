package com.example.examenamst;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EstadoBateria extends AppCompatActivity {
    DatabaseReference db_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_bateria);
        db_reference = FirebaseDatabase.getInstance().getReference().child("Sensor").child("Data0");
        leerRegistros();
    }

    public void leerRegistros(){
        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    obtenerBateria(snapshot);

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println(error.toException());
            }
        });
    }


    public void obtenerBateria(DataSnapshot snapshot){


        // Crea un objeto LayoutParams que corresponda al tipo de contenedor que estás utilizando (en este caso, LinearLayout)
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Ancho
                LinearLayout.LayoutParams.WRAP_CONTENT  // Alto
        );

        // Establece el peso deseado (en este caso, 1)
        layoutParams.weight = 1;





        //LinearLayout contenedorData = (LinearLayout) findViewById(R.id.ContenedorData);


        String bateriaVal = String.valueOf(snapshot.child("uplink_message").child("decoded_payload").child("bateria").getValue());

        LinearLayout filaData = new LinearLayout(getApplicationContext());


        TextView bateria = findViewById(R.id.txtBateria);
        bateria.setText(bateriaVal+" %");
        bateria.setLayoutParams(layoutParams);
        bateria.setWidth(24);

        //filaData.addView(bateria);

        //contenedorData.addView(filaData);



    }




}
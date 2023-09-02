package com.example.examenamst;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatosSensados extends AppCompatActivity {
    DatabaseReference db_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_sensados);
        db_reference = FirebaseDatabase.getInstance().getReference().child("Sensor").child("Data0");
        leerRegistros();
    }

    public void leerRegistros(){
        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mostrarRegistrosPorPantalla(snapshot);

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println(error.toException());
            }
        });
    }


    public void mostrarRegistrosPorPantalla(DataSnapshot snapshot){


        // Crea un objeto LayoutParams que corresponda al tipo de contenedor que estás utilizando (en este caso, LinearLayout)
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, // Ancho
                        LinearLayout.LayoutParams.WRAP_CONTENT  // Alto
                );

        // Establece el peso deseado (en este caso, 1)
                layoutParams.weight = 1;





        LinearLayout contenedorData = (LinearLayout) findViewById(R.id.ContenedorData);

        String fechaVal = String.valueOf(snapshot.child("received_at").getValue());
        String sensorVal = String.valueOf(snapshot.child("uplink_message").child("decoded_payload").child("sensor").getValue());
        String incendioVal = String.valueOf(snapshot.child("uplink_message").child("decoded_payload").child("incendio").getValue());


        //Separar Fecha y hora
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputTimeFormat = new SimpleDateFormat("HH:mm:ss");



        try {
            Date date = inputFormat.parse(fechaVal);

            String formattedDate = outputDateFormat.format(date);
            String formattedTime = outputTimeFormat.format(date);

            LinearLayout filaData = new LinearLayout(getApplicationContext());

            TextView fecha = new TextView(getApplicationContext());
            fecha.setText(formattedDate);
            fecha.setLayoutParams(layoutParams);
            fecha.setWidth(24);

            filaData.addView(fecha);

            TextView hora = new TextView(getApplicationContext());
            hora.setText(formattedTime);
            hora.setLayoutParams(layoutParams);
            hora.setWidth(76);

            filaData.addView(hora);

            TextView sensor = new TextView(getApplicationContext());
            sensor.setText(sensorVal);
            sensor.setLayoutParams(layoutParams);
            sensor.setWidth(56);

            filaData.addView(sensor);

            TextView incend = new TextView(getApplicationContext());
            incend.setText(incendioVal);
            incend.setLayoutParams(layoutParams);
            incend.setWidth(88);

            filaData.addView(incend);

            contenedorData.addView(filaData);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }




}


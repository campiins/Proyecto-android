package com.marcamp1.proyectoBiometria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    LogicaDelNegocio logica = new LogicaDelNegocio();

    Button botonGuardar;
    Button botonObtener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Guardar Medicion ---------------------------------------------------------------------------
        botonGuardar = findViewById(R.id.btnGuardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // generar datos simulados
                long fecha = System.currentTimeMillis();
                Date date = new Date(fecha+3600000);
                Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
                String fechaActual = format.format(date);
                final int tMin = 18;
                final int tMax = 32;
                int temperatura = new Random().nextInt((tMax - tMin) + 1) + tMin;
                final int gMin = 300;
                final int gMax = 600;
                int valorco2 = new Random().nextInt((gMax - gMin) + 1) + gMin;

                Medicion medicion = new Medicion(valorco2, temperatura, fechaActual);

                logica.guardarMedicion(medicion);
            }
        });

        // Obtener Mediciones -----------------------------------------------------------------------------
        botonObtener = findViewById(R.id.btnObtener);
        botonObtener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logica.obtenerTodasLasMediciones();
                ArrayList<Medicion> ultimasMediciones = logica.obtenerUltimasMediciones(2);
                Log.d("Lista: ", String.valueOf(ultimasMediciones));
            }
        });
    } // onCreate()

} // activity
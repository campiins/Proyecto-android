package com.marcamp1.proyectoBiometria;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.UUID;

public class LogicaDelNegocio {

    // constructor
    public LogicaDelNegocio(){};

    // Medicion -> guardarMedicion()
    public void guardarMedicion(Medicion medicion) {

        // Base de datos
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String nombreFichero = UUID.randomUUID().toString();
        db.collection("Medicion").document(nombreFichero).set(medicion).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("Datos", "Documento creado/actualizado");
                } else {
                    Log.e("Error | ", "No se pudo crear el documento");
                }
            }
        });

    } // ()

    //obtenerTodasLasMediciones() -> [Medicion] | Ahora mismo no devuelve nada, solo muestra el resultado por el Logcat
    public void obtenerTodasLasMediciones() {

        // Base de datos
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Medicion").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete (@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("Datos: ", String.valueOf(document.getData()));
                    }
                } else {
                    Log.e("Error | ", "Error obteniendo documentos: ", task.getException());
                }
            }
        });
    }

    // cuantas: N -> obtenerUltimasMediciones() -> [Medicion]
    public ArrayList<Medicion> obtenerUltimasMediciones(Integer cuantas) {
        ArrayList<Medicion> lista = new ArrayList<Medicion>();

        // Base de datos
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Medicion").limit(cuantas).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete (@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("Datos: ", String.valueOf(document.getData()));
                        if (document.exists()) {
                            Medicion nueva = document.toObject(Medicion.class);
                            lista.add(nueva);
                        } else {
                            Log.e("Error | ", "El documento no existe");
                        }
                    }
                } else {
                    Log.e("Error | ", "Error obteniendo documentos: ", task.getException());
                }
            }
        });
        return lista; // devuelve un array vacío (no consigo que funcione)
    }
}
package com.example.myapplication;

import static com.example.myapplication.teste.getJSONFromaAPI;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.jetbrains.annotations.NotNull;


public class Dados extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);

        Button btnCep = (Button) findViewById(R.id.buscep);
        EditText ettcep = (EditText) findViewById(R.id.ettcep);
        EditText ettretorno = (EditText) findViewById(R.id.ettretorno);


        btnCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://viacep.com.br/ws/"+ ettcep.getText() +"/json/";
                String retorno = getJSONFromaAPI(url);
                ettretorno.setText(retorno);


                //ettretorno.setText(getJSONFromaAPI("https://viacep.com.br/ws/"+ ettcep.getText() +"/json/"));
            }


        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location l) {

                Toast toast = Toast.makeText(getApplicationContext(), l.toString(), Toast.LENGTH_LONG);
                toast.show();
                Log.d("location", l.toString());
                Log.d("location", ""+l.getLatitude());
                Log.d("location", ""+l.getLongitude());
                Log.d("location", "https://maps.google.com/?q="+l.getLatitude()+","+l.getLongitude());
                Log.d("location", l.toString());
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://maps.google.com/?q="+l.getLatitude()+","+l.getLongitude()));
                startActivity(i);
            }
        });
        fusedLocationClient.getLastLocation().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception l) {
                Toast toast = Toast.makeText(getApplicationContext(), l.toString(), Toast.LENGTH_LONG);
                toast.show();
                Log.d("location", l.toString());
            }
        });


    }


}

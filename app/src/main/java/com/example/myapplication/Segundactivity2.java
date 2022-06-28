package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.location.FusedLocationProviderClient;

public class Segundactivity2 extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button button3,button2;
    private historicoFragment historicoFragment;
    private gerenciadorFragment gerenciadorFragment;
    private FusedLocationProviderClient fusedLocationClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundactivity2);

        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);


        gerenciadorFragment = new gerenciadorFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameConteudo, gerenciadorFragment);
        transaction.commit();


        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                historicoFragment = new historicoFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, historicoFragment);
                transaction.commit();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gerenciadorFragment = new gerenciadorFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, gerenciadorFragment);
                transaction.commit();
            }
        });
    }

    public void start2(View view) {
        Toast.makeText(this, "APP em fase de testes", Toast.LENGTH_SHORT).show();

        final int REQUEST_IMAGE_CAPTURE = 1;

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Log.d("gdfgdfgdfg", takePictureIntent.toString());
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imageView = findViewById(R.id.imageView2);
            imageView.setImageBitmap(imageBitmap);
        }
    }


}





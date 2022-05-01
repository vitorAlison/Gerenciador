package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Segundactivity2 extends AppCompatActivity {

    private Button button3,button2;
    private historicoFragment historicoFragment;
    private gerenciadorFragment gerenciadorFragment;

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
    }
}
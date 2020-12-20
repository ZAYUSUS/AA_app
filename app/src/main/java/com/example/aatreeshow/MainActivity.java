package com.example.aatreeshow;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AAtree arbol;

    TextInputEditText entrada;
    TextView arbolshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arbol = new AAtree();
        entrada = (TextInputEditText)findViewById(R.id.entrada);
        arbolshow = (TextView)findViewById(R.id.Arbolview);

        arbol.Insert(24);
        arbol.Insert(5);
        arbol.Insert(28);
        arbol.Insert(6);
        arbol.Insert(94);

    }

    public void Insertar(View view){
        arbolshow.setText("");
        try {
            arbol.Insert(Integer.parseInt(entrada.getText().toString()));
        }catch (RuntimeException a){
            a.printStackTrace();
        }
        Refreshtree();


    }

    private void Refreshtree(){
        arbolshow.append("inorder:");
        for(int num : arbol.inorder()) {
            arbolshow.append(num+" ");
        }
        arbolshow.append("\n");
        arbolshow.append("preorder:");
        for(int num : arbol.preorder()) {
            arbolshow.append(num+" ");
        }
        arbolshow.append("\n");
        arbolshow.append("postorder:");
        for(int num : arbol.postorder()) {
            arbolshow.append(num+" ");
        }

    }
    public void Resetarbol(View view){
        arbol = new AAtree();
        arbolshow.setText("");
    }
}
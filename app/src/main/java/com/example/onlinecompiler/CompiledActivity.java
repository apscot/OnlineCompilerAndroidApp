package com.example.onlinecompiler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.onlinecompiler.model.CompilerResponse;

import java.io.Serializable;

public class CompiledActivity extends AppCompatActivity {

    private TextView textView6,textview7;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compiled);


        textView6 = findViewById(R.id.textView6);
        textview7 = findViewById(R.id.textView7);
        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivity1();
            }
        });

       CompilerResponse compilerResponse = (CompilerResponse) this.getIntent().getSerializableExtra("reponseObject");

       if(null != compilerResponse) {
           if (null != compilerResponse.getStdErr() && !compilerResponse.getStdErr().isEmpty())
               textView6.setText(compilerResponse.getStdErr());
           else
               textView6.setText("No Error Found");

           if (null != compilerResponse.getStdOut()  && !compilerResponse.getStdOut().isEmpty())
               textview7.setText(compilerResponse.getStdOut());
           else
               textview7.setText("No Output!");
       }

    }
    public void openActivity1() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

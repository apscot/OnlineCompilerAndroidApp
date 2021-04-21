package com.example.onlinecompiler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.example.onlinecompiler.model.CompilerResponse;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button compileButton;
    private EditText editText1;
    private EditText editText2;
    Map<String, String> postParam= new HashMap<String, String>();
    CompilerResponse  compilerResponse;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText1.setText("/*Dont do any package declaration*/ \n\n import java.util.*;\n\npublic class Work{ \t\t/*dont change the classname */ \n\n" +
                "\t\t public static void main(String args[]) throws Exception{\n\n" +
                "\t\t\t\t //Your Code Goes Here\n\n\t\t}\n\n}");




        compileButton = (Button) findViewById(R.id.button);
        compileButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            postParam.put("code",editText1.getText().toString());
            postParam.put("stdIn",editText2.getText().toString());
            openActivity2();
        }
    });
}

    public void openActivity2() {

        String url = "http://ec2-52-66-214-0.ap-south-1.compute.amazonaws.com:8080/compile"; //"http://192.168.1.132:8080/compile";

        Log.e("Val:",editText1.getText().toString());

        RequestQueue rq = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(postParam), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                       Gson gson = new Gson();
                       compilerResponse = gson.fromJson(response.toString(),CompilerResponse.class);
                       Log.e("Reponse",response.toString());
                        Intent intent = new Intent(context, CompiledActivity.class);
                        intent.putExtra("reponseObject", (Serializable) compilerResponse);
                        startActivity(intent);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                       Log.e("Error Reponse",error.toString());

                    }
                });


        rq.add(jsonObjectRequest);



    }
}

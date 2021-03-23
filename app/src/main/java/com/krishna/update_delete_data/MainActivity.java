package com.krishna.update_delete_data;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String fetchUrl = "http://10.0.2.2/android_php/fetchData.php";
    private static final String updateUrl = "http://10.0.2.2/android_php/updateData.php";
    private static final String deleteUrl = "http://10.0.2.2/android_php/deleteData.php";
    EditText searchName,firstName,lastName,address,email,mobile,password;
    String First,Last,Address,Email,Mobile,Password;
    Button search,update,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchName = findViewById(R.id.serchname);
        firstName = findViewById(R.id.fname);
        lastName = findViewById(R.id.lname);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);

        search = findViewById(R.id.search);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteData();
            }
        });
    }




    private void fetchData() {

        StringRequest request = new StringRequest(Request.Method.POST, fetchUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);

                    int success = object.getInt("sucess");

                    if (success == 0)
                    {
                        Toast.makeText(MainActivity.this,object.getString("message"),Toast.LENGTH_LONG).show();
                    }
                    else if (success == 1)
                    {
                        First = object.getString("fname");
                        Last = object.getString("lname");
                        Address = object.getString("address");
                        Email = object.getString("email");
                        Mobile = object.getString("mobile");
                        Password = object.getString("password");

                        firstName.setText(First);
                        lastName.setText(Last);
                        address.setText(Address);
                        email.setText(Email);
                        mobile.setText(Mobile);
                        password.setText(Password);
                        Toast.makeText(MainActivity.this,object.getString("message"),Toast.LENGTH_LONG).show();
                        searchName.setText("");
                    }
                    else if (success == 2)
                    {
                        Toast.makeText(MainActivity.this,object.getString("message"),Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<String, String>();

                map.put("sName",searchName.getText().toString().trim());

                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void updateData() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, updateUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                try
                {
                    JSONObject jsonObject=new JSONObject(response);

                    int sucess=jsonObject.getInt("sucess");

                    if(sucess==1)
                    {
                        firstName.setText("");
                        lastName.setText("");
                        address.setText("");
                        email.setText("");
                        mobile.setText("");
                        password.setText("");
                        Toast.makeText(MainActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                    }
                    else if(sucess==2)
                    {
                        Toast.makeText(MainActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }
                    else if(sucess==3)
                    {
                        Toast.makeText(MainActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }
                }

                catch (JSONException e)
                {
                    e.printStackTrace();

                    Toast.makeText(MainActivity.this,"Exception....",Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> param=new HashMap<String,String>();

                param.put("firstname",firstName.getText().toString().trim());
                param.put("lastname",lastName.getText().toString().trim());
                param.put("address",address.getText().toString().trim());
                param.put("email",email.getText().toString().trim());
                param.put("mobile",mobile.getText().toString().trim());
                param.put("password",password.getText().toString().trim());
                return param;

            }
        };

        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    private void deleteData() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, deleteUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject jsonObject=new JSONObject(response);

                    int sucess=jsonObject.getInt("sucess");

                    if(sucess==1)
                    {
                        firstName.setText("");
                        lastName.setText("");
                        address.setText("");
                        email.setText("");
                        mobile.setText("");
                        password.setText("");
                        searchName.setText("");
                        Toast.makeText(MainActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                    }
                    else if(sucess==2)
                    {
                        Toast.makeText(MainActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }
                    else if(sucess==3)
                    {
                        Toast.makeText(MainActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }
                }

                catch (JSONException e)
                {
                    e.printStackTrace();

                    Toast.makeText(MainActivity.this,"e.getMessage()",Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(MainActivity.this,"error "+error,Toast.LENGTH_LONG).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> param=new HashMap<String,String>();

                param.put("sName",firstName.getText().toString().trim());
                return param;
            }
        };

        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
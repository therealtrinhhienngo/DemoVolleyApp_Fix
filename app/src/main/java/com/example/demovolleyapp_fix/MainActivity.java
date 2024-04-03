package com.example.demovolleyapp_fix;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.demovolleyapp_fix.Adapter.EmployeeListAdapter;
import com.example.demovolleyapp_fix.Model.Employee;
import com.example.demovolleyapp_fix.Service.ApiService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button getApiButton;

    ArrayList<Employee> handleList;
    EmployeeListAdapter employeeListAdapter;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recycleView);
        getApiButton = findViewById(R.id.callApiButton);

        apiService = new ApiService();
        handleList = new ArrayList<>();
        employeeListAdapter = new EmployeeListAdapter(this, handleList);

        recyclerView.setAdapter(employeeListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getApiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("demo", "Click");
                getApiData(MainActivity.this);
            }
        });
    }

    public void getApiData(Context context){
        // Making request
        RequestQueue queqe = Volley.newRequestQueue(context);

        // Start the request
        JsonArrayRequest request = new JsonArrayRequest("http://192.168.1.63:3000/journalRoute/get", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++){
                    try {
                        JSONObject object = jsonArray.getJSONObject(i);

                        String id = object.getString("id");
                        String name = object.getString("name");

                        Employee newEmployee = new Employee(id, name);
                        handleList.add(newEmployee);
                    }
                    catch (Exception e){
                        Log.e("err", "Error: " + e.toString());
                    }
                }
                employeeListAdapter = new EmployeeListAdapter(MainActivity.this, handleList);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(employeeListAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("err2", "Error Voller: " + volleyError.toString());
            }
        });

        queqe.add(request);
    }
}
package com.example.demovolleyapp_fix.Service;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.demovolleyapp_fix.Model.Employee;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ApiService {
    String apiUrl = "http://192.168.1.63:3000/journalRoute/get";

    public ArrayList<Employee> getApiData(Context context){
        ArrayList<Employee> employeeArrayList = new ArrayList<>();

        // Making request
        RequestQueue queqe = Volley.newRequestQueue(context);

        // Start the request
        JsonArrayRequest request = new JsonArrayRequest(apiUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++){
                    try {
                        JSONObject object = jsonArray.getJSONObject(i);

                        String id = object.getString("id");
                        String name = object.getString("name");

                        Employee newEmployee = new Employee(id, name);
                        employeeArrayList.add(newEmployee);
                    }
                    catch (Exception e){
                        Log.e("err", "Error: " + e.toString());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("err2", "Error Voller: " + volleyError.toString());
            }
        });

        queqe.add(request);

        return employeeArrayList;
    }
}

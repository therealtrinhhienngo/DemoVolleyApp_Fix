package com.example.demovolleyapp_fix.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demovolleyapp_fix.Model.Employee;
import com.example.demovolleyapp_fix.R;

import java.util.ArrayList;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Employee> employeeArrayList;

    public EmployeeListAdapter(Context context, ArrayList<Employee> employeeArrayList) {
        this.context = context;
        this.employeeArrayList = employeeArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View heroView = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(heroView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee currentObj = employeeArrayList.get(position);

        holder.idOutput.setText("Id: " + currentObj.getId());
        holder.nameOutput.setText("Name: " + currentObj.getName());

        Log.d("demo", "Arr Id: " + currentObj.getId());
    }

    @Override
    public int getItemCount() {
        return employeeArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView idOutput, nameOutput;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idOutput = itemView.findViewById(R.id.idOutput);
            nameOutput = itemView.findViewById(R.id.nameOutput);
        }
    }
}

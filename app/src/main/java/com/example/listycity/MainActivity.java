package com.example.listycity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    private TextView textView;

    boolean delete_mode = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        Button add_city = findViewById(R.id.add_city);
        View add_city_view = findViewById(R.id.add_city_view);
        TextView cityInput = findViewById(R.id.input_box);
        Button confirm_button = findViewById(R.id.confirm_button);
        Button delete_city = findViewById(R.id.delete_city);
        add_city.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (View.VISIBLE == add_city_view.getVisibility()) {
                    add_city_view.setVisibility(View.GONE);
                } else {
                    add_city_view.setVisibility(View.VISIBLE);
                }
            }
        });
        confirm_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String newCity = cityInput.getText().toString().trim();
                if (!newCity.isEmpty()) {
                    dataList.add(newCity);
                    cityAdapter.notifyDataSetChanged();
                    cityInput.setText("");
                    add_city_view.setVisibility(View.GONE);
                }
            }
        });
        delete_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_mode = !delete_mode;
                if(delete_mode){
                    delete_city.setBackgroundColor(Color.parseColor("#444444"));
                } else{
                    delete_city.setBackgroundColor(Color.parseColor("#D3D3D3"));
                }
            }
        });
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            if (delete_mode){
                dataList.remove(position);
                cityAdapter.notifyDataSetChanged();
                delete_mode = false;
                delete_city.setBackgroundColor(Color.parseColor("#D3D3D3"));
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
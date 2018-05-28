package com.example.azaat.testfotth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText search;
    Spinner spinner;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    List<Person> list;
    List<Person> listFiltre;

    String []arr = {"Choose","Male","Female"};
    int selectFilter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = (EditText) findViewById(R.id.search);
        spinner = (Spinner) findViewById(R.id.spinner);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progressbar) ;

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,arr);
        spinner.setAdapter(arrayAdapter);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterfase interfase = retrofit.create(RetrofitInterfase.class);
        Call<List<Person>> call = interfase.listResp();
        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                Toast.makeText(MainActivity.this, "Response", Toast.LENGTH_SHORT).show();
                list = response.body();
                listFiltre = list;
                Collections.sort(listFiltre, new Comparator<Person>() {
                    @Override
                    public int compare(Person o1, Person o2) {
                        return (o1.getName()+" "+o1.getSurname()).compareTo(o2.getName()+" "+o2.getSurname());
                    }
                });
                MyAdapter adapter = new MyAdapter(list,MainActivity.this);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(selectFilter != position){
                    selectFilter = position;
                    if(arr[position].equals("Male") || arr[position].equals("Female")){
                        listFiltre = new ArrayList<>();
                        for (int i = 0;i < list.size();i++){
                            if (arr[position].equals(list.get(i).getMi())) listFiltre.add(list.get(i));
                        }
                        Collections.sort(listFiltre, new Comparator<Person>() {
                            @Override
                            public int compare(Person o1, Person o2) {
                                return (o1.getName()+" "+o1.getSurname()).compareTo(o2.getName()+" "+o2.getSurname());
                            }
                        });
                        MyAdapter adapter = new MyAdapter(listFiltre,MainActivity.this);
                        recyclerView.setAdapter(adapter);
                    }else {
                        listFiltre = list;
                        MyAdapter adapter = new MyAdapter(list,MainActivity.this);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s != null || s.length() != 0){
                    List<Person> l = new ArrayList<>();
                    for(int i = 0;i<listFiltre.size();i++){
                        Person person = listFiltre.get(i);
                        if ((person.getName()+" "+person.getSurname()).contains(s.toString())) l.add(person);
                    }
                    MyAdapter adapter = new MyAdapter(l,MainActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }


}

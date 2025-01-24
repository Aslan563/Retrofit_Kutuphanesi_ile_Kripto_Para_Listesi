package com.example.retrofitktphanesikriptoparauygulamas;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
     Retrofit retrofit;
     RecyclerView mylistem;
     adapter adapter1;
     ArrayList<kriptomodel> arrayListkripto;
     CompositeDisposable compositeDisposable;
    private String BASE_URL = "https://raw.githubusercontent.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mylistem=findViewById(R.id.mylistem);


        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Load();
    }

    public void Load(){

        final kriptoAPI kriptoapi=retrofit.create(kriptoAPI.class);

        compositeDisposable=new CompositeDisposable();

        compositeDisposable.add(kriptoapi.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getdata));
       /* Call<List<kriptomodel>> call= (Call<List<kriptomodel>>) kriptoapi.getData();

        call.enqueue(new Callback<List<kriptomodel>>() {
            @Override
            public void onResponse(Call<List<kriptomodel>> call, Response<List<kriptomodel>> response) {
            if(response.isSuccessful()){
                List<kriptomodel>  responselist=response.body();
                arrayListkripto=new ArrayList<>(responselist);
                mylistem.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));
                adapter1=new adapter(arrayListkripto,getApplicationContext());
                mylistem.setAdapter(adapter1);


            }
            }

            @Override
            public void onFailure(Call<List<kriptomodel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/



    }
    public void getdata(List<kriptomodel> kriptolist){
        arrayListkripto=new ArrayList<>(kriptolist);
        mylistem.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));
        adapter1=new adapter(arrayListkripto,getApplicationContext());
        mylistem.setAdapter(adapter1);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
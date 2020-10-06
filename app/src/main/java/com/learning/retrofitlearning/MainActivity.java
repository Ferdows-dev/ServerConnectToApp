package com.learning.retrofitlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static  final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    List<posts> posts;
    private postsAdapter postsAdapter;
    private userIDService service;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv);
        posts = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(userIDService.class);

        Call<List<posts>> apiCall = service.getAllPosts();

        apiCall.enqueue(new Callback<List<posts>>() {
            @Override
            public void onResponse(Call<List<posts>> call, Response<List<posts>> response) {
                if (response.code()==200){
                    posts = response.body();
                    Log.e("posts", posts +"");
                    postsAdapter = new postsAdapter(MainActivity.this, posts);
                    listView.setAdapter(postsAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<posts>> call, Throwable t) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posts posts1 = posts.get(position);
               startActivity(new Intent(MainActivity.this,DetailsActivity.class)
                        .putExtra("Posts",posts1));
            }
        });
    }
}
package com.example.samplehi;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] mDataset; //초기 원본 데이터 변수- NewsActivity.java 에도 복사해줌
    RequestQueue queue; //queue 변수는 전역변수로 빼줌, 그리고 초기화


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        queue= Volley.newRequestQueue(this); //
        getnews(); //// 1. 완료

        // <제일 큰 Logic 정리>
        //1. 화면이 로딩 -> 뉴스 정보를 받아온다.
        //2. 정보 -> 어댑터 넘겨준다.
        //3. 어댑터 -> 셋팅, 화면에 표시
    }
    public void getnews(){
        // Instantiate the RequestQueue.
        String url ="http://newsapi.org/v2/top-headlines?country=kr&apiKey=3aed0a37b3ab4df8acd7fd5b9f5a022f";
                //volley한테 이 주소로 접속하라고 알려주는 것

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) { //response? 반응인데, 값이 String 이네

                        //Log.d("NEWS",response); //로그, ,, 이건 뭘까 - 이건 원본데이타 로그 찍은 것.

                        try {
                            JSONObject jsonObj = new JSONObject(response); // json string 이라는 가정 하에 JSONObject 로 바꾼다

                            JSONArray arrayArticles = jsonObj.getJSONArray("articles"); //articles라는 이름을 가진 배열을 가져와라.

                            //response ->> NewsData Class 분류
                            // (string 원문을 잘 분류, 분리하여 NewsData 클래스에 담아줄 생각임!)

                            List<NewsData> news = new ArrayList<>(); //선언 = 생성 (위치도 고려해서 넣어야함 for문 때무넹)

                            // 얼굴 인식 -> 스티커 (하트 뿅)
                            //
                            for(int i = 0, j=arrayArticles.length();i<j;i++) {
                                JSONObject obj = arrayArticles.getJSONObject(i);

                                Log.d("NEWS",obj.toString());

                                NewsData newsData = new NewsData();
                                newsData.setTitle(obj.getString("title")); //분류
                                newsData.setUrlToImage(obj.getString("urlToImage"));
                                newsData.setContent(obj.getString("content"));

                                news.add(newsData); //LIst에 하나씩 넣어라==add();
                            }

                            // specify an adapter (see also next example)
                            mAdapter = new MyAdapter(news, NewsActivity.this); //data를 액티비티파일에서 받아와서 어댑터로 전달하는 부분
                            recyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    // ...
}
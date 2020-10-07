package com.example.samplehi;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private List<NewsData> mDataset; //초기 원본 데이터 변수- NewsActivity.java 에도 복사해줌

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder

        // ( 1 ) ---------------------------------------
        public static class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView TextView_Title;
            public TextView TextView_Content;
            public SimpleDraweeView ImageView_title;
            public MyViewHolder(View v) {
                super(v);
                TextView_Title=v.findViewById(R.id.TextView_title); //xml에서 어떤 요소를 찾을땐 부모에서 찾으라 해야 하므로 v.~~~
                TextView_Content=v.findViewById(R.id.TextView_content);
                ImageView_title=v.findViewById(R.id.ImageView_title); // 그 자식들이 누구인지 매칭을 시켜주는 작업
                ImageView_title = (SimpleDraweeView) v.findViewById(R.id.ImageView_title);
               // -------------------------------------------- MyViewHolder - 화면 한줄한줄을 담당
            }
        }

        // ( 2 ) ---------------------------------------------------------
        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(List<NewsData> myDataset, Context context) { //어댑터에서 받을 데이터 형태를 List<NewsData>로 변경한다.
            mDataset = myDataset;
            Fresco.initialize(context);
        } //초기 데이터를 여기에 저장 - dataset
        // --------------------------------------------------- 뷰홀더 반복하는 작업
        // *.뷰홀더가 원본 데이터의 크기(번지수 길이)만큼 반복됩니다. !



        @Override
        // Create new views (invoked by the layout manager)
        // ( 3 )--------------------------------------------------------
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
            // create a new view
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_news, parent, false); //row_news.xml은 Linear로 묶여있으므로 바꿔주었음

            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        } // ------------------------------------------------ onCreateViewHolder - 한 row에 이미지파일을 연결하는 부분



        // ( 4 ) -----------------------------------------------------------------
        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            NewsData news = mDataset.get(position);

            holder.TextView_Title.setText(news.getTitle());

            String content = news.getContent();
            if (content != null && content.length() >0){
                holder.TextView_Content.setText(content);
            }
            else{
                holder.TextView_Content.setText("-");
            }

            Uri uri = Uri.parse(news.getUrlToImage());

            holder.ImageView_title.setImageURI(uri); //이미지 로딩용 샘플 코드

        } // ----------------------------------------------- onBindViewHolder - 위에서 받아오고, bind에서 '표시'해준다 여기서 홀더는 myViewHolder
        // (뷰홀더가 반복되면서 onBind~ 함수에서 값을 셋팅합니다.) - 원본데이터의 크기만큼 반복한다.


        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset == null ? 0 : mDataset.size(); //삼항연산자 활용- 1값이 트루면 0, false면 뒤에꺼
        } //여기의 length만큼 반복
    }


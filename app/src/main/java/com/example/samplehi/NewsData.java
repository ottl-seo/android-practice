package com.example.samplehi;

import java.io.Serializable;

public class NewsData implements Serializable {
    private String title;
    private String urlToImage;
    private String content; //NewsData 이건 뉴스 한개만 보여줌 -> 여러개 하려면? List 배열이 필요함

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }


}

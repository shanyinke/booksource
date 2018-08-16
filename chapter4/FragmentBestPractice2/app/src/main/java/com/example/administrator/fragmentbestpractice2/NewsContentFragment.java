package com.example.administrator.fragmentbestpractice2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NewsContentFragment extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle SavedInstanceState){
        view = inflater.inflate(R.layout.news_content_frag,container,false);
        return view;    }
        public void refresh(String newsTitle,String newsContent){
        View visiblityLayout =view.findViewById(R.id.visibility_layout);
            TextView newsTitleText= (TextView) view.findViewById(R.id.news_title);
            TextView newsContentText=(TextView) view.findViewById(R.id.news_content);
            newsTitleText.setText(newsTitle);
            newsContentText.setText(newsContent);
        }
}

package com.example.administrator.androidthreadtest2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int UPDATE_TEXT=1;
    private TextView text;
    private Handler handler = new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case UPDATE_TEXT:
                    text.setText(text.getText()+"Nice to meet you!");
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button changeText = (Button) findViewById(R.id.change_text);
        changeText.setOnClickListener(this);
        text = (TextView) findViewById(R.id.text);

    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.change_text:
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = UPDATE_TEXT;
                    handler.sendMessage(message);
                }
            }).start();
            break;
            default:
                break;
        }
    }
}

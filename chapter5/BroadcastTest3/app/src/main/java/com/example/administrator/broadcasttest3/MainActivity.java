package com.example.administrator.broadcasttest3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetWorkChangeReceiver netWorkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netWorkChangeReceiver =new NetWorkChangeReceiver();
        registerReceiver(netWorkChangeReceiver,intentFilter);

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(netWorkChangeReceiver);
    }
    class NetWorkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent){
            ConnectivityManager connectivityManager=(ConnectivityManager)
            getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            //获取名字为tv_hello的TextView控件
            TextView tv_hello = (TextView) findViewById(R.id.text_view);


            if (networkInfo != null && networkInfo.isConnected()){
                //给TextView控件设置文字内容
                tv_hello.setText("网络已经连接");
                //给TextView控件设置文字颜色
                tv_hello.setTextColor(Color.RED);
                //给TextView控件设置文字大小
                tv_hello.setTextSize(30);
                Toast.makeText(context,"network isConnected",Toast.LENGTH_SHORT).show();
            }else{
                //给TextView控件设置文字内容
                tv_hello.setText("网络已经断开");
                //给TextView控件设置文字颜色
                tv_hello.setTextColor(Color.RED);
                //给TextView控件设置文字大小
                tv_hello.setTextSize(30);
                Toast.makeText(context,"network is unConnected",Toast.LENGTH_SHORT).show();
            }

           // Toast.makeText(context,"network changes",Toast.LENGTH_SHORT).show();
        }
    }
}

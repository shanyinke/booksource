package com.example.administrator.activitytest1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends BaseActivity {
    public static void actionStart(Context context, String data1, String data2) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SecondActivity","Task id is "+ getTaskId());
        Toast.makeText(this,"Task id is "+ getTaskId(),Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"SecondActivity Created",Toast.LENGTH_SHORT).show();
        Log.d("SecondActivity",this.toString());
        setContentView(R.layout.activity_second);
//        Intent intent=getIntent();
//        String data=intent.getStringExtra("extra_data");
//        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
//        Log.d("SecondActivity",data);
        Button button_2=(Button) findViewById(R.id.button_2);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent();
//                intent.putExtra("data_return","Hello MainActivity");
//                setResult(RESULT_OK,intent);
//                finish();
                Intent intent = new Intent( SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("data_return", "Hello FirstActivity");
        setResult(RESULT_OK, intent);
        finish();
    }
@Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("SecondActivity","onDestroy");
        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT ).show();
}

}

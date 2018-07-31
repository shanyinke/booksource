package com.example.administrator.activitytest1;

import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity",this.toString());
        Log.d("MainActivity","Task id is "+ getTaskId());
        setContentView(R.layout.first_layout);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
//                Intent intent=new Intent("com.example.administrator.activitytest1.ACTION_START");
//                intent.addCategory("com.example.administrator.activitytest1.MY_CATEGOREY");
//                Intent intent =new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("http://www.aksky.com"));
//                Intent intent=new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:10086"));
//                String data="Hello SecondActivity";
//                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
//                intent.putExtra("extra_data",data);
//                startActivity(intent);
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                startActivityForResult(intent, 1);
//                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
//                intent.putExtra("param1","data1");
//                intent.putExtra("param2","data2");
//                startActivity(intent);
                SecondActivity.actionStart(MainActivity.this, "data1", "data2");
//                finish();
//                Toast.makeText(MainActivity.this,"You Clicked Button 2",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
            case R.id.remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
            case R.id.Item_3:
                Toast.makeText(this, "You clicked Item3", Toast.LENGTH_SHORT).show();
            default:

        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("FirstActivity", returnedData);
                    Toast.makeText(this,returnedData, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("MainActivity","onRestart");
        Toast.makeText(this,"onRestart",Toast.LENGTH_SHORT).show();
    }
}

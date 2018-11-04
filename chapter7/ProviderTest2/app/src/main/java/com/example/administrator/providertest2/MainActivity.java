package com.example.administrator.providertest2;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private String newId;
    TextView textView;
    Date dt;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
               Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                // 添加数据
                Uri uri = Uri.parse("content://com.example.administrator.databasetest2.provider/book");
                ContentValues values = new ContentValues();
                values.put("name", "A Clash of Kings");
                values.put("author", "George Martin");
                values.put("pages", 1040);
                values.put("price", 55.55);
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
                 dt = new Date();

                textView.setText(textView.getText()+"\n添加数据成功"+dt.toString());
            }
        });
        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                Uri uri = Uri.parse("content://com.example.administrator.databasetest2.provider/book");
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if(cursor != null){
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        dt = new Date();
                        textView.setText("book name  is "+name +
                        "\n book author is "+author
                        +"\n book pages is "+pages+
                        "\n book price  is "+price+"\n"+dt.toString()+textView.getText());

                    }
                    cursor.close();
                }
            }
        });
        Button updateData = (Button)findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.example.administrator.databasetest2.provider/book/"+newId);
//
//                ContentValues values = new ContentValues();
//                values.put("name", "A Clash of Kings");
//                values.put("author", "George Martin");
//                values.put("pages", 1040);
//                values.put("price", 55.55);
                ContentValues values = new ContentValues();
                values.put("name","A Story of Swords");
                values.put("pages",1216);
                values.put("price",24.05);
                getContentResolver().update(uri,values,null,null);

            }
        });
        Button deleteDate = (Button) findViewById(R.id.delete_data);
        deleteDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.example.administrator.databasetest2.provider/book/"+newId);
                getContentResolver().delete(uri,null,null);
               // newId = uri.getPathSegments().get(1);
            }
        });

    }
}

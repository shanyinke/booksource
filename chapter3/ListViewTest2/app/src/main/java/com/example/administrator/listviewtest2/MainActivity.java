package com.example.administrator.listviewtest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<Fruit>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
        ListView listView= (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void initFruits() {
        for (int i = 0; i <2; i++) {
            Fruit apple =new Fruit("Apple",R.drawable.apple_pic,"hahaha ");
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana", R.drawable.banana_pic,"hahaha ");
            fruitList.add(banana);
            Fruit orange = new Fruit("Orange", R.drawable.orange_pic,"hahaha ");
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic,"hahaha ");
            fruitList.add(watermelon);
            Fruit pear = new Fruit("Pear", R.drawable.pear_pic,"hahaha ");
            fruitList.add(pear);
            Fruit grape = new Fruit("Grape", R.drawable.grape_pic,"hahaha ");
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic,"hahaha ");
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic,"hahaha ");
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic,"hahaha ");
            fruitList.add(cherry);
            Fruit mango = new Fruit("Mango", R.drawable.mango_pic,"hahaha ");
            fruitList.add(mango);
        }
    }

}

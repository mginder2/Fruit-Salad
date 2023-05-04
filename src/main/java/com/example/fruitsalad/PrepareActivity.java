package com.example.fruitsalad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class PrepareActivity extends AppCompatActivity {

    TextView t1,t2,points;
    Button cookBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);

        getRoomData();

        cookBtn = findViewById(R.id.cook);
        cookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int score = new Random().nextInt(71) + 5;
                Intent intent = new Intent(PrepareActivity.this, MainActivity.class);
                intent.putExtra("tempint", score);
                startActivity(intent);
            }
        });
    }

    public void getRoomData(){
        InventoryDatabase db = Room.databaseBuilder(getApplicationContext(),
                InventoryDatabase.class, "Inventory").allowMainThreadQueries().build();
        InventoryDao invDao = db.inventoryDao();

        t1 = findViewById(R.id.textView);
        t2 = findViewById(R.id.textView2);

        List<String> fruits = invDao.getAllFruit();

        for(int i = 0; i < fruits.size(); i++){
            t1.append(fruits.get(i) + " ");
            t2.append(fruits.get(i) + " ");
        }


    }
}
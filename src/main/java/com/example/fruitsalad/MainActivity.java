package com.example.fruitsalad;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button prepareBtn;
    Button harvestBtn;
    Toolbar toolbar;
    TextView points;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar
        toolbar = findViewById(R.id.toolsHere);
        setSupportActionBar(toolbar);
        //make database
        InventoryDatabase db = Room.databaseBuilder(getApplicationContext(),
                InventoryDatabase.class, "Inventory").allowMainThreadQueries().build();
        InventoryDao invDao = db.inventoryDao();
        ScoreDatabase scores = Room.databaseBuilder(getApplicationContext(),
                ScoreDatabase.class, "Scores").allowMainThreadQueries().build();

        if(savedInstanceState != null) {
            if (savedInstanceState.containsKey("score")) {
                score = savedInstanceState.getInt("score");
            }
        }
        else{
            score = 0;
        }



        //prepare button
        prepareBtn = findViewById(R.id.prepare);
        prepareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prepareInt = new Intent(getBaseContext(), PrepareActivity.class);
                startActivity(prepareInt);
            }
        });

        //harvest button
        harvestBtn = findViewById(R.id.harvest);
        harvestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int random = new Random().nextInt(4) + 1;
                Inventory inv;
                if(random == 1){
                    inv =  new Inventory("strawberry", 10, 1.5);
                    db.inventoryDao().insert(inv);
                    Toast.makeText(MainActivity.this, "You harvested a Strawberry!", Toast.LENGTH_SHORT).show();
                }
                else if(random == 2){
                    inv =  new Inventory("peach", 25, 1.1);
                    db.inventoryDao().insert(inv);
                    Toast.makeText(MainActivity.this, "You harvested a Peach!", Toast.LENGTH_SHORT).show();
                }
                else if(random == 3){
                    inv =  new Inventory("grape", 50, 1.0);
                    db.inventoryDao().insert(inv);
                    Toast.makeText(MainActivity.this, "You harvested a Grape!", Toast.LENGTH_SHORT).show();
                }
                else{
                    inv =  new Inventory("banana", 100, .9);
                    db.inventoryDao().insert(inv);
                    Toast.makeText(MainActivity.this, "You harvested a Banana!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        List<Integer> lst = scores.scoreDao().getAllScores();

        if(lst != null){
            //score = lst.get(0);
        }

        Intent intent = getIntent();
        int intTemp = intent.getIntExtra("tempint", 0);
        score = score + intTemp;

        scores.scoreDao().deleteAll();
        scores.scoreDao().insert(new Score(score));

        //points
        points = findViewById(R.id.currPoints);
        points.setText("Points:" + score);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("score", score);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        startActivity(new Intent(this, SettingsActivity.class));
        return true;
    }
}
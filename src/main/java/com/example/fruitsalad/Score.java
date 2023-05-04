package com.example.fruitsalad;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Score {
    @PrimaryKey(autoGenerate = true)
    public int fid;

    @ColumnInfo(name = "curr_score")
    public int score;

    public Score(int score){
        this.score = score;
    }
}

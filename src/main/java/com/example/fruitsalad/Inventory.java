package com.example.fruitsalad;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Inventory {
    @PrimaryKey(autoGenerate = true)
    public int fid;

    @ColumnInfo(name = "fruit_name")
    public String fruitName;

    @ColumnInfo(name = "base_points")
    public int basePoints;

    @ColumnInfo(name = "point_multiplier")
    public double pointMultiplier;

    public Inventory(String fruitName, int basePoints, double pointMultiplier){
        this.fruitName = fruitName;
        this.basePoints = basePoints;
        this.pointMultiplier = pointMultiplier;
    }
}

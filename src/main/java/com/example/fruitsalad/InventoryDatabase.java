package com.example.fruitsalad;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Inventory.class}, version = 1)
public abstract class InventoryDatabase extends RoomDatabase {
    public abstract InventoryDao inventoryDao();
}

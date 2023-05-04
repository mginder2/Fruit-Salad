package com.example.fruitsalad;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InventoryDao {
    @Insert
    long insert(Inventory inventory);

    @Query("SELECT * FROM inventory")
    List<Inventory> getAllInventories();

    @Query("SELECT fruit_name FROM inventory")
    List<String> getAllFruit();

    @Query("DELETE FROM inventory WHERE fid = :id")
    void deleteById(int id);
}
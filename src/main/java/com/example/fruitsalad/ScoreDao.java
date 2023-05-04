package com.example.fruitsalad;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ScoreDao {
    @Insert
    void insert(Score score);

    @Query("SELECT curr_score FROM score")
    List<Integer> getAllScores();

    @Query("DELETE FROM  score")
    void deleteAll();
}

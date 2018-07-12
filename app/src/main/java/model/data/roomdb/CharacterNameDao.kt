package model.data.roomdb

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface CharacterNameDao {
    @Query("SELECT * FROM CharacterNames")
    fun getCharacterList(): List<CharacterNames>

    @Insert
    fun insert(repoList: ArrayList<CharacterNames>)

    @Query("DELETE FROM CharacterNames")
    fun deleteData()
}
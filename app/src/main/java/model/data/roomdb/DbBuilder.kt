package model.data.roomdb

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [(CharacterNames::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterNames(): CharacterNameDao
}
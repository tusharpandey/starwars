package model.data.roomdb

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "CharacterNames")
class CharacterNames {

    @PrimaryKey
    @ColumnInfo(name = "charactername")
    var name: String = ""


}
package view.charaterlist

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.app.starwars.R
import model.data.roomdb.AppDatabase
import view.utility.Utility

class CharacterListActivity : AppCompatActivity() {

    companion object {
        lateinit var dbInstance: AppDatabase
            private set
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)
        dbInstance = Room.databaseBuilder(this, AppDatabase::class.java, Utility.DB_NAME)
                .allowMainThreadQueries().build()
    }
}
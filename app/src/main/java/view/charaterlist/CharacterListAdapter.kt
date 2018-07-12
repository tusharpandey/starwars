package view.charaterlist

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.app.starwars.R
import model.data.pojo.Characters

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    var characterList: Characters = Characters()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val parentLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_character_list, parent, false) as CardView
        return ViewHolder(parentLayout)
    }

    override fun getItemCount(): Int {
        return characterList.results.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = characterList.results.get(position);
        holder.textView.text = data.name
    }

    class ViewHolder(cardView: CardView) : RecyclerView.ViewHolder(cardView) {
        var textView: TextView

        init {
            textView = cardView.findViewById(R.id.characterName) as TextView
        }
    }

    fun setCharacters(characters: Characters) {
        if (characterList.results.size == 0) {
            characterList = characters;
        } else {
            characterList.results.addAll(characters.results)
        }
        notifyDataSetChanged()
    }
}
package model.data.implementation

import model.callbacks.OnCharacterListReceived
import model.data.DBRepository
import model.data.pojo.Characters
import model.data.pojo.Result
import view.charaterlist.CharacterListActivity

class DBRepoImpl : DBRepository {
    override fun getCharacterList(pageItem: Int, onCharacterListReceived: OnCharacterListReceived) {
        var characterList = CharacterListActivity.dbInstance.characterNames().getCharacterList()
        var characters = Characters()
        var list = mutableListOf<Result>()
        for (name in characterList) {
            var result = Result()
            result.name = name.name
            list.add(result)
        }
        characters.results = list
        onCharacterListReceived.onCharacterListReceived(characters)
    }

    override fun getCharacterListSize() : Int {
        var characterList = CharacterListActivity.dbInstance.characterNames().getCharacterList()
        return characterList.size
    }

}
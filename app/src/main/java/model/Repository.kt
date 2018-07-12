package model

import model.callbacks.OnCharacterListReceived

interface Repository {
    fun getCharacterList(pageItem: Int, onCharacterListReceived: OnCharacterListReceived)

    fun getCharacterListSize() : Int
}
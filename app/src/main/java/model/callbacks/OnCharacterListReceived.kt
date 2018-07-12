package model.callbacks

import model.data.pojo.Characters

interface OnCharacterListReceived : Error{
    fun onCharacterListReceived(characters: Characters);
}
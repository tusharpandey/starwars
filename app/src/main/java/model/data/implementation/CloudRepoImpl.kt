package model.data.implementation

import model.callbacks.OnCharacterListReceived
import model.data.CloudRepository
import model.data.api.RetrofitRequestBuilder

class CloudRepoImpl : CloudRepository {

    var retrofitRequestBuilder: RetrofitRequestBuilder

    init {
        retrofitRequestBuilder = RetrofitRequestBuilder();
    }

    override fun getCharacterList(pageItem: Int, onCharacterListReceived: OnCharacterListReceived) {
        retrofitRequestBuilder.getCharacterList(pageItem, onCharacterListReceived)
    }

    override fun getCharacterListSize(): Int {
        return 0
    }
}
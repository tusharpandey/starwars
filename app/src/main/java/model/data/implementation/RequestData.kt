package model.data.implementation

import android.content.Context
import model.Repository
import model.callbacks.OnCharacterListReceived
import model.data.CloudRepository
import model.data.DBRepository
import view.utility.Utility

class RequestData(context: Context) : Repository {

    private var dbRepository: DBRepository
    private var cloudDataRepository: CloudRepository
    var context: Context = context;

    init {
        dbRepository = DBRepoImpl()
        cloudDataRepository = CloudRepoImpl()
    }

    override fun getCharacterList(pageItem: Int, onCharacterListReceived: OnCharacterListReceived) {
        if(pageItem==1 && Utility.noNetwork(context)){
            dbRepository.getCharacterList(pageItem, onCharacterListReceived)
            return;
        }
        cloudDataRepository.getCharacterList(pageItem, onCharacterListReceived)
    }

    override fun getCharacterListSize(): Int {
        return dbRepository.getCharacterListSize()
    }

}
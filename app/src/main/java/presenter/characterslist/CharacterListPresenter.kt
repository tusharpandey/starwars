package presenter.characterslist

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View
import com.app.starwars.R
import model.callbacks.OnCharacterListReceived
import model.data.implementation.RequestData
import model.data.pojo.Characters
import presenter.BasePresenter
import view.CharacterListView
import view.charaterlist.CharaterListLayout
import view.utility.Utility
import view.views.snackbar.RetryAction
import view.views.snackbar.SnackbarBuilder

class CharacterListPresenter constructor(view: CharacterListView) : BasePresenter, RetryAction {

    var characterListView: CharacterListView = view
    var requestData: RequestData = RequestData(view.getViewContext())

    override fun isAttachedToWindow(): Boolean {
        return characterListView.getViewContext() != null;
    }

    override fun onDetachedFromWindow() {
    }

    override fun onAttachToWindow() {
    }

    override fun getContext(): Context {
        return characterListView.getViewContext();
    }

    fun getCharacterList(pageItem: Int) {

        if (Utility.noNetwork(characterListView.getViewContext()) && requestData.getCharacterListSize() == 0) {
            characterListView.handleProgress(View.GONE)
            var msg = characterListView.getViewContext().getString(R.string.error_no_network)
            var snackbarBuilder = SnackbarBuilder(Snackbar.LENGTH_INDEFINITE, msg)
            snackbarBuilder.action = this
            characterListView.showError(characterListView as CharaterListLayout, snackbarBuilder)
            return
        } else {
            characterListView.handleProgress(View.VISIBLE)
        }

        requestData.getCharacterList(pageItem, object : OnCharacterListReceived {
            override fun onCharacterListReceived(characters: Characters) {
                if (isAttachedToWindow()) {
                    characterListView.setCharacters(characters)
                }
            }

            override fun onError(t: Throwable) {
                if (isAttachedToWindow()) {
                    characterListView.handleProgress(View.GONE)
                    var snackbarBuilder = SnackbarBuilder(Snackbar.LENGTH_SHORT, t.localizedMessage)
                    characterListView.showError(characterListView as CharaterListLayout, snackbarBuilder)
                }
            }
        })
    }

    override fun onClick(v: View) {
        if (isAttachedToWindow()) {
            characterListView.getCharacterList()
        }
    }
}
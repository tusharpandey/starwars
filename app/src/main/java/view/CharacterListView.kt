package view

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View
import model.data.pojo.Characters
import view.utility.OnLoadMoreListener
import view.views.snackbar.CustomSnackbar
import view.views.snackbar.SnackbarBuilder


interface CharacterListView : CustomSnackbar, OnLoadMoreListener {
    fun getViewContext(): Context
    fun setCharacters(characterListView: Characters)
    fun handleProgress(status: Int)
    fun getCharacterList();

    fun showError(view: View, snackbarBuilder: SnackbarBuilder) {
        when (snackbarBuilder.type) {
            Snackbar.LENGTH_SHORT ->
                showSnackbar(view, snackbarBuilder.msg)
            Snackbar.LENGTH_INDEFINITE ->
                showErrorWithAction(view, snackbarBuilder.msg, snackbarBuilder.action)
        }
    }
}

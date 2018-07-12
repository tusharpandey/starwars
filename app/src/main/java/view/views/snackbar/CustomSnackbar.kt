package view.views.snackbar

import android.support.design.widget.Snackbar
import android.view.View
import com.app.starwars.R


interface CustomSnackbar {

    fun showSnackbar(view: View, msg: String) {
        val snackbar = Snackbar
                .make(view, msg, Snackbar.LENGTH_SHORT)
        snackbar.show()
    }

    fun showErrorWithAction(view: View, msg: String, action: Action) {
        val snackbar = Snackbar
                .make(view, msg, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(R.string.action_retry, action)
        snackbar.show()
    }

}
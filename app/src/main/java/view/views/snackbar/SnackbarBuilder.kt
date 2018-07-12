package view.views.snackbar

import android.view.View

class TempListener() : Action {
    override fun onClick(v: View?) {
    }

}

data class SnackbarBuilder(var type: Int = -1, var msg: String = "") {
    var action: Action = TempListener()
}
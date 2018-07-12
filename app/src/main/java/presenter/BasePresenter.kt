package presenter

import android.content.Context

interface BasePresenter {
    fun getContext(): Context

    fun onAttachToWindow()

    fun onDetachedFromWindow()

    fun isAttachedToWindow() : Boolean
}
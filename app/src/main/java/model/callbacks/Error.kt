package model.callbacks

interface Error {
    fun onError(t: Throwable);
}
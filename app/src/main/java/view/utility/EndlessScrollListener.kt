package view.utility

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

interface OnLoadMoreListener {
    fun onLoadMore()
}

data class LoadMore(var linearLayoutManager: LinearLayoutManager, var loadMoreListener: OnLoadMoreListener)

class EndlessScrollListener(loadMore: LoadMore) : RecyclerView.OnScrollListener() {

    var linearLayoutManager = loadMore.linearLayoutManager
    var onLoadMoreListener = loadMore.loadMoreListener

    private var isLoading: Boolean = false
    private var visibleThreshold = 5
    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        totalItemCount = linearLayoutManager.getItemCount()
        lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
        if (!isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
            if (onLoadMoreListener != null) {
                onLoadMoreListener.onLoadMore()
            }
            isLoading = true
        }
    }
}
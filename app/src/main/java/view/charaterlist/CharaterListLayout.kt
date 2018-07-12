package view.charaterlist

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.app.starwars.R
import kotlinx.android.synthetic.main.layout_character_list.view.*
import model.data.pojo.Characters
import presenter.characterslist.CharacterListPresenter
import view.CharacterListView
import view.utility.EndlessScrollListener
import view.utility.LoadMore

class CharaterListLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet)
    : ConstraintLayout(context, attrs), CharacterListView {

    var pageItem = 1;
    private var presenter: CharacterListPresenter;
    private lateinit var characterAdapter: CharacterListAdapter

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_character_list, this, true)
        presenter = CharacterListPresenter(this)
        initCharacterList()
    }

    private fun initCharacterList() {
        var viewManager = LinearLayoutManager(context)
        characterAdapter = CharacterListAdapter()

        characterlist_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = characterAdapter
        }

        val linearLayoutManager = characterlist_recyclerview.getLayoutManager() as LinearLayoutManager
        val loadMoreListener = this
        characterlist_recyclerview.addOnScrollListener(EndlessScrollListener(LoadMore(linearLayoutManager, loadMoreListener)))
    }

    override fun getCharacterList() {
        presenter.getCharacterList(pageItem)
    }

    override fun setCharacters(characters: Characters) {
        handleProgress(View.GONE)
        characterAdapter.setCharacters(characters)
        pageItem++
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.onAttachToWindow()
        handleProgress(View.VISIBLE)
        getCharacterList()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.onDetachedFromWindow()
    }

    override fun getViewContext(): Context {
        return context
    }

    override fun handleProgress(status: Int) {
        if (pageItem == 1) {
            characterlist_progress.visibility = status
        }
    }

    override fun onLoadMore() {
        getCharacterList()
    }
}
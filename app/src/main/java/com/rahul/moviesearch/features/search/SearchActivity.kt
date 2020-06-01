package com.rahul.moviesearch.features.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahul.moviesearch.R
import com.rahul.moviesearch.component.DialogExt
import com.rahul.moviesearch.component.makeVisible
import com.rahul.moviesearch.features.search.viewmodel.SearchRowViewModel
import com.rahul.moviesearch.features.search.viewmodel.SearchViewModel
import com.rahul.moviesearch.utils.NetworkUtils
import com.rahul.moviesearch.utils.filterEmpty
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchActivity : AppCompatActivity() {
    private val viewModel by viewModel<SearchViewModel>()
    private var rowViewModels: ArrayList<SearchRowViewModel>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        observeList()
        fetchData()
    }

    /**
     * observer to check live data changes
     */
    private fun observeList() {
        viewModel?.movieList.observe(this, androidx.lifecycle.Observer {
            changeState(ViewState.SHOW_LIST)
            initRecyclerView()
        })
    }

    /**
     * load recyclerview with latest changes
     */
    private fun initRecyclerView() {
        if (viewModel.pagination.first == 1) {
            searchRecyclerView.adapter = SearchAdapter(rowViewModels)
            searchRecyclerView.layoutManager = GridLayoutManager(this@SearchActivity, 2)
            // Pagination
            searchRecyclerView.addOnScrollListener(recyclerViewOnScrollListener)
        }
        viewModel.movieList.value?.map {
            (searchRecyclerView.adapter as SearchAdapter).add(SearchRowViewModel(it))
        }
        viewModel.updatePageCount()
    }

    /**
     * fetch data from the server
     */
    private fun fetchData() {
        if (!NetworkUtils.isNetworkAvailable(this))
            DialogExt(this).buildDialog(getString(R.string.network_not_available)) {
                fetchData()
            }
        else
            fetchMovieList()
    }

    /**
     * fetch data from the server
     */
    private fun fetchMovieList() {
        if (viewModel.pagination.second) {
            if (viewModel.fetchingFirstPage()) changeState(ViewState.LOADER) else changeState(
                ViewState.BOTTOM_LOADER
            )
            viewModel?.fetchMovieList("bat", viewModel.pagination.first.filterEmpty())
        }
    }

    /**
     * change the state of this view
     * show loader or show list or show bottom loader
     */
    private fun changeState(viewState: ViewState) {
        when (viewState) {
            ViewState.SHOW_LIST -> {
                searchRecyclerView.makeVisible(true)
                mainLoader.makeVisible(false)
                bottomLoader.makeVisible(false)
            }
            ViewState.BOTTOM_LOADER -> {
                searchRecyclerView.makeVisible(true)
                mainLoader.makeVisible(false)
                bottomLoader.makeVisible(true)
            }
            ViewState.BOTTOM_LOADER -> {
                searchRecyclerView.makeVisible(false)
                mainLoader.makeVisible(true)
                bottomLoader.makeVisible(false)
            }
        }
    }

    private val recyclerViewOnScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var canScrollMore = searchRecyclerView.canScrollVertically(1)
                if (!canScrollMore && viewModel.pagination.second) {
                    fetchData()
                }
            }
        }

}

enum class ViewState {
    LOADER,
    BOTTOM_LOADER,
    SHOW_LIST
}

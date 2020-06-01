package com.rahul.moviesearch.features.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
    private var searchKey = "bat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        observeList()
        fetchData(searchKey)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchItem: MenuItem = menu!!.findItem(R.id.search)

        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.queryHint = "Search Movies"
        searchView.setOnQueryTextListener(searchViewListener)
//        searchView.isIconified = false


        return true
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
    private fun fetchData(movieName: String) {
        searchKey = if (movieName.isNullOrEmpty()) "bat" else movieName
        if (!NetworkUtils.isNetworkAvailable(this))
            DialogExt(this).buildDialog(getString(R.string.network_not_available)) {
                fetchData(searchKey)
            }
        else
            fetchMovieList(searchKey)
    }

    /**
     * fetch data from the server
     */
    private fun fetchMovieList(movieName: String) {
        if (viewModel.pagination.second) {
            if (viewModel.fetchingFirstPage()) changeState(ViewState.LOADER) else changeState(
                ViewState.BOTTOM_LOADER
            )
            viewModel?.fetchMovieList(movieName, viewModel.pagination.first.filterEmpty())
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
                    fetchData(searchKey)
                }
            }
        }

    private val searchViewListener =
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return if (newText.isNullOrEmpty()) false
                else {
                    resetList()
                    fetchData(newText)
                    true
                }
            }

        }

    private fun resetList() {
        viewModel.pagination = Pair(1, true);
        (searchRecyclerView.adapter as SearchAdapter).removeAll();
    }

}

enum class ViewState {
    LOADER,
    BOTTOM_LOADER,
    SHOW_LIST,
}

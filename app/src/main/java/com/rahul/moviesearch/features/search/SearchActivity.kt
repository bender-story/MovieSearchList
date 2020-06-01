package com.rahul.moviesearch.features.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.moviesearch.R
import com.rahul.moviesearch.component.DialogExt
import com.rahul.moviesearch.features.search.viewmodel.SearchRowViewModel
import com.rahul.moviesearch.features.search.viewmodel.SearchViewModel
import com.rahul.moviesearch.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
            initRecyclerView()
        })
    }

    /**
     * load recyclerview with latest changes
     */
    private fun initRecyclerView() {
        GlobalScope.launch(Dispatchers.Main) {

            rowViewModels = viewModel?.getSearchRowViewModel() as ArrayList<SearchRowViewModel>?
            searchRecyclerView.adapter = SearchAdapter(rowViewModels)
            searchRecyclerView.layoutManager = GridLayoutManager(this@SearchActivity, 2)
        }

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
        viewModel?.fetchMovieList("bat", 1)
    }
}

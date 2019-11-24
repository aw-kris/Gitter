package com.awkris.test.gitter.view.searchuser

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.awkris.test.gitter.Gitter
import com.awkris.test.gitter.R
import com.awkris.test.gitter.data.model.User
import com.awkris.test.gitter.di.module.SearchUserModule
import com.awkris.test.gitter.view.utils.EndlessScrollListener
import com.awkris.test.gitter.view.utils.showMessage
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_search_user.*
import javax.inject.Inject

class SearchUserFragment : Fragment(), SearchUserContract.View {
    @Inject
    internal lateinit var searchUserPresenter: SearchUserPresenter

    private lateinit var presenter: SearchUserContract.Presenter
    private var layoutManager: LinearLayoutManager? = null
    private lateinit var adapter: SearchUserAdapter

    private val list = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActivityComponent()
        presenter = searchUserPresenter
        searchUserPresenter.setView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        srv_user.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { presenter.searchUser(it) }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

            }
        )
    }

    private fun setAdapter() {
        layoutManager = LinearLayoutManager(activity)
        rcv_searchresult.layoutManager = layoutManager
        adapter = SearchUserAdapter(list, activity as Context)
        rcv_searchresult.adapter = adapter
        rcv_searchresult.addOnScrollListener(
            object : EndlessScrollListener() {
                override fun onLoadMore() {
                    if (presenter.isNextPageAvailable()) {
                        presenter.loadMoreUser()
                    }
                }
            }
        )
    }

    private fun setupActivityComponent() {
        Gitter.get()
            .appComponent
            .plus(SearchUserModule(this))
            .inject(this)
    }

    override fun hideProgressBar() {
        main_progress_bar.visibility = View.GONE
    }

    override fun setEmptyStateList(visibility: Boolean, keyword: String?, isError: Boolean?) {
        if (visibility) {
            empty_state_view.visibility = View.VISIBLE
            rcv_searchresult.visibility = View.INVISIBLE
        } else {
            empty_state_view.visibility = View.INVISIBLE
            rcv_searchresult.visibility = View.VISIBLE
        }
        if (isError != null && isError) {
            empty_state_view.text = resources.getString(R.string.empty_state_error, keyword)
        } else {
            empty_state_view.text = resources.getString(R.string.empty_state_no_result, keyword)
        }
    }

    override fun showProgressBar() {
        main_progress_bar.visibility = View.VISIBLE
    }

    override fun updateData(sourceDataList: List<User>) {
        list.addAll(sourceDataList)
        adapter.notifyDataSetChanged()
    }

    override fun showSnackBar(message: String?) {
        Snackbar.make(
            content_container,
            resources.getString(R.string.empty_state_error, message ?: ""),
            Snackbar.LENGTH_SHORT
        ).showMessage(true)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchUserFragment()
    }
}
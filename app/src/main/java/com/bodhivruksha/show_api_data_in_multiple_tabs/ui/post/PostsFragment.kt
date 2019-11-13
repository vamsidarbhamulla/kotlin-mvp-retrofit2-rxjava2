package com.bodhivruksha.show_api_data_in_multiple_tabs.ui.post

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bodhivruksha.show_api_data_in_multiple_tabs.R
import com.bodhivruksha.show_api_data_in_multiple_tabs.model.Post
import kotlinx.android.synthetic.main.posts_fragment.*
import kotlinx.android.synthetic.main.view_error.*
import java.lang.ref.WeakReference

class PostsFragment: Fragment(), PostsPresenter.View {

    private lateinit var rvAdapter: PostsRvAdapter

    private var postsLists: List<Post>? = ArrayList()
    private var presenter= PostsPresenter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.attachView(this)
        presenter.getListFromApi()
        return inflater.inflate(R.layout.posts_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
    }

    override fun onDestroyView() {
        postsLists = null
        presenter.detachView()
        super.onDestroyView()
    }

    override fun notifyDataSetChanged(postList: List<Post>) {
        rvAdapter.postsList = postList
        rvAdapter.notifyDataSetChanged()
        errorContainer.visibility = View.GONE
    }

    override fun showError() {
        errorContainer.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        rvAdapter = PostsRvAdapter(postsLists ?: emptyList(), PostItemListener(context))

        rvPostsList.layoutManager = LinearLayoutManager(context)
        rvPostsList.adapter = rvAdapter
        rvPostsList.setHasFixedSize(true)
    }

    companion object {
        fun newInstance(title: String) : Fragment {
            val fragment = PostsFragment()
            val args = Bundle()
            args.putString("title", title)
            fragment.arguments = args
            return fragment
        }
    }

    class PostItemListener(context: Context?): PostsRvAdapter.Listener {

        private val TAG = PostItemListener::class.java.simpleName
        private val weakContext : WeakReference<Context?>? = WeakReference(context)

        override fun onClickItem(post: Post) {
            val context: Context? = weakContext?.get()

            if (context != null) {
                Toast.makeText(
                    context,
                    "This post is created by user id: ${post.userId}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d(TAG, "toast post information")
            }
        }
    }
}
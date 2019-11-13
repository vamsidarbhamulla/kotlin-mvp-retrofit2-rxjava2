package com.bodhivruksha.show_api_data_in_multiple_tabs.ui.post

import com.bodhivruksha.show_api_data_in_multiple_tabs.api.ApiServiceInterface
import com.bodhivruksha.show_api_data_in_multiple_tabs.model.Post
import com.bodhivruksha.show_api_data_in_multiple_tabs.ui.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PostsPresenter: BasePresenter<PostsPresenter.View>() {

    private val TAG = PostsPresenter::class.java.simpleName

    private val apiServiceInterface = ApiServiceInterface.create()
    private val subscriptions = CompositeDisposable()

    fun getListFromApi() {
        val subscriber = apiServiceInterface.getPostList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list : List<Post>? ->
                if (list != null){
                    view?.notifyDataSetChanged(list!!)
                } else {
                    view?.showError()
                }
            }

        subscriptions.add(subscriber)
    }

    override fun destroy() {
        subscriptions.clear()
    }

    interface View {
        fun notifyDataSetChanged(postsList: List<Post>)
        fun showError()
    }
}
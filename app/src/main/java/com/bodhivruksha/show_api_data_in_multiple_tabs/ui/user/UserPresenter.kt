package com.bodhivruksha.show_api_data_in_multiple_tabs.ui.user

import android.util.Log
import com.bodhivruksha.show_api_data_in_multiple_tabs.api.ApiServiceInterface
import com.bodhivruksha.show_api_data_in_multiple_tabs.model.User
import com.bodhivruksha.show_api_data_in_multiple_tabs.ui.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserPresenter: BasePresenter<UserPresenter.View>() {

    private val TAG = UserPresenter::class.java.simpleName

    private val apiServiceInterface = ApiServiceInterface.create()
    private val subscriptions = CompositeDisposable()

    fun getListFromApi()  {
        val subscriber = apiServiceInterface.getUserList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list: List<User>? ->
                Log.d(TAG, "users api call is successful")
                if (list != null) {
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
        fun notifyDataSetChanged(userList: List<User>)
        fun showError()
    }
}
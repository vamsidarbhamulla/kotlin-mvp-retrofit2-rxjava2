package com.bodhivruksha.show_api_data_in_multiple_tabs.ui

abstract class BasePresenter<V> {

    protected var view: V? = null

    fun attachView(view: V) {
        this.view = view
        init()
    }

    fun detachView() {
        destroy()
        this.view = null
    }

    open fun init() {}
    open fun destroy() {}


}
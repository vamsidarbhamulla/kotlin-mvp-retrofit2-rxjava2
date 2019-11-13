package com.bodhivruksha.show_api_data_in_multiple_tabs.ui.user

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
import com.bodhivruksha.show_api_data_in_multiple_tabs.model.User
import kotlinx.android.synthetic.main.users_fragment.*
import kotlinx.android.synthetic.main.view_error.*
import java.lang.ref.WeakReference

class UsersFragment: Fragment(), UserPresenter.View {

    private lateinit var rvAdapter: UserRVAdapter

    private var presenter= UserPresenter()

    private var userList: MutableList<User>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.users_fragment, container, false)
        presenter.attachView(this)
        presenter.getListFromApi()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpRecylerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        userList = null
        presenter.detachView()
    }

    override fun notifyDataSetChanged(userList: List<User>) {
        rvAdapter.userList = userList
        rvAdapter.notifyDataSetChanged()
        errorContainer.visibility = View.GONE
    }

    override fun showError() {
        errorContainer.visibility = View.VISIBLE
    }

    private fun setUpRecylerView() {

        rvUsersList.layoutManager = LinearLayoutManager(context)
        rvAdapter = UserRVAdapter(
            userList ?: emptyList(),
            UserItemListener(
                context
            )
        )
//        rvAdapter.setHasStableIds(true)

        rvUsersList.adapter = rvAdapter
        rvUsersList.setHasFixedSize(true)
//        rvUsersList.addItemDecoration(DividerItemDecoration(activity, HORIZONTAL ))
    }

    companion object {
        fun newInstance(title: String) : Fragment {
            val fragment = UsersFragment()
            val args = Bundle()
            args.putString("title", title)
            fragment.arguments = args
            return fragment
        }
    }


    class UserItemListener(context: Context?): UserRVAdapter.Listener {

        private val TAG = UserItemListener::class.java.simpleName
        private val weakContext : WeakReference<Context?>? = WeakReference(context)

        override fun onClickItem(user: User) {
            val context: Context? = weakContext?.get()

            if (context != null) {
                Toast.makeText(
                    context,
                    "User email id: ${user.email} \nUser workplace: ${user.company.name} ",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d(TAG, "toast user information")
            }
        }
    }

}
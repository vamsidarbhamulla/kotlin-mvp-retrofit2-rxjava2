package com.bodhivruksha.show_api_data_in_multiple_tabs.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bodhivruksha.show_api_data_in_multiple_tabs.R
import com.bodhivruksha.show_api_data_in_multiple_tabs.ui.album.AlbumsFragment
import com.bodhivruksha.show_api_data_in_multiple_tabs.ui.post.PostsFragment
import com.bodhivruksha.show_api_data_in_multiple_tabs.ui.user.UsersFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        pagerAdapter = ViewPagerAdapter(
            supportFragmentManager
        )
        val users = "Users"
        val albums = "Albums"
        val posts = "Posts"

        pagerAdapter.addFragment(UsersFragment.newInstance(users), users)
        pagerAdapter.addFragment(PostsFragment.newInstance(posts), posts)
        pagerAdapter.addFragment(AlbumsFragment.newInstance(albums), albums)

        viewPager.adapter = pagerAdapter
        tabs.setupWithViewPager(viewPager)
    }
}

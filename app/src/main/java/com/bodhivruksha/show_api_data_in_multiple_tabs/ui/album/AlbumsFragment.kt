package com.bodhivruksha.show_api_data_in_multiple_tabs.ui.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bodhivruksha.show_api_data_in_multiple_tabs.R

class AlbumsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.albums_fragment, container, false)


    companion object {
        fun newInstance(title: String) : Fragment {
            val fragment = AlbumsFragment()
            val args = Bundle()
            args.putString("title", title)
            fragment.arguments = args
            return fragment
        }
    }
}
package com.timeriver.gogank.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.timeriver.gogank.R
import kotlinx.android.synthetic.main.fragment_main_page_2.*

class MainPage2Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_page_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_page_2_last.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
        tv_page_2_next.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_page_3)
        }
    }

}
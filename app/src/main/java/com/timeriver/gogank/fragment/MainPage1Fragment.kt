package com.timeriver.gogank.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.timeriver.gogank.R
import kotlinx.android.synthetic.main.fragment_main_page_1.*

class MainPage1Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_page_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_page_1.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_page_2)
        }
    }
}
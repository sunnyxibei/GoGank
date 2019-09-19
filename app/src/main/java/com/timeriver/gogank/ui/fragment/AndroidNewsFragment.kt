package com.timeriver.gogank.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.timeriver.gogank.R
import com.timeriver.gogank.ui.adapter.AndroidNewsAdapter
import com.timeriver.gogank.viewmodel.AndroidNewsViewModel
import kotlinx.android.synthetic.main.fragment_android_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AndroidNewsFragment : Fragment() {

    private val androidNewsViewModel: AndroidNewsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_android_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_android_news.layoutManager = LinearLayoutManager(requireContext())

        val adapter = AndroidNewsAdapter(requireContext())
        list_android_news.adapter = adapter

        androidNewsViewModel.normalClassData.observe(this, Observer {
            adapter.submitList(it)
        })

    }
}
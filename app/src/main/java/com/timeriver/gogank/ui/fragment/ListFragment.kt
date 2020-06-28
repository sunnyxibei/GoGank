package com.timeriver.gogank.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.timeriver.gogank.R
import com.timeriver.gogank.ui.activity.BrowserActivity
import com.timeriver.gogank.ui.adapter.NewsAdapter
import com.timeriver.gogank.viewmodel.AndroidNewsViewModel
import kotlinx.android.synthetic.main.fragment_android_news.*
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

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
        Log.d("ListFragment", "onViewCreated")
        list_android_news.layoutManager = LinearLayoutManager(requireContext())
        val adapter = NewsAdapter(requireContext())
        adapter.onItemClickListener = {
            BrowserActivity.start(requireActivity(), it.url.orEmpty())
        }
        list_android_news.adapter = adapter
        androidNewsViewModel.newsLiveData.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        //监听刷新状态当刷新完成之后关闭刷新
        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest {
                if (it.refresh !is LoadState.Loading) {
                    refresh_layout.isRefreshing = false
                }
            }
        }
        refresh_layout.setOnRefreshListener {
            adapter.refresh()
        }
    }
}
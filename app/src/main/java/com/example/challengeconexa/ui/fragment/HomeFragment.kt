package com.example.challengeconexa.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeconexa.utils.Result
import com.example.challengeconexa.databinding.FragmentHomeBinding
import com.example.challengeconexa.ui.adapter.NewsAdapter
import com.example.challengeconexa.ui.view_model.NewsViewModel
import com.example.challengeconexa.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: NewsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val newsAdapter = NewsAdapter { news ->
            //val action = NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(news)
            //findNavController().navigate(action)
        }

        binding.rvUsersHome.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.allNews.observe(viewLifecycleOwner, EventObserver { result ->
            newsAdapter.setList(result)
        })


        viewModel.loadNews()

    }

}
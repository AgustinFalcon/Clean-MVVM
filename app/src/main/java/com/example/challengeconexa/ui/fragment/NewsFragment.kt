package com.example.challengeconexa.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeconexa.databinding.FragmentNewsBinding
import com.example.challengeconexa.ui.adapter.NewsAdapter
import com.example.challengeconexa.ui.view_model.NewsViewModel
import com.example.challengeconexa.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private val viewModel: NewsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val newsAdapter = NewsAdapter { news ->
            val action = NewsFragmentDirections.actionHomeFragmentToDetailNewsFragment(news)
            findNavController().navigate(action)
        }

        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val divider = DividerItemDecoration(requireContext(), LinearLayoutManager(requireContext()).orientation)
        binding.rvNews.addItemDecoration(divider)

        viewModel.allNews.observe(viewLifecycleOwner, EventObserver { result ->
            newsAdapter.submitList(result)
        })

        viewModel.errorNews.observe(viewLifecycleOwner, EventObserver { result ->
            dialogError(result)
        })

        viewModel.failureNews.observe(viewLifecycleOwner, EventObserver { result ->
            dialogError(result)
        })

        viewModel.loadNews()


        binding.searchUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchNews(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.searchNews(it)
                } ?: viewModel.searchNews("")
                return true
            }
        })

    }


    private fun dialogError(message: String) {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle("Atención")
        dialog.setMessage(message)

        dialog.setPositiveButton("Retry") { _,_ ->
            viewModel.loadNews()
        }

        dialog.create().show()
    }

}
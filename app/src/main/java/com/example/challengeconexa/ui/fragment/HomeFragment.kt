package com.example.challengeconexa.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
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

        val divider = DividerItemDecoration(requireContext(), LinearLayoutManager(requireContext()).orientation)
        binding.rvUsersHome.addItemDecoration(divider)

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
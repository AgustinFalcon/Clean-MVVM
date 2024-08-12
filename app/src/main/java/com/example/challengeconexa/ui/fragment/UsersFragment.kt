package com.example.challengeconexa.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeconexa.R
import com.example.challengeconexa.databinding.FragmentUsersBinding
import com.example.challengeconexa.ui.adapter.NewsAdapter
import com.example.challengeconexa.ui.adapter.UserAdapter
import com.example.challengeconexa.ui.view_model.NewsViewModel
import com.example.challengeconexa.ui.view_model.UsersViewModel
import com.example.challengeconexa.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private val viewModel: UsersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val newsAdapter = UserAdapter { user ->
            val action = UsersFragmentDirections.actionUsersFragmentToMapFragment(user.address.geo)
            findNavController().navigate(action)
            //val action = UsersFragmentDirections.actionUsersFragmentToDetailUserFragment(user)
            //findNavController().navigate(action)
        }

        binding.rvUsers.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val divider = DividerItemDecoration(requireContext(), LinearLayoutManager(requireContext()).orientation)
        binding.rvUsers.addItemDecoration(divider)

        viewModel.users.observe(viewLifecycleOwner, EventObserver { result ->
            newsAdapter.submitList(result)
        })



        viewModel.loadUsers()
    }

}
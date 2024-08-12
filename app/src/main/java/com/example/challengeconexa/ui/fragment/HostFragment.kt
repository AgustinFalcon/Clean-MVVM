package com.example.challengeconexa.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.challengeconexa.databinding.FragmentHostBinding


class HostFragment : Fragment() {

    private lateinit var binding: FragmentHostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHostBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnNews.setOnClickListener {
                val action = HostFragmentDirections.actionHostFragmentToNewsFragment()
                findNavController().navigate(action)
            }

            btnUsers.setOnClickListener {
                val action = HostFragmentDirections.actionHostFragmentToUsersFragment()
                findNavController().navigate(action)
            }

            btnMaps.setOnClickListener {
                val action = HostFragmentDirections.actionHostFragmentToNewsFragment()
                findNavController().navigate(action)
            }
        }

    }




}
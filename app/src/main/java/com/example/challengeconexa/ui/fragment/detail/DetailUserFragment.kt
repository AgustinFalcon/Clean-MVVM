package com.example.challengeconexa.ui.fragment.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.challengeconexa.databinding.FragmentDetailUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailUserFragment : Fragment() {

    private lateinit var binding: FragmentDetailUserBinding
    private val args: DetailUserFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailUserBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = args.user


        with(binding) {
            tvName.text = user.firstname
            tvAddress.text = user.address.street
            tvEmail.text = user.email
            tvUserId.text = user.id.toString()
        }

    }
}
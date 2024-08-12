package com.example.challengeconexa.ui.fragment.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.challengeconexa.databinding.FragmentDetailNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailNewsFragment : Fragment() {

    private lateinit var binding: FragmentDetailNewsBinding
    private val args: DetailNewsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailNewsBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val news = args.new

        with(binding) {
            tvNewId.text = news.id.toString()
            tvCategoryId.text = news.category
            tvTitle.text = news.title
        }

    }


}
package com.best.bestbrains.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.best.bestbrains.MainActivity
import com.best.bestbrains.R
import com.best.bestbrains.databinding.FragmentDetailBinding

class DetailFragment: Fragment(R.layout.fragment_detail) {

    lateinit var binding: FragmentDetailBinding

    lateinit var viewModel: DetailViewModel
    lateinit var viewModelFactory: DetailViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)


        val args: DetailFragmentArgs by navArgs()
        (activity as MainActivity).supportActionBar?.title = "${args.user.fullName}"


        viewModelFactory = DetailViewModelFactory(args.user)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}
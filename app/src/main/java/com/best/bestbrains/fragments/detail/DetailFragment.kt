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
import dagger.hilt.android.AndroidEntryPoint

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


        // The correct way for passing args from safe-args would be using a SaveStateHandle
        // (sort of a bundle-logic helper), but here I used ViewModelFactory for demonstration purposes.
        val args: DetailFragmentArgs by navArgs()
        (activity as MainActivity).supportActionBar?.title = "${args.user.fullName}"


        viewModelFactory = DetailViewModelFactory(args.user)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}
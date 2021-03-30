package com.best.bestbrains.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.best.bestbrains.*
import com.best.bestbrains.api.ApiObject
import com.best.bestbrains.ItemAdapter
import com.best.bestbrains.data.UserRepository
import com.best.bestbrains.databinding.FragmentListBinding
import kotlinx.coroutines.*

class ListFragment : Fragment(R.layout.fragment_list) {

    lateinit var binding: FragmentListBinding
    lateinit var viewModel: ListViewModel
    lateinit var viewModelFactory: ListViewModelFactory
    lateinit var repository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater)

        repository = UserRepository(ApiObject.retrofitService)
        viewModelFactory = ListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ListViewModel::class.java)

        // We pass a custom click listener for additional control and separation of concerns
        val itemAdapter = ItemAdapter(ItemAdapter.CustomClickListener {
            viewModel.navigateToDetail(it)
        })

        binding.lifecycleOwner = this
        binding.recyclerView.adapter = itemAdapter

        // It might be an overhead to create a binding adapter for this
        viewModel.users.observe(viewLifecycleOwner) {
            itemAdapter.notifyDataSetChanged()
            itemAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        // A listener which triggers navigating
        viewModel.itemDetail.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(it))
                viewModel.navigateCompleted()
            }
        }

        (activity as MainActivity).supportActionBar?.title = "BestBrain"


        return binding.root
    }

}
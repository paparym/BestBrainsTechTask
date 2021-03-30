package com.best.bestbrains

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.map
import com.best.bestbrains.api.ApiRequests
import com.best.bestbrains.api.User
import com.best.bestbrains.api.retrofit
import com.best.bestbrains.databinding.FragmentListBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import retrofit2.awaitResponse

class ListFragment: Fragment(R.layout.fragment_list) {

    lateinit var backgroundScope: CoroutineScope
    lateinit var binding: FragmentListBinding

    lateinit var viewModel: ListViewModel
    lateinit var viewModelFactory: ListViewModelFactory
    lateinit var repository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)

        repository = UserRepository(retrofit)
        viewModelFactory = ListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ListViewModel::class.java)
        backgroundScope = CoroutineScope(Dispatchers.IO)


        val itemAdapter = ItemAdapter()
        binding.lifecycleOwner = this
        binding.recyclerView.adapter = itemAdapter


//        backgroundScope.launch {
//            val response = UserApi.retrofitService.getPage(1).awaitResponse()
//            if (response.isSuccessful) {
//                val data = response.body()?.data
//                withContext(Dispatchers.Main) {
//                    itemAdapter.submitData(viewModel.users)
//                }
//            }
//        }

            viewModel.users.observe(viewLifecycleOwner) {
                itemAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }




        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        backgroundScope.cancel()
    }
}
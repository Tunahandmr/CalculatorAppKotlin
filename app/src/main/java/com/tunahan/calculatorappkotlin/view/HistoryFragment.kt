package com.tunahan.calculatorappkotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tunahan.calculatorappkotlin.R
import com.tunahan.calculatorappkotlin.util.SwipeToDeleteCallback
import com.tunahan.calculatorappkotlin.adapter.HistoryAdapter
import com.tunahan.calculatorappkotlin.databinding.FragmentHistoryBinding
import com.tunahan.calculatorappkotlin.model.History
import com.tunahan.calculatorappkotlin.viewmodel.CalculatorViewModel


class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding?=null
    private val binding get() = _binding!!

    private lateinit var mCalculatorViewModel: CalculatorViewModel
    private val adapter = HistoryAdapter()

   // private val args by navArgs<HistoryFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        //recyclerview
        val rv = binding.recyclerView
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(requireContext())

        //viewmodel
        mCalculatorViewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)
        mCalculatorViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })


        swipeDelete()

        return binding.root
    }

    private fun swipeDelete(){


        val swipeDeleteCallback = object : SwipeToDeleteCallback(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                val rvId = adapter.historyList.get(position).id



                adapter.historyList.removeAt(position)
                adapter.notifyItemRemoved(position)

                val h =History(rvId, "","")
                mCalculatorViewModel.deleteHistory(h)
            }

        }
        val itemTouchHelper = ItemTouchHelper(swipeDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appName = resources.getString(R.string.app_name)
        //menu
        binding.toolbar2.title=appName
        binding.toolbar2.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar2.setNavigationOnClickListener {
            val a = HistoryFragmentDirections.actionHistoryFragmentToMainFragment()
            Navigation.findNavController(it).navigate(a)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
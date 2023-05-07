package com.example.butterposv2.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.butterposv2.R
import com.example.butterposv2.reciept_recycler_adapter
import com.example.butterposv2.ui.theViewModel


class OrderFragment : Fragment() {

    private lateinit var sharedViewModel: theViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: reciept_recycler_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(theViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_order, container, false)
        recyclerView = view.findViewById(R.id.reciept_Recycler_View)
        adapter = reciept_recycler_adapter(emptyList()) // Pass an empty list initially
        recyclerView.adapter = adapter

        sharedViewModel.getSelectedDataList().observe(viewLifecycleOwner, { dataList ->
            adapter.setData(dataList)
            // Notify the adapter that data has changed
            adapter.notifyDataSetChanged()
        })

        return view
    }

}
/*
class ThirdFragment : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ThirdAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_third, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = ThirdAdapter(emptyList()) // Pass an empty list initially
        recyclerView.adapter = adapter

        sharedViewModel.getSelectedDataList().observe(viewLifecycleOwner, { dataList ->
            adapter.setData(dataList)
            // Notify the adapter that data has changed
            adapter.notifyDataSetChanged()
        })

        return view
    }
}

 */
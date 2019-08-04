package com.hamburger.kotlinqiitareader.ui.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamburger.kotlinqiitareader.R
import com.hamburger.kotlinqiitareader.databinding.ItemsFragmentBinding

class ItemsFragment : Fragment() {

    companion object {
        fun newInstance() = ItemsFragment()
    }

    private lateinit var viewModel: ItemsViewModel

    private val controller: ItemsEpoxyController by lazy {
        ItemsEpoxyController()
    }

    private lateinit var binding: ItemsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.items_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ItemsFragmentBinding.bind(view)
        binding.recyclerView.adapter = controller.adapter
        if (binding.recyclerView.layoutManager == null) {
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ItemsViewModel::class.java)
        viewModel.data.observe(this, Observer {
            controller.setData(it)
        })
        viewModel.load()
    }
}

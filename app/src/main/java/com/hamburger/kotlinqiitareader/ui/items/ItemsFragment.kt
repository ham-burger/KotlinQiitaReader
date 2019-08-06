package com.hamburger.kotlinqiitareader.ui.items

import android.content.Intent
import android.net.Uri
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
import com.hamburger.kotlinqiitareader.service.Constants
import com.hamburger.kotlinqiitareader.service.ItemDTO
import com.hamburger.kotlinqiitareader.ui.item.ItemActivity


class ItemsFragment : Fragment(), ItemsDelegate {

    companion object {
        fun newInstance() = ItemsFragment()
    }

    private lateinit var viewModel: ItemsViewModel

    private val controller: ItemsEpoxyController by lazy {
        ItemsEpoxyController(this)
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
        binding.delegate = this
        binding.recyclerView.adapter = controller.adapter
        if (binding.recyclerView.layoutManager == null) {
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ItemsViewModel::class.java)
        viewModel.data.observe(this, Observer {
            controller.submitList(it)
        })
        viewModel.networkState.observe(this, Observer {
            binding.networkState = it
        })
    }

    override fun onClickItem(item: ItemDTO) {
        context?.let {
            it.startActivity(ItemActivity.newIntent(it, item))
        }
    }

    override fun onClickLogin() {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(Constants.authorizeUrl)
        }
        startActivity(intent)

    }
}

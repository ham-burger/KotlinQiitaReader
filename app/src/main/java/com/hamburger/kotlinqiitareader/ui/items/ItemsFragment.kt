package com.hamburger.kotlinqiitareader.ui.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamburger.kotlinqiitareader.R
import com.hamburger.kotlinqiitareader.databinding.ItemsFragmentBinding
import com.hamburger.kotlinqiitareader.service.dto.ItemDTO
import com.hamburger.kotlinqiitareader.ui.item.ItemActivity
import com.hamburger.kotlinqiitareader.ui.user.UserActivity
import java.lang.ref.WeakReference

class ItemsFragment : Fragment(), ItemsDelegate {
    companion object {
        private const val keyCode = "keyCode"
        fun newInstance(code: String?) = ItemsFragment().also {
            val bundle = Bundle()
            if (!code.isNullOrBlank()) {
                bundle.putString(keyCode, code)
            }
            it.arguments = bundle
        }
    }

    private lateinit var viewModel: ItemsViewModel
    private lateinit var binding: ItemsFragmentBinding
    private val code: String? by lazy {
        arguments?.getString(keyCode, null)
    }

    private val controller: ItemsEpoxyController by lazy {
        ItemsEpoxyController(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.items_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = controller.adapter
        if (binding.recyclerView.layoutManager == null) {
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, ItemsViewModel.Factory(code, WeakReference(this)))
                .get(ItemsViewModel::class.java)
        viewModel.data.observe(this, Observer {
            controller.submitList(it)
        })
        viewModel.networkState.observe(this, Observer {
            binding.networkState = it
        })
    }

    override fun onClickItem(item: ItemDTO) {
        context?.let {
            it.startActivity(ItemActivity.newIntent(it, item.id))
        }
    }

    override fun onSuccessLogin() {
        context?.let {
            Toast.makeText(it, "ログインしました", Toast.LENGTH_SHORT).show()
            it.startActivity(UserActivity.newIntent(it))
        }
    }
}

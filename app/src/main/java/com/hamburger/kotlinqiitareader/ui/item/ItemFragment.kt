package com.hamburger.kotlinqiitareader.ui.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.hamburger.kotlinqiitareader.R
import com.hamburger.kotlinqiitareader.databinding.ItemFragmentBinding

class ItemFragment : Fragment() {
    companion object {
        private const val keyId = "keyId"
        fun newInstance(id: String) =
                ItemFragment().apply {
                    arguments = Bundle().apply {
                        putString(keyId, id)
                    }
                }
    }

    private lateinit var viewModel: ItemViewModel
    private lateinit var binding: ItemFragmentBinding
    private val id by lazy {
        arguments?.getString(keyId, null) ?: ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.item_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, ItemViewModel.Factory(id))
                .get(ItemViewModel::class.java)
        binding.viewModel = viewModel
    }
}

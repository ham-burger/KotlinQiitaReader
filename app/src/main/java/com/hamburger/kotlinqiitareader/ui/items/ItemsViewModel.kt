package com.hamburger.kotlinqiitareader.ui.items

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

class ItemsViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val data = MutableLiveData<List<Int>>().also {
        it.value = (1..100).toList()
    }
}

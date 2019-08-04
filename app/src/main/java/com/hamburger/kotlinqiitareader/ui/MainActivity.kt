package com.hamburger.kotlinqiitareader.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.hamburger.kotlinqiitareader.R
import com.hamburger.kotlinqiitareader.databinding.ActivityMainBinding
import com.hamburger.kotlinqiitareader.ui.items.ItemsFragment
import com.hamburger.kotlinqiitareader.ui.util.FragmentChangeable

class MainActivity : AppCompatActivity(), FragmentChangeable {
    override val fragmentManager: FragmentManager get() = supportFragmentManager
    override val fragmentFoundationId: Int get() = R.id.container

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            replaceFragment(ItemsFragment.newInstance())
        }
    }
}

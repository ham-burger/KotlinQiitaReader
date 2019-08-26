package com.hamburger.kotlinqiitareader.ui.item

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.hamburger.kotlinqiitareader.R
import com.hamburger.kotlinqiitareader.ui.util.FragmentChangeable

class ItemActivity : AppCompatActivity(), FragmentChangeable {
    override val fragmentManager: FragmentManager get() = supportFragmentManager
    override val fragmentFoundationId: Int get() = R.id.container

    private val id by lazy { intent.getStringExtra(keyId) ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (savedInstanceState == null) {
            replaceFragment(ItemFragment.newInstance(id))
        }
    }

    companion object {
        private const val keyId = "keyId"
        fun newIntent(context: Context, id: String): Intent {
            return Intent(context, ItemActivity::class.java).also {
                it.putExtra(keyId, id)
            }
        }
    }
}

package com.hamburger.kotlinqiitareader.ui.item

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hamburger.kotlinqiitareader.R
import com.hamburger.kotlinqiitareader.databinding.ActivityItemBinding
import com.hamburger.kotlinqiitareader.service.dto.ItemDTO

class ItemActivity : AppCompatActivity() {

    private val binding: ActivityItemBinding by lazy {
        DataBindingUtil.setContentView<ActivityItemBinding>(
            this,
            R.layout.activity_item
        )
    }
    private val item by lazy { intent.getSerializableExtra(keyItemDTO) as ItemDTO }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = item.title
        binding.body = item.body
    }

    companion object {
        private const val keyItemDTO = "keyItemDTO"
        fun newIntent(context: Context, itemDTO: ItemDTO): Intent {
            return Intent(context, ItemActivity::class.java).also {
                it.putExtra(keyItemDTO, itemDTO)
            }
        }
    }
}

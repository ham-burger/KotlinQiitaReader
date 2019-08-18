package com.hamburger.kotlinqiitareader.ui.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hamburger.kotlinqiitareader.R

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, UserFragment.newInstance())
                .commitNow()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "ユーザ情報"
    }
}

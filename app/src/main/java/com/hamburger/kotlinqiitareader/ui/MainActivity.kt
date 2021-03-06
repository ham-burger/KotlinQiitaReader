package com.hamburger.kotlinqiitareader.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.hamburger.kotlinqiitareader.BuildConfig
import com.hamburger.kotlinqiitareader.R
import com.hamburger.kotlinqiitareader.service.repository.RepositoryHolder
import com.hamburger.kotlinqiitareader.ui.items.ItemsFragment
import com.hamburger.kotlinqiitareader.ui.user.UserActivity
import com.hamburger.kotlinqiitareader.ui.util.FragmentChangeable

class MainActivity : AppCompatActivity(), FragmentChangeable {
    override val fragmentManager: FragmentManager get() = supportFragmentManager
    override val fragmentFoundationId: Int get() = R.id.container
    private val deepLinkUri: Uri? by lazy { intent.data }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            replaceFragment(ItemsFragment.newInstance(deepLinkUri?.getQueryParameter("code")))
        }
    }

    override fun onResume() {
        super.onResume()
        invalidateOptionsMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.items_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.login -> onClickLogin()
            R.id.user_info -> startActivity(UserActivity.newIntent(this))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.let {
            val isLogin = RepositoryHolder.accessTokenRepository.isLogin
            it.findItem(R.id.login).apply {
                isVisible = !isLogin
            }
            it.findItem(R.id.user_info).apply {
                isVisible = isLogin
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    private fun onClickLogin() {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            val authorizeUrl =
                    "https://qiita.com/api/v2/oauth/authorize?client_id=${BuildConfig.qiitaClientId}&scope=read_qiita"
            data = Uri.parse(authorizeUrl)
        }
        startActivity(intent)
    }
}

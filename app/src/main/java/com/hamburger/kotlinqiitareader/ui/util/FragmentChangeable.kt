package com.hamburger.kotlinqiitareader.ui.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

interface FragmentChangeable {
    val fragmentManager: FragmentManager
    val fragmentFoundationId: Int

    fun pushFragment(fragment: Fragment) = changeFrontFragment(fragment, true)
    fun replaceFragment(fragment: Fragment) = changeFrontFragment(fragment, false)

    private fun changeFrontFragment(fragment: Fragment, needsAddToBackStack: Boolean) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction.replace(fragmentFoundationId, fragment)
        if (needsAddToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }
}
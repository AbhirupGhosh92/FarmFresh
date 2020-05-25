package com.app.farmfresh.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


object Utils {

    fun hideKeyboard(
        context: Context,
        view: View
    ) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showKeyboard(activity: Activity)
    {
        val inputManager: InputMethodManager? =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as?
                    InputMethodManager
        // check if no view has focus:
        val v = activity?.currentFocus ?: return
        inputManager?.showSoftInput(v, 0)
    }

}
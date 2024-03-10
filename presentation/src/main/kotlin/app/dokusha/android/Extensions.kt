package app.dokusha.android

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.openKeyboard() {
    view?.let { activity?.openKeyboard(it) }
}

fun Fragment.closeKeyboard() {
    view?.let { activity?.closeKeyboard(it) }
}

fun Activity.openKeyboard() {
    openKeyboard(currentFocus ?: View(this))
}

fun Activity.closeKeyboard() {
    closeKeyboard(currentFocus ?: View(this))
}

fun Context.openKeyboard(view: View) {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    view.requestFocus()
    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

fun Context.closeKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
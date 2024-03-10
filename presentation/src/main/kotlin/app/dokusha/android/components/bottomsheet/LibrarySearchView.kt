package app.dokusha.android.components.bottomsheet

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SearchView
import app.dokusha.android.R

class LibrarySearchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SearchView(context, attrs) {

    init {
        queryHint = context.getString(R.string.search_hint)
    }

}
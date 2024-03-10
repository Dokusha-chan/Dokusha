package app.dokusha.android.library

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import app.dokusha.android.R
import app.dokusha.android.base.BaseFragment
import app.dokusha.android.components.bottomsheet.LibraryControlBottomSheet
import app.dokusha.android.components.bottomsheet.LibrarySearchView
import app.dokusha.android.databinding.FragmentLibraryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LibraryFragment : BaseFragment<FragmentLibraryBinding>(
    FragmentLibraryBinding::inflate
) {

    private val viewModel: LibraryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
    }

    private fun setupMenu() {
        with(binding.topAppBar) {
            setupSearchView(menu.findItem(R.id.item_search))
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.item_search -> true
                    R.id.item_filter -> {
                        openFilterBottomSheet()
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun setupSearchView(searchMenuItem: MenuItem) {
        with(searchMenuItem.actionView as LibrarySearchView) {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.search(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
    }

    private fun openFilterBottomSheet() {
        LibraryControlBottomSheet().show(parentFragmentManager)
    }


}
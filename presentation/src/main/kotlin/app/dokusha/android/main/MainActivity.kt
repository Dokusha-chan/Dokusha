package app.dokusha.android.main

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import app.dokusha.android.R
import app.dokusha.android.closeKeyboard
import app.dokusha.android.databinding.ActivityMainBinding
import app.dokusha.android.history.HistoryFragment
import app.dokusha.android.library.LibraryFragment
import app.dokusha.android.settings.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewPagerPageChangeCallback: OnPageChangeCallback by lazy {
        object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bottomNavigationView.menu.getItem(position).isChecked = true
                closeKeyboard()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(ActivityMainBinding.inflate(layoutInflater).also {
            binding = it
        }.root)

        setupViewPager()
        setupBottomNavigation()
    }

    private fun setupViewPager() {
        binding.viewPager.apply {
            offscreenPageLimit = 1
            adapter = ViewPagerAdapter(
                supportFragmentManager, lifecycle, listOf(
                    LibraryFragment(),
                    HistoryFragment(),
                    SettingsFragment()
                )
            )
        }
    }

    private fun setupBottomNavigation() {
        with(binding) {
            bottomNavigationView.setOnItemSelectedListener {
                viewPager.currentItem = when (it.itemId) {
                    R.id.item_library -> 0
                    R.id.item_history -> 1
                    R.id.item_settings -> 2
                    else -> viewPager.currentItem
                }
                true
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.viewPager.registerOnPageChangeCallback(viewPagerPageChangeCallback)
    }

    override fun onDestroy() {
        binding.viewPager.unregisterOnPageChangeCallback(viewPagerPageChangeCallback)
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}
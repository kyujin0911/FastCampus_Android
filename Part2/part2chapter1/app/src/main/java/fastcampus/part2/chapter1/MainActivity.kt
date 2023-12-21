package fastcampus.part2.chapter1

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayoutMediator
import fastcampus.part2.chapter1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position -> //ctrl로 넘어가서 어떤 인자를 필요로 하는지 확인 가능
            run{
                tab.text = "position $position"
            }
        }.attach()
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]
        if(currentFragment is WebViewFragment) {
            if (currentFragment.canGoBack()) {
                currentFragment.goBack()
            } else {
                super.onBackPressed()
            }
        }else {
            super.onBackPressed()
        }
    }
}
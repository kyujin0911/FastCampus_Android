package fastcampus.part2.chapter1

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayoutMediator
import fastcampus.part2.chapter1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnTabLayoutNameChanged { // MainActivity는 listener를 통해 OnTabLayoutNameChanged을 듣게 됨
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spf = getSharedPreferences(WebViewFragment.SHARED_PREFERENCE, MODE_PRIVATE)
        val tab0 = spf?.getString("tab0_name", "월요웹툰")
        val tab1 = spf?.getString("tab1_name", "화요웹툰")
        val tab2 = spf?.getString("tab2_name", "수요웹툰")

        binding.viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position -> //ctrl로 넘어가서 어떤 인자를 필요로 하는지 확인 가능
            run{
                tab.text = when(position){
                    0 -> tab0
                    1 -> tab1
                    else -> tab2
                }
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

    override fun nameChanged(position: Int, name: String) {
        val tab = binding.tabLayout.getTabAt(position)
        tab?.text = name
    }
}
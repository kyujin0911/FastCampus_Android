package fastcampus.part2.chapter1

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import fastcampus.part2.chapter1.databinding.FragmentWebviewBinding

class WebViewFragment(private val position: Int): Fragment() {
    private lateinit var binding: FragmentWebviewBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater, container, false)
        return binding.root // return 되는 View가 실제 Fragment에 그려질 View
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //View가 만들어진 이후에 호출되는 함수
        super.onViewCreated(view, savedInstanceState)

        binding.webView.apply {
            webViewClient = WebtoonWebViewClient(binding.progressBar){url ->
                activity?.getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE)?.edit { // 바로 값이 필요한 경우: commit, else: apply
                    putString("tab$position", url)
                }
            }
            settings.javaScriptEnabled = true
            loadUrl("https://comic.naver.com/index")
        }

        binding.backToLastBtn.setOnClickListener {
            val spf = activity?.getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE) // fragment가 아직 activity에 attach 되지 않았다면 해당 코드의 activity는 null
            val url = spf?.getString("tab$position", "")
            if(url.isNullOrEmpty()){
                Toast.makeText(context, "마지막 저장 시점이 없습니다", Toast.LENGTH_SHORT).show()
            } else {
                binding.webView.loadUrl(url)
            }
        }
    }

    fun canGoBack(): Boolean{
        return binding.webView.canGoBack()
    }

    fun goBack(){
        binding.webView.goBack()
    }
}
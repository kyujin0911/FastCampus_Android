package fastcampus.part1.mywebbrowser

import android.content.Intent
import android.content.SharedPreferences.Editor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebViewClient
import fastcampus.part1.mywebbrowser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }
        binding.webView.loadUrl("http://www.google.com")

        binding.uriEditText.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                binding.webView.loadUrl(binding.uriEditText.text.toString())
                true
            }
            false
        }

        registerForContextMenu(binding.webView)
    }

    override fun onBackPressed() {
        if(binding.webView.canGoBack()){
            binding.webView.goBack()
        }
        else{
            super.onBackPressed()
        }
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_google, R.id.action_home -> {
                binding.webView.loadUrl("http://www.google.com")
                return true
            }
            R.id.action_naver -> {
                binding.webView.loadUrl("http://www.naver.com")
                return true
            }
            R.id.action_daum -> {
                binding.webView.loadUrl("http://www.daum.net")
                return true
            }
            R.id.action_call -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:031-123-4567")
                if(intent.resolveActivity(packageManager)!=null){
                    startActivity(intent)
                }
            }
            R.id.action_send_text -> {
                return true
            }
            R.id.action_email -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_share -> {

            }
            R.id.action_browser -> {

            }
        }
        return super.onContextItemSelected(item)
    }
}
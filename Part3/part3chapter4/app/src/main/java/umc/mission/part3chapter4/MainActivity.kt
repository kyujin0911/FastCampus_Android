package umc.mission.part3chapter4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import umc.mission.part3chapter4.databinding.ActivityMainBinding
import umc.mission.part3chapter4.mvvm.MvvmActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
        }

    }

    fun openMVC() {

    }
    fun openMVP() {

    }
    fun openMVVM() {
        startActivity(Intent(this, MvvmActivity::class.java))
    }
    fun openMVI() {

    }
}
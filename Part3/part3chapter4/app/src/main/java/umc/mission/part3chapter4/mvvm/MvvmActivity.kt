package umc.mission.part3chapter4.mvvm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import umc.mission.part3chapter4.databinding.ActivityMvvmBinding
import umc.mission.part3chapter4.mvvm.repository.ImageRepository
import umc.mission.part3chapter4.mvvm.repository.ImageRepositoryImpl

class MvvmActivity: AppCompatActivity() {

    private val viewModel: MvvmViewModel by viewModels {
        MvvmViewModel.MvvmViewModelFactory(ImageRepositoryImpl())
    }

    private lateinit var binding: ActivityMvvmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvvmBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
    }

    fun loadImage(){
        viewModel.loadRandomImage()
    }
}
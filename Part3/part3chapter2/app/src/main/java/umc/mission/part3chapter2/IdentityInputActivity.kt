package umc.mission.part3chapter2

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import umc.mission.part3chapter2.databinding.ActivityIdentityInputBinding
import umc.mission.part3chapter2.util.ViewUtil.hideKeyboard
import umc.mission.part3chapter2.util.ViewUtil.setOnEditorActionListener
import umc.mission.part3chapter2.util.ViewUtil.showKeyboard
import umc.mission.part3chapter2.util.ViewUtil.showKeyboardDelay

class IdentityInputActivity: AppCompatActivity() {

    private lateinit var binding: ActivityIdentityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        initView()
        binding.nameEdit.showKeyboardDelay()
    }

    private fun initView(){
        with(binding){
            nameEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT){
                birthdayLayout.isVisible = true
                birthdayEdit.showKeyboard()
            }

            birthdayEdit.doAfterTextChanged {
                if(birthdayEdit.length() > 7){
                    genderLayout.isVisible = true
                    birthdayEdit.hideKeyboard()
                }
            }

            genderChipGroup.setOnCheckedStateChangeListener{ group, checkIds ->
                if(!telecomLayout.isVisible){
                    telecomLayout.isVisible = true
                }
            }

            telelcomChipGroup.setOnCheckedStateChangeListener{ group, checkIds ->
                if(!phoneLayout.isVisible){
                    phoneLayout.isVisible = true
                    phoneEdit.showKeyboard()
                }
            }

            phoneEdit.doAfterTextChanged {
                if(phoneEdit.length() > 10){
                    confirmBtn.isVisible = true
                    phoneEdit.hideKeyboard()
                }
            }

            phoneEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE){
                if(phoneEdit.length() > 9){
                    confirmBtn.isVisible = true
                    phoneEdit.hideKeyboard()
                }
            }
        }
    }
}
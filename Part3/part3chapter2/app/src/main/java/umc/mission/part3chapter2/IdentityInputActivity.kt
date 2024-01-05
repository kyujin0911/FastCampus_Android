package umc.mission.part3chapter2

import android.content.Intent
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

class IdentityInputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIdentityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        initView()
        binding.nameEdit.showKeyboardDelay()
    }

    private fun initView() {
        with(binding) {
            nameEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
                if(validName()){
                    nameLayout.error = null
                    if(phoneLayout.isVisible){
                        confirmBtn.isVisible = true
                    } else {
                        birthdayLayout.isVisible = true
                        birthdayEdit.showKeyboard()
                    }
                } else {
                    confirmBtn.isVisible = false
                    nameLayout.error = "1자 이상의 한글을 입력해주세요."
                }
            }

            birthdayEdit.doAfterTextChanged {
                if (birthdayEdit.length() > 7) {
                    if(validBirthday()){
                        birthdayLayout.error = null
                        if(phoneLayout.isVisible){
                            confirmBtn.isVisible = true
                        } else {
                            genderLayout.isVisible = true
                            birthdayEdit.hideKeyboard()
                        }
                    } else {
                        confirmBtn.isVisible = false
                        birthdayLayout.error = "생년월일 형식이 다릅니다."
                    }

                }
            }

            birthdayEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE){
                val isValid = validBirthday() && birthdayEdit.length() > 7
                if(isValid){
                    confirmBtn.isVisible = phoneLayout.isVisible // phoneLayout이 Visible일 때만 보이게끔
                    birthdayLayout.error = null
                } else {
                    birthdayLayout.error = "생년월일 형식이 다릅니다."
                }
            }

            genderChipGroup.setOnCheckedStateChangeListener { group, checkIds ->
                if (!telecomLayout.isVisible) {
                    telecomLayout.isVisible = true
                }
            }

            telelcomChipGroup.setOnCheckedStateChangeListener { group, checkIds ->
                if (!phoneLayout.isVisible) {
                    phoneLayout.isVisible = true
                    phoneEdit.showKeyboard()
                }
            }

            phoneEdit.doAfterTextChanged {
                if (phoneEdit.length() > 10) {
                    if(validPhone()){
                        phoneLayout.error = null
                        confirmBtn.isVisible = true
                        phoneEdit.hideKeyboard()
                    } else {
                        confirmBtn.isVisible = false
                        phoneLayout.error = "전화번호 형식이 다릅니다."
                    }
                }
            }

            phoneEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE) {
                    confirmBtn.isVisible = phoneEdit.length() > 9 && validPhone()
                    phoneEdit.hideKeyboard()
            }
        }
    }

    fun onClickDone(){
        if(!validName()){
            binding.nameLayout.error = "1자 이상의 한글을 입력해주세요."
            return
        }

        if(!validBirthday()){
            binding.birthdayLayout.error = "생년월일 형식이 다릅니다."
        }

        if(!validPhone()){
            binding.phoneLayout.error = "전화번호 형식이 다릅니다."
        }

        startActivity(Intent(this, VerifyOtpActivity::class.java))
    }

    private fun validName() = !binding.nameEdit.text.isNullOrBlank()
            && REGEX_NAME.toRegex().matches(binding.nameEdit.text!!)

    private fun validBirthday() = !binding.birthdayEdit.text.isNullOrBlank()
            && REGEX_BIRTHDAY.toRegex().matches(binding.birthdayEdit.text!!)

    private fun validPhone() = !binding.phoneEdit.text.isNullOrBlank()
            && REGEX_PHONE.toRegex().matches(binding.phoneEdit.text!!)


    companion object {
        private const val REGEX_NAME = "^[가-힣]{2,}\$"
        private const val REGEX_BIRTHDAY = "^(19|20)[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])"
        private const val REGEX_PHONE = "^01([016789])([0-9]{3,4})([0-9]{4})"
    }
}
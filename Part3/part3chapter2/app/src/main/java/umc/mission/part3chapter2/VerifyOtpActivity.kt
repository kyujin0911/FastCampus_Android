package umc.mission.part3chapter2

import android.os.Bundle
import android.os.CountDownTimer
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.android.gms.auth.api.phone.SmsRetriever
import umc.mission.part3chapter2.databinding.ActivityVerifyOtpBinding
import umc.mission.part3chapter2.util.ViewUtil.setOnEditorActionListener
import umc.mission.part3chapter2.util.ViewUtil.showKeyboardDelay

class VerifyOtpActivity: AppCompatActivity(), AuthOtpReceiver.OtpReceiveListener {
    private lateinit var binding: ActivityVerifyOtpBinding
    private var smsReceiver: AuthOtpReceiver? = null
    // 3분, 1초마다 onTick 함수 실행
    private var timer: CountDownTimer? = object : CountDownTimer((3* 60 * 1000), 1000){
        override fun onTick(p0: Long) {
            val min = (p0 / 1000) / 60
            val sec = (p0 / 1000) % 60
            binding.timerTV.text = "$min:${String.format("%02d", sec)}"
        }

        override fun onFinish() {
            binding.timerTV.text = ""
            Toast.makeText(this@VerifyOtpActivity, "입력 제한시간을 초과하였습니다.\n다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.otpCodeEdit.showKeyboardDelay()
        startSmsReceiver()
    }

    override fun onDestroy() {
        clearTimer()
        stopSmsReceiver()
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        initView()
    }

    private fun initView(){
        startTimer()
        with(binding){
            otpCodeEdit.doAfterTextChanged {
                if( otpCodeEdit.length() >= 6){
                    stopTimer()
                    Toast.makeText(this@VerifyOtpActivity, "인증번호 입력이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            otpCodeEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE){

            }
        }
    }

    private fun startTimer(){
        timer?.start()
    }

    private fun stopTimer(){
        timer?.cancel()
    }

    private fun clearTimer(){
        stopTimer()
        timer = null
    }

    private fun startSmsReceiver(){
        SmsRetriever.getClient(this).startSmsRetriever().also { task ->
            task.addOnSuccessListener {
                if(smsReceiver == null){
                    smsReceiver = AuthOtpReceiver().apply {
                        setOtpListener(this@VerifyOtpActivity)
                    }
                }
                registerReceiver(smsReceiver, smsReceiver!!.doFilter())
            }

            task.addOnFailureListener {
                stopSmsReceiver()
            }
        }
    }

    private fun stopSmsReceiver(){
        if(smsReceiver != null){
            unregisterReceiver(smsReceiver)
            smsReceiver = null
        }
    }

    override fun onOtpReceived(otp: String) {
        binding.otpCodeEdit.setText(otp)
    }
}
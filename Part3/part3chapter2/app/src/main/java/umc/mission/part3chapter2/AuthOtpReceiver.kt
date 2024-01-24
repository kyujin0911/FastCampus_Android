package umc.mission.part3chapter2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.http.UrlRequest.Status
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes

/**
 * 1. 문자 내용이 140바이트를 초과하면 안된다.
 * 2. 문자 맨 앞에 <#>가 포함이 되어야한다.
 * 3. 맨 마지막에 앱을 식별하는 11글자 해시코드가 존재해야함.
 */

class AuthOtpReceiver: BroadcastReceiver() {

    private var listener: OtpReceiveListener? = null

    fun setOtpListener(receiveListener: OtpReceiveListener){
        this.listener = receiveListener
    }

    fun doFilter() = IntentFilter().apply {
        addAction(SmsRetriever.SMS_RETRIEVED_ACTION)
    }

    interface OtpReceiveListener {
        fun onOtpReceived(otp: String)
    }

    companion object{
        private const val PATTERN = "^<#>.*\\[Sample\\].+\\[\\d{6}\\].+\$"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action == SmsRetriever.SMS_RETRIEVED_ACTION){
            intent.extras?.let{ bundle ->
                val status = bundle.get(SmsRetriever.EXTRA_STATUS) as com.google.android.gms.common.api.Status
                when(status.statusCode){
                    CommonStatusCodes.SUCCESS -> {
                        val otpSms = bundle.getString(SmsRetriever.EXTRA_SMS_MESSAGE, "")
                        if(listener != null && otpSms.isNotEmpty()){
                            val otp = PATTERN.toRegex().find(otpSms)?.destructured?.component1()
                            if(!otp.isNullOrBlank()){
                                listener!!.onOtpReceived(otp)
                            }
                        }
                    }
                }
            }
        }
    }

}
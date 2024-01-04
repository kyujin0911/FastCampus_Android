package umc.mission.part3chapter2.bindingapater

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import umc.mission.part3chapter2.R

@BindingAdapter(value = ["code_text", "code_index"])
fun ImageView.setPin(codeText: CharSequence?, index: Int){
    if(codeText != null){
        if (codeText.length > index){
            setImageResource(R.drawable.baseline_circle_black_24)
            Log.d("codeText", codeText.toString())
        } else {
            setImageResource(R.drawable.baseline_circle_gray_24)
        }
    }
}
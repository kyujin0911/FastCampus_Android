package umc.mission.part2chapter2

import android.app.ProgressDialog.show
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import umc.mission.part2chapter2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_RECORD_AUDIO_CODE = 200
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recoredBtn.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission( // 앱 처음 실행 시 권한 혀용한 적이 없으니 넘어감
                    this,
                    android.Manifest.permission.RECORD_AUDIO
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // You can use the API that requires the permission.

                }
                ActivityCompat.shouldShowRequestPermissionRationale( // 권한 팝업을 띄운 적이 있는 경우는 true
                    this, android.Manifest.permission.RECORD_AUDIO) -> {
                    showPermissionRationalDialog()
                }
                else -> { // 시스템 팝업을 띄움
                    // You can directly ask for the permission.
                    ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.RECORD_AUDIO),
                        REQUEST_RECORD_AUDIO_CODE)
                }
            }
        }
    }

    private fun showPermissionRationalDialog(){ //Rational: 원리
        AlertDialog.Builder(this)
            .setMessage("녹음 권한을 켜주셔야지 앱을 정상적으로 사용할 수 있습니다.")
            .setPositiveButton("권한 허용하기"){ _, _ ->
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.RECORD_AUDIO),
                    REQUEST_RECORD_AUDIO_CODE)
            }
            .setNegativeButton("취소"){ dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .show()
    }

    private fun showPermissionSettingDialog(){ //Rational: 원리
        AlertDialog.Builder(this)
            .setMessage("녹음 권한을 켜주셔야지 앱을 정상적으로 사용할 수 있습니다. 앱 설정 화면으로 진입하셔서 권한을 켜주세요.")
            .setPositiveButton("권한 변경하러 가기"){ _, _ ->
                navigateToAppSetting()
            }
            .setNegativeButton("취소"){ dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .show()
    }

    private fun navigateToAppSetting(){
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", packageName, null) // 해당 앱에 대한 디테일 설정 화면으로 넘어감
        }
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val audioRecordPermissionGranted = requestCode == REQUEST_RECORD_AUDIO_CODE
                && grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED

        if(audioRecordPermissionGranted){

        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.RECORD_AUDIO)){ // 권한 팝업이 떴지만 거절을 한 경우 교육을 위한 교육용 팝업을 띄움
                showPermissionRationalDialog()
            }
            else { // shouldShowRequestPermissionRationale 즉, 교육용 팝업이 필요없다는 것은 팝업을 봤음에도 권한을 허용하지 않았다는 뜻
                showPermissionSettingDialog() // 권한 허용을 위한 팝업을 반복적으로 띄우는 것은 사용자에게 피로감 유발 따라서 직접 설정에서 허용하도록 유도하는 팝업 띄움
            }
        }
    }
}
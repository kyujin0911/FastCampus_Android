package umc.mission.part2chapter2

import android.app.ProgressDialog.show
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Color
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import umc.mission.part2chapter2.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity(), OnTimerTickListener {
    companion object {
        private const val REQUEST_RECORD_AUDIO_CODE = 200
    }

    private enum class State{
        RELEASE, RECORDING, PLAYING
    }
    private lateinit var timer: Timer
    private lateinit var binding: ActivityMainBinding
    private var recorder: MediaRecorder? = null
    private var player: MediaPlayer? = null
    private var fileName: String = ""
    private var state: State = State.RELEASE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fileName = "${externalCacheDir?.absolutePath}/audiorecordtest.3gp"
        timer = Timer(this)

        binding.recoredBtn.setOnClickListener {
            when(state){
                State.RELEASE -> {
                    record()
                }
                State.RECORDING -> {
                    onRecord(false)
                }
                State.PLAYING -> {

                }
            }
        }

        binding.playBtn.setOnClickListener {
            when(state){
                State.RELEASE -> {
                    onPlay(true)
                }
                else -> {
                    // do nothing
                }
            }
        }

        binding.stopBtn.setOnClickListener {
            when(state){
                State.PLAYING -> {
                    onPlay(false)
                }
                else -> {
                    // do nothing
                }
            }
        }
    }

    private fun record(){
        when {
            ContextCompat.checkSelfPermission( // 앱 처음 실행 시 권한 혀용한 적이 없으니 넘어감
                this,
                android.Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED -> {
                onRecord(true)

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

    private fun onPlay(isStart: Boolean) = if (isStart) startPlaying() else stopPlaying()

    // 릴리즈 -> 녹음중 -> 릴리즈(저장)
    // 릴리즈 -> 재생 -> 릴리즈

    private fun onRecord(isStart: Boolean) = if(isStart){
        startRecording()
    } else{
        stopRecording()
    }

    private fun startRecording(){
        state = State.RECORDING
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC) // 마이크를 사용하겠다는 의미
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP) // 출력 파일 형식 지정
            setOutputFile(fileName) // 지정한 파일로 출력
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException){ // 출력 파일을 쓸 때 용량 부족, 파일 권한 등으로 인한 에러 발생 가능
                Log.e("APP", "prepare() failed $e")
            }

            start()
        }
        binding.waveformView.clearData()
        timer.start()

        binding.recoredBtn.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.baseline_pause_24)
        )

        binding.recoredBtn.imageTintList = ColorStateList.valueOf(Color.BLACK)
        binding.playBtn.isEnabled =false
        binding.playBtn.alpha = 0.3f // playBtn을 사용하지 못한다는 것을 표현하기 위해 playBtn을 살짝 흐리게 설정
    }

    private fun stopRecording(){
        recorder?.apply {
            stop()
            release()
        }
        recorder = null

        timer.stop()
        state = State.RELEASE

        binding.recoredBtn.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.baseline_fiber_manual_record_24)
        )
        binding.recoredBtn.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red))
        binding.playBtn.isEnabled = true
        binding.playBtn.alpha = 1.0f
    }

    private fun startPlaying(){
        state = State.PLAYING

        player = MediaPlayer().apply {
            setDataSource(fileName)
            try {
                prepare()
            } catch (e: IOException){
                Log.e("APP", "media player prepare failed $e")
            }

            start()
        }

        binding.waveformView.clearWave()
        timer.start()

        player?.setOnCompletionListener { // media player의 재생이 완료되었을 때 호출이 되는 리스너
            stopPlaying()
        }

        binding.recoredBtn.isEnabled = false
        binding.recoredBtn.alpha = 0.3f
    }

    private fun stopPlaying(){
        state = State.RELEASE

        player?.release()
        player= null

        timer.stop()

        binding.recoredBtn.isEnabled = true
        binding.recoredBtn.alpha = 1.0f
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
            onRecord(true)
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.RECORD_AUDIO)){ // 권한 팝업이 떴지만 거절을 한 경우 교육을 위한 교육용 팝업을 띄움
                showPermissionRationalDialog()
            }
            else { // shouldShowRequestPermissionRationale 즉, 교육용 팝업이 필요없다는 것은 팝업을 봤음에도 권한을 허용하지 않았다는 뜻
                showPermissionSettingDialog() // 권한 허용을 위한 팝업을 반복적으로 띄우는 것은 사용자에게 피로감 유발 따라서 직접 설정에서 허용하도록 유도하는 팝업 띄움
            }
        }
    }

    override fun onTick(duration: Long) {
        val mills = duration % 1000
        val second = (duration / 1000) % 60
        val minute = (duration / 1000) / 60

        binding.timerTextView.text = String.format("%02d:%02d.%02d", minute, second, mills / 10)


        if(state == State.PLAYING){
            binding.waveformView.replayAmplitude(duration.toInt())
        } else  if(state == State.RECORDING){
            binding.waveformView.addAmplitude(recorder?.maxAmplitude?.toFloat() ?: 0f)
        }
    }
}
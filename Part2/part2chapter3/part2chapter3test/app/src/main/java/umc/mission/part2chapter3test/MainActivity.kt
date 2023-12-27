package umc.mission.part2chapter3test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import okhttp3.*
import umc.mission.part2chapter3test.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.ServerSocket
import java.net.Socket

// HttpURLConnection은 내부적으로 okhttp를 사용
// okhttp를 사용하면 소켓 프로그래밍을 직접 안해도 되서 편리함

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = OkHttpClient()
        var serverHost = ""

        binding.serverHostEditText.addTextChangedListener {
            serverHost = it.toString()
        }

        binding.confirmBtn.setOnClickListener {
            val request: Request = Request.Builder()
                .url("http://$serverHost:8080")
                .build()

            val callback = object : Callback {
                // onFailure: 요청 자체가 실패했거나 통신과정에서 오료 발생 시
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("Client", e.toString())
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "수신에 실패했습니다", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    // isSuccessful: 여기서 [200..300)은 HTTP 응답 코드의 성공 범위를 나타냅니다.
                    // HTTP 응답 코드가 200에서 299까지인 경우, 이는 서버가 요청을 성공적으로 처리했음을 나타냅니다.
                    // 따라서 함수나 메서드가 true를 반환한다는 것은
                    // 해당 HTTP 응답 코드가 성공 범위에 속한다는 것을 의미합니다.
                    if (response.isSuccessful) {
                        val response = response.body?.string()
                        // string(): response의 body 객체를 string으로 return
                        // toString은 body의 해시값을 받아옴
                        runOnUiThread {
                            binding.informationTextView.isVisible = true
                            binding.informationTextView.text = response

                            binding.confirmBtn.isVisible = false
                            binding.serverHostEditText.isVisible = false
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "수신에 실패했습니다", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
            // enqueue(): 요청을 나중에 어느 시점에서 실행하도록 예약합니다. 디스패처(dispatcher)는 요청이 언제 실행될지를 정의하며,
            // 일반적으로는 현재 실행 중인 다른 요청이 없는 경우 즉시 실행됩니다.
            // 이 클라이언트는 이후에 responseCallback을 호출하여 HTTP 응답 또는 실패 예외를 반환합니다
            client.newCall(request).enqueue(callback)
        }


        // client가 새로운 request를 server에게 전달
        // excute(): 즉시 요청을 호출하고, 응답이 처리될 때까지 블록되거나 오류가 발생할 때까지 대기
        // return: Response
        //val response = client.newCall(request).execute()

        /*Thread{
            try {
                // 애뮬레이터의 로컬호스트를 사용하기 위해 10.0.0.2 사용
                val socket = Socket("10.0.2.2", 8080)
                val printer = PrintWriter(socket.getOutputStream())
                val reader = BufferedReader(InputStreamReader(socket.getInputStream()))

                printer.println("GET / HTTP/1.1")
                printer.println("Host: 192.168.219.116:8080")
                printer.println("User-Agent: android")
                printer.println("\r\n")
                printer.flush()

                var input: String? = "-1"
                while (input != null) {
                    input = reader.readLine()
                    Log.e("Client", input)
                }
                reader.close()
                printer.close()
                socket.close()
            } catch (e: Exception){
                Log.e("CLIENT", e.toString())
            }
        }.start()*/

    }
}
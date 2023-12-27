package umc.mission.part2chapter3test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.ServerSocket
import java.net.Socket

// HttpURLConnection은 내부적으로 okhttp를 사용
// okhttp를 사용하면 소켓 프로그래밍을 직접 안해도 되서 편리함

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread{
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
        }.start()

    }
}
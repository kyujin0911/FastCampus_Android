package umc.mission.part2chapter3test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread {
            val port = 8080
            val server = ServerSocket(port)

            val socket = server.accept()

            val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
            val printer = PrintWriter(socket.getOutputStream())

            var input: String? = "-1"
            while (input != null && input != "")
                input = reader.readLine()

            Log.e("SERVER", "READ DATA $input")

            printer.println("HTTP/1.1 200 OK") // 규격
            printer.println("Content-Type: text/html\r\n") // header

            printer.println("<h1>Hello World</h1>")
            printer.println("\r\n")
            printer.flush()

            printer.close()
            reader.close()
            socket.close()
        }.start()
    }
}
package kr.co.fastcampus.part4.chapter3_5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part4.chapter3_5.ui.theme.SurfaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SurfaceTheme {
                Greeting("Android")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    // Surface는 뒤에 터치를 전달하지 않는 특징을 가짐
    // 기본적으로 UI를 구축하는 기반이 됨
    // 아래 Text의 margin을 5dp 주려면 Text의 Margin 설정이 아닌
    // Surface의 padding을 5dp 부여해야 함
    // color를 MaterialTheme의 Color로 설정하면 content color는 미리 지정되어있는 on~의 Color로 설정된다
    Surface(
        border = BorderStroke(width = 2.dp, color = Color.Magenta),
        modifier = Modifier.padding(5.dp),
        elevation = 10.dp,
        shape = CircleShape,
        color = MaterialTheme.colors.error
    ) {
        Text(
            text = "Hello $name!",
            modifier = Modifier.padding(8.dp)
        )
    }

    // 스텝 1: Surface에 elevation을 설정합시다.

    // 스텝 2: border의 값을 설정해봅시다.

    // 스텝 3: Surface의 shape도 설정해봅시다.

    // 스텝 4: color를 지정합시다.
    // MaterialTheme.colors에서 primary, error,
    // background, surface, secondary 등을 지정해봅시다.
    // contentColor가 자동으로 선택됩니다.
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SurfaceTheme {
        Greeting("Android")
    }
}
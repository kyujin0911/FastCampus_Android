package fastcampus.part1.chapter7_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var bgBtn: Button
    lateinit var btBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bgBtn = findViewById<Button>(R.id.changeBg)
        registerForContextMenu(bgBtn)
        btBtn = findViewById<Button>(R.id.changeBtn)
        registerForContextMenu(btBtn)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if(v == bgBtn){
            menuInflater.inflate(R.menu.menu, menu)
        }
        else{
            menuInflater.inflate(R.menu.btn_menu, menu)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.changeRed -> {

            }
        }

        return super.onContextItemSelected(item)
    }
}
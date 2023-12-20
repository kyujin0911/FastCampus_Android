package fastcampus.part1.chapter7

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "word")
data class Word(
    val text: String,
    val mean: String,
    val type: String,
    @PrimaryKey(autoGenerate = true)val id: Int = 0,
) //데이터를 holding 하기 위한 클래스, 반드시 하나 이상의 property 필요, 상속 불가능
    : Parcelable

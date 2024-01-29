package kr.ac.tukorea.part3chapter6.model

data class Image(
    val imageUrl: String
): ListItem {
    override val viewType: ViewType
        get() = ViewType.IMAGE
}

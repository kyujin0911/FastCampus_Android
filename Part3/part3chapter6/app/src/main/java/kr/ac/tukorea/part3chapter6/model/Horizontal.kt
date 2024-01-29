package kr.ac.tukorea.part3chapter6.model

data class Horizontal(
    val title: String,
    val items: List<ListItem>
) : ListItem {
    override val viewType: ViewType
        get() = ViewType.HORIZONTAL
}

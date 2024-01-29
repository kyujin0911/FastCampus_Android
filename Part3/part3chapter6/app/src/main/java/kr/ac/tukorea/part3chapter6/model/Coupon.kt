package kr.ac.tukorea.part3chapter6.model

data class Coupon(
    val imageUrl: String,
    val name: String,
    val coupon: String
):ListItem {
    override val viewType: ViewType
        get() = ViewType.COUPON
}

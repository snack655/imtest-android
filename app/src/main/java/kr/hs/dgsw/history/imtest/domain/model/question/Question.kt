package kr.hs.dgsw.history.imtest.domain.model.question

data class Question(
    val question: String,
    val answer: Int,
    val images: List<String>
)
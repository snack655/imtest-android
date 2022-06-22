package kr.hs.dgsw.history.imtest.domain.model.question

data class Question(
    val answer: Int,
    val images: List<String>,
    val question: String,
    val questionId: Int
)
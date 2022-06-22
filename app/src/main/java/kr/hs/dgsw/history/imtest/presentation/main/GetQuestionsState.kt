package kr.hs.dgsw.history.imtest.presentation.main

import kr.hs.dgsw.history.imtest.domain.model.question.Question

data class GetQuestionsState (
    val isLoading: Boolean = false,
    val questions: List<Question>? = null,
    val error: String = ""
)
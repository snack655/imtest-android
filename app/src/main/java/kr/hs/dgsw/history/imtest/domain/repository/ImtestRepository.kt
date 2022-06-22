package kr.hs.dgsw.history.imtest.domain.repository

import kr.hs.dgsw.history.imtest.domain.model.question.Question

interface ImtestRepository {
    suspend fun getQuestions(): List<Question>
}
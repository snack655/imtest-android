package kr.hs.dgsw.history.imtest.data.repository

import kr.hs.dgsw.history.imtest.data.remote.api.ImtestApi
import kr.hs.dgsw.history.imtest.domain.model.question.Question
import kr.hs.dgsw.history.imtest.domain.repository.ImtestRepository
import javax.inject.Inject

class ImtestRepositoryImpl @Inject constructor(
    private val api: ImtestApi
) : ImtestRepository {
    override suspend fun getQuestions(): List<Question> {
        return api.getQuestions()
    }
}
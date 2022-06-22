package kr.hs.dgsw.history.imtest.data.remote.api

import kr.hs.dgsw.history.imtest.data.remote.dto.QuestionDto
import retrofit2.http.GET

interface ImtestApi {
    @GET("/")
    suspend fun getQuestions(): QuestionDto
}
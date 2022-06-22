package kr.hs.dgsw.history.imtest.domain.usecase.questions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.history.imtest.common.Resource
import kr.hs.dgsw.history.imtest.domain.model.question.Question
import kr.hs.dgsw.history.imtest.domain.repository.ImtestRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetQuestions @Inject constructor(
    private val repository: ImtestRepository
) {
    operator fun invoke(): Flow<Resource<List<Question>>> = flow {
        try {
            emit(Resource.Loading<List<Question>>())
            val result = repository.getQuestions()
            emit(Resource.Success<List<Question>>(result))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Question>>(e.localizedMessage ?: "알 수 없는 오류가 발생했습니다."))
        } catch (e: IOException) {
            emit(Resource.Error<List<Question>>("서버에 도달 할 수 없습니다. 네트워크 상태를 확인해 주세요."))
        }
    }
}
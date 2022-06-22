package kr.hs.dgsw.history.imtest.presentation.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.history.imtest.common.Resource
import kr.hs.dgsw.history.imtest.domain.usecase.questions.GetQuestions
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getQuestions: GetQuestions
) : ViewModel() {
    private val _getQuestionsState = MutableStateFlow<GetQuestionsState>(GetQuestionsState(isLoading = false))
    val getQuestionsState: StateFlow<GetQuestionsState> = _getQuestionsState

    init {
        getAllQuestions();
    }

    private fun getAllQuestions() {
        getQuestions().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _getQuestionsState.value = GetQuestionsState(questions = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _getQuestionsState.value = GetQuestionsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _getQuestionsState.value = GetQuestionsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
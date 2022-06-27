package kr.hs.dgsw.history.imtest.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.history.imtest.R
import kr.hs.dgsw.history.imtest.databinding.ActivityMainBinding
import kr.hs.dgsw.history.imtest.domain.model.question.Question
import kr.hs.dgsw.history.imtest.presentation.adapter.QuestionAdapter
import kr.hs.dgsw.history.imtest.presentation.result.ResultActivity
import java.lang.reflect.ParameterizedType
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    var countAnswer: Int = 0
    lateinit var questions: List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        performDataBinding()

        with(viewModel) {
            EVENT_ON_CLICK_FIRST_IMAGE.observe(this@MainActivity) {
                checkAnswer(0)
                checkLastIndex()
                nextPage()
            }

            EVENT_ON_CLICK_SECOND_IMAGE.observe(this@MainActivity) {
                checkAnswer(1)
                checkLastIndex()
                nextPage()
            }

            lifecycleScope.launchWhenStarted {
                getQuestionsState.collect { state ->
                    if (!state.questions.isNullOrEmpty()) {
                        questions = state.questions
                        initViewPager(questions)
                        binding.pbLoading.visibility = View.GONE
                        binding.vpQuestion.visibility = View.VISIBLE
                    }
                    if (state.isLoading) {
                        binding.pbLoading.visibility = View.VISIBLE
                        binding.vpQuestion.visibility = View.GONE
                    }
                    if (state.error.isNotBlank()) {
                        Toast.makeText(this@MainActivity, state.error, Toast.LENGTH_SHORT).show()
                        binding.pbLoading.visibility = View.GONE
                        binding.vpQuestion.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun nextPage() {
        val current = binding.vpQuestion.currentItem
        binding.vpQuestion.setCurrentItem(current + 1, true)
    }

    private fun checkAnswer(myAnswer: Int) {
        if (questions[binding.vpQuestion.currentItem].answer == myAnswer)
            countAnswer += 1
    }

    private fun initViewPager(questions: List<Question>) {
        binding.vpQuestion.isUserInputEnabled = false
        val questionAdapter = QuestionAdapter()
        binding.vpQuestion.adapter = questionAdapter

        questionAdapter.submitList(questions)
        /*questionAdapter.submitList(
            listOf(
                Question(0, listOf(
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg",
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg"
                ), "셈플입니다 히히호호 한국사1", 1),
                Question(0, listOf(
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg",
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg"
                ), "셈플입니다 히히호호 한국사2", 2),
                Question(0, listOf(
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg",
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg"
                ), "셈플입니다 히히호호 한국사3", 3),
                Question(0, listOf(
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg",
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg"
                ), "셈플입니다 히히호호 한국사4", 4),
                Question(0, listOf(
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg",
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg"
                ), "셈플입니다 히히호호 한국사5", 5),
                Question(0, listOf(
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg",
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg"
                ), "셈플입니다 히히호호 한국사6", 6),
                Question(0, listOf(
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg",
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg"
                ), "셈플입니다 히히호호 한국사7", 7),
                Question(0, listOf(
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg",
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg"
                ), "셈플입니다 히히호호 한국사8", 8),
                Question(0, listOf(
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg",
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg"
                ), "셈플입니다 히히호호 한국사9", 9),
                Question(0, listOf(
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg",
                    "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg"
                ), "셈플입니다 히히호호 한국사10", 10),
            )
        )*/
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.vm = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    private fun checkLastIndex() {
        if (questions.size - 1 == binding.vpQuestion.currentItem) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("answerNum", countAnswer)
            intent.putExtra("lastIdx", questions.size)
            startActivity(intent)
            finish()
        }
    }

    companion object {
        val EVENT_ON_CLICK_FIRST_IMAGE = MutableLiveData<Int>()
        val EVENT_ON_CLICK_SECOND_IMAGE = MutableLiveData<Int>()
    }
}
package kr.hs.dgsw.history.imtest.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import kr.hs.dgsw.history.imtest.R
import kr.hs.dgsw.history.imtest.databinding.ActivityMainBinding
import kr.hs.dgsw.history.imtest.domain.model.question.Question
import kr.hs.dgsw.history.imtest.presentation.adapter.QuestionAdapter
import java.lang.reflect.ParameterizedType
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        performDataBinding()
        initViewPager()

        
    }

    private fun initViewPager() {
        binding.vpQuestion.isUserInputEnabled = false
        val questionAdapter = QuestionAdapter()
        binding.vpQuestion.adapter = questionAdapter
        questionAdapter.submitList(
            listOf(
                Question("김기준 탁구 최민재한테 개처발림", 1,
                    listOf(
                        "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg",
                        "https://i.ytimg.com/vi/1h2HltMeG0g/maxresdefault.jpg"
                    )

                ),
                Question("김기준 탁구 최민재한테 개처발림", 2,
                    listOf(
                        "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg",
                        "https://i.ytimg.com/vi/1h2HltMeG0g/maxresdefault.jpg"
                    )

                ),
                Question("김기준 탁구 최민재한테 개처발림", 1,
                    listOf(
                        "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg",
                        "https://i.ytimg.com/vi/1h2HltMeG0g/maxresdefault.jpg"
                    )

                ),
                Question("김기준 탁구 최민재한테 개처발림", 2,
                    listOf(
                        "https://i.ytimg.com/vi/1h2HltMeG0g/maxresdefault.jpg",
                        "https://i.ytimg.com/vi/SZ1P47nCpGU/maxresdefault.jpg"
                    )
                )
            )
        )
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.vm = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}
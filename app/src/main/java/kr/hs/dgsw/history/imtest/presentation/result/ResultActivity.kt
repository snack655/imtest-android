package kr.hs.dgsw.history.imtest.presentation.result

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import kr.hs.dgsw.history.imtest.R
import kr.hs.dgsw.history.imtest.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        performDataBinding()

        val intent = Intent()
        val count = intent.getIntExtra("answerNum", 0)
        val lastIdx = intent.getIntExtra("lastIdx", 0)
        binding.tvResult.text = "%d/%d".format(count, lastIdx)
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}
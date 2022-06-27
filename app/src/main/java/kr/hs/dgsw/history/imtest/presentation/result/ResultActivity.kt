package kr.hs.dgsw.history.imtest.presentation.result

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import kr.hs.dgsw.history.imtest.R
import kr.hs.dgsw.history.imtest.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        performDataBinding()

        val intent = intent
        val count = intent.getIntExtra("answerNum", 0)
        val lastIdx = intent.getIntExtra("lastIdx", 1)
        binding.tvCount.text = "%d/%d".format(count, lastIdx)

        if (lastIdx / 2 == count) {
            binding.tvDesc.text = "한국인이 맞는지 의심스럽군.."
        } else if (lastIdx / 2 < count) {
            binding.tvDesc.text = "당신 한국인이 맞군요!!"
        } else if (lastIdx / 2 > count) {
            binding.tvDesc.text = "당신 한국인이 아니군요"
        }
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}
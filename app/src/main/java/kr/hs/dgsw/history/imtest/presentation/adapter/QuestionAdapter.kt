package kr.hs.dgsw.history.imtest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.unit.Constraints
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.history.imtest.R
import kr.hs.dgsw.history.imtest.common.Constants
import kr.hs.dgsw.history.imtest.databinding.ItemQuestionBinding
import kr.hs.dgsw.history.imtest.domain.model.question.Question
import kr.hs.dgsw.history.imtest.presentation.adapter.callback.QuestionDiffUtilCallback
import kr.hs.dgsw.history.imtest.presentation.main.MainActivity

class QuestionAdapter : ListAdapter<Question, QuestionAdapter.QuestionViewHolder>(QuestionDiffUtilCallback) {

    inner class QuestionViewHolder(
        private val binding: ItemQuestionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Question) {

            binding.tvQuestion.text = item.question

            Glide.with(binding.ivFirstAnswer.context)
                .load(Constants.BASE_URL + "/" + item.images[0])
                .error(R.drawable.default_img)
                .fitCenter()
                .into(binding.ivFirstAnswer)

            Glide.with(binding.ivSecondAnswer.context)
                .load(Constants.BASE_URL + "/" + item.images[1])
                .error(R.drawable.default_img)
                .fitCenter()
                .into(binding.ivSecondAnswer)

            binding.ivFirstAnswer.setOnClickListener {
                MainActivity.EVENT_ON_CLICK_FIRST_IMAGE.value = 1
            }

            binding.ivSecondAnswer.setOnClickListener {
                MainActivity.EVENT_ON_CLICK_SECOND_IMAGE.value = 2
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_question,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
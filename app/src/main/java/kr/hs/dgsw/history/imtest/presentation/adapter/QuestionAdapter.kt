package kr.hs.dgsw.history.imtest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.history.imtest.R
import kr.hs.dgsw.history.imtest.databinding.ItemQuestionBinding
import kr.hs.dgsw.history.imtest.domain.model.question.Question
import kr.hs.dgsw.history.imtest.presentation.adapter.callback.QuestionDiffUtilCallback

class QuestionAdapter : ListAdapter<Question, QuestionAdapter.QuestionViewHolder>(QuestionDiffUtilCallback) {

    inner class QuestionViewHolder(
        private val binding: ItemQuestionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Question) {

            Glide.with(binding.ivFirstAnswer.context)
                .load(item.images[0])
                .error(R.drawable.default_img)
                .centerCrop()
                .into(binding.ivFirstAnswer)

            Glide.with(binding.ivSecondAnswer.context)
                .load(item.images[1])
                .error(R.drawable.default_img)
                .centerCrop()
                .into(binding.ivFirstAnswer)
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
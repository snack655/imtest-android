package kr.hs.dgsw.history.imtest.presentation.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.history.imtest.domain.model.question.Question

object QuestionDiffUtilCallback : DiffUtil.ItemCallback<Question>() {
    override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean = oldItem == newItem
}
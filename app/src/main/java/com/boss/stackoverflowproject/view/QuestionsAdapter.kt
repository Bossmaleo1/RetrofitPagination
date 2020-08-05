package com.boss.stackoverflowproject.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boss.stackoverflowproject.R
import com.boss.stackoverflowproject.model.Question
import com.boss.stackoverflowproject.model.convertTitle
import com.boss.stackoverflowproject.model.getDate
import kotlinx.android.synthetic.main.question_layout.view.*

class QuestionsAdapter(val questions: ArrayList<Question>, val listener: QuestionsListener): RecyclerView.Adapter<QuestionsAdapter.AdapterViewHolder>() {

    fun addQuestions(newQuestions: List<Question>) {
        val currentLength = questions.size
        questions.addAll(newQuestions)
        notifyItemInserted(currentLength)
    }

    fun clearQuestions() {
        questions.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.question_layout, parent, false),
            listener
        )

    override fun getItemCount() = questions.size

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    class AdapterViewHolder(view: View,val listener: QuestionsListener): RecyclerView.ViewHolder(view) {

        val layout = view.item_layout
        val title = view.item_title
        val score = view.item_score
        val date = view.item_date


        fun bind(question: Question) {
            title.text = convertTitle(question.title)
            score.text = question.score
            date.text = getDate(question.date)

            layout.setOnClickListener{listener.onQuestionClicked(question)}
        }
    }

}
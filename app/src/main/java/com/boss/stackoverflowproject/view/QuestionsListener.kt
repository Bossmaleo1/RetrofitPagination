package com.boss.stackoverflowproject.view

import com.boss.stackoverflowproject.model.Question

interface QuestionsListener {

    fun onQuestionClicked(question:Question)
}
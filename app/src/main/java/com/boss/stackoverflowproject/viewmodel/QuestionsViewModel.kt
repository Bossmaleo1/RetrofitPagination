package com.boss.stackoverflowproject.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boss.stackoverflowproject.model.Question
import com.boss.stackoverflowproject.model.ResponseWrapper
import com.boss.stackoverflowproject.model.StackOverflowService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionsViewModel : ViewModel() {

    val questionsResponse = MutableLiveData<List<Question>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    var page = 0

    fun getNextPage() {
        page++;
        getQuestions()
    }

    fun getFirstPage() {
        page = 1;
        getQuestions()
    }

    private fun getQuestions() {
       StackOverflowService.api.getQuestions(page)
           .enqueue(object :Callback<ResponseWrapper<Question>> {
               override fun onResponse(
                   call: Call<ResponseWrapper<Question>>,
                   response: Response<ResponseWrapper<Question>>
               ) {
                   if(response.isSuccessful) {
                       val questions = response.body()
                       questions?.let {
                           questionsResponse.value = questions.items
                           loading.value = false
                           error.value = null
                       }
                   }
               }

               override fun onFailure(call: Call<ResponseWrapper<Question>>, t: Throwable) {
                   onError(t.localizedMessage)
               }



           })
    }

    private fun onError(message: String) {
        error.value = message
        loading.value = false
    }
}
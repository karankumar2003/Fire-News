package com.example.firenews.screens.saved

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firenews.models.Article
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ViewModel() {

    val savedNewsList: MutableState<List<Article>> = mutableStateOf(emptyList())

    init {
        getSavedNews()
    }

    fun getSavedNews() = viewModelScope.launch {
        val list = mutableListOf<Article>()
        val currentUserId = auth.currentUser?.uid
        firestore.collection("users").document(currentUserId!!)
            .collection("articles").get()
            .addOnSuccessListener { querySnapshot ->
                for (articleDocument in querySnapshot) {
                    list.add(articleDocument.toObject(Article::class.java))
                }
                savedNewsList.value = list
            }
            .addOnFailureListener {
                Log.d("SavedScreen", "Failure to load saved News: ${it.message}")
            }
    }
}
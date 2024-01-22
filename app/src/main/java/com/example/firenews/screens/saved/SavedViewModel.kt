package com.example.firenews.screens.saved

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.firenews.models.Article
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ViewModel() {

    val savedNewsList: MutableState<List<Article>> = mutableStateOf(emptyList())

    fun getNewsInRealTime() {
        val currentUserId = auth.currentUser?.uid
        Log.d("SavedViewModel","$currentUserId")
        firestore.collection("users").document(currentUserId!!).collection("articles")
            .addSnapshotListener{ querySnapshot, firebaseFirestoreException ->
                firebaseFirestoreException?.let {
                    Log.d("SavedViewModel", "realtimeUpdates: ${it.message}")
                    return@addSnapshotListener
                }


                querySnapshot?.let {
                    val list = mutableListOf<Article>()
                    for (articleDocument in querySnapshot) {
                        val article = articleDocument.toObject(Article::class.java)
                        list.add(article)
                    }
                    savedNewsList.value = list
                }



            }
    }


}
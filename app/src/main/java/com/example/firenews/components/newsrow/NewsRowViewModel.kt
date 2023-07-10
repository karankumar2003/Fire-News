package com.example.firenews.components.newsrow

import android.content.Context
import android.util.Log
import android.widget.Toast
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
class NewsRowViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ViewModel() {

    val currentUserId = auth.currentUser?.uid

    val articlesCollectionRef =
        firestore.collection("users").document(currentUserId!!).collection("articles")


    fun handleSaveArticle(newsArticle: Article, context: Context) = viewModelScope.launch {

        val uniqueIdentifier =
            newsArticle.description.toString() + newsArticle.author.toString() + newsArticle.title.toString()

        val article = Article(
            newsArticle.author,
            newsArticle.content,
            newsArticle.description,
            newsArticle.publishedAt,
            newsArticle.source,
            newsArticle.title,
            newsArticle.url,
            newsArticle.urlToImage,
            uniqueIdentifier = uniqueIdentifier
        )

        val list = mutableListOf<Article>()
        var documentId = ""

        articlesCollectionRef.whereEqualTo("uniqueIdentifier", uniqueIdentifier).get()
            .addOnSuccessListener { querySnapshot ->

                querySnapshot?.let {
                    if (!querySnapshot.isEmpty()) {
                        documentId = querySnapshot.documents[0].id
                        for (document in querySnapshot) {
                            list.add(document.toObject(Article::class.java))
                        }
                    }
                    Log.d("NewsRowViewModel", " list size : ${list.size}  ")
                }
            }.addOnSuccessListener {

                if (list.size > 0) {
                    articlesCollectionRef.document(documentId).delete().addOnSuccessListener {
                        Toast.makeText(context, "Article Removed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    articlesCollectionRef
                        .add(article)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Article Saved", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "Failed to save article", Toast.LENGTH_SHORT)
                                .show()
                            Log.d("NewsRow", "Failed to save article ${it.message} ")
                        }
                }

            }


    }


    var isAlreadySaved: MutableState<Boolean?> = mutableStateOf(null)


    fun checkIfAlreadySaved(newsArticle: Article) {
        val list = mutableListOf<Article>()

        val uniqueIdentifier =
            newsArticle.description.toString() + newsArticle.author.toString() + newsArticle.title.toString()

        articlesCollectionRef.whereEqualTo("uniqueIdentifier", uniqueIdentifier).get()
            .addOnSuccessListener { querySnapshot ->

                querySnapshot?.let {
                    if (!querySnapshot.isEmpty()) {
                        for (document in querySnapshot) {
                            list.add(document.toObject(Article::class.java))
                        }
                    }
                    Log.d("NewsRowViewModel", " list size : ${list.size}  ")
                }
            }
            .addOnSuccessListener {
                isAlreadySaved.value = list.size > 0

            }
            .addOnFailureListener {
                Log.d(
                    "NewsRowViewModel",
                    "checkIfAlreadySaved: Could not determine : ${it.message}"
                )
            }
    }

}
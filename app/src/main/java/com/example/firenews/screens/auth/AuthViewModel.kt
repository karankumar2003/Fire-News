package com.example.firenews.screens.auth

import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firenews.R
import com.example.firenews.models.MUser
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val firestore: FirebaseFirestore, private val auth: FirebaseAuth) : ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading


    fun signInWithEmailAndPassword(email: String, password: String, onDone: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(
                                "AuthViewModel",
                                "signInWithEmailAndPassword: Successfully Logged In "
                            )
                            onDone()
                        } else {
                            Log.d(
                                "AuthViewModel",
                                "signInWithEmailAndPassword: Not successful in logging in"
                            )
                        }
                    }
            } catch (e: Exception) {
                Log.d("AuthViewModel", "signInWithEmailAndPassword: ${e.message}")
            }
        }

    fun createUserWithEmailAndPassword(email: String, password: String, onDone: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(
                                "AuthViewModel",
                                "createUserWithEmailAndPassword: Successfully Created User "
                            )
                            val displayName = task.result.user?.email?.split("@")?.get(0)
                            createUser(displayName)
                            onDone()
                        } else {
                            Log.d(
                                "AuthViewModel",
                                "createUserWithEmailAndPassword: Not Successful in Creating User "
                            )
                        }
                    }
            } catch (e: Exception) {
                Log.d("AuthViewModel", "createUserWithEmailAndPassword: ${e.message}")
            }
        }

    private fun createUser(displayName: String?) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val user = MUser(
            userId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            quote = "",
            profession = "",
            id = null
        ).toMap()

        firestore
            .collection("users")
            .document(userId!!)
            .set(user)
            .addOnSuccessListener {
                Log.d(
                    "AuthViewModel",
                    "createUser:  Successful in Creating User in firestore "
                )
            }
            .addOnFailureListener{
                Log.d(
                    "AuthViewModel",
                    "createUserWithEmailAndPassword: Not Successful in Creating User in firestore"
                )
            }

    }


}
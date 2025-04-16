package com.example.instagramclone.firestore

import android.widget.Toast
import com.example.instagramclone.model.User
import com.example.instagramclone.util.Constants
import com.example.instagramclone.view.MainActivity
import com.example.instagramclone.view.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FireStoreClass {
    private val mFireStore = FirebaseFirestore.getInstance()

    fun signInUser(activity: MainActivity){
        mFireStore.collection(Constants.USERS).document(getCurrentUserId()).get().addOnSuccessListener { doc->
            val logged = doc.toObject(User::class.java)
            if(logged != null){
                activity.signInSuccess(logged)
            }
        } .addOnFailureListener {
            Toast.makeText(activity, it.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
    fun registerUser(activity: SignUpActivity, userInfo: User){
        mFireStore.collection(Constants.USERS).document(getCurrentUserId()).set(userInfo, SetOptions.merge()).addOnSuccessListener {
            activity.userRegisteredSuccess()
        }
    }
    fun getCurrentUserId(): String{
        return FirebaseAuth.getInstance().currentUser!!.uid

    }
}
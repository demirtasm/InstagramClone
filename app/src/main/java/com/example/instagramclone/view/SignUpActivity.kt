package com.example.instagramclone.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.instagramclone.R
import com.example.instagramclone.databinding.ActivitySignUpBinding
import com.example.instagramclone.firestore.FireStoreClass
import com.example.instagramclone.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            registerUser()
        }
    }

    fun userRegisteredSuccess() {
        FirebaseAuth.getInstance().signOut()
        finish()
    }
    private fun registerUser() {
        val name: String = binding.edtUserName.text.toString().trim { it <= ' ' }
        val email: String = binding.edtEmail.text.toString().trim { it <= ' ' }
        val password: String = binding.edtPassword.text.toString().trim { it <= ' ' }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val registeredEmail = firebaseUser.email!!
                    val user = User(firebaseUser.uid, name, registeredEmail)
                    FireStoreClass().registerUser(this, user)
                } else {

                }
            }
    }
}
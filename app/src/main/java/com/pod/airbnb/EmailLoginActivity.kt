package com.pod.airbnb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.pod.airbnb.navigation.model.ProfileDTO
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_email_login.*
import kotlinx.android.synthetic.main.activity_email_login.e_email_edittext
import kotlinx.android.synthetic.main.activity_email_login.e_password_edittext
import kotlinx.android.synthetic.main.activity_email_login.quit

class EmailLoginActivity : AppCompatActivity() {

    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_login)
        auth = FirebaseAuth.getInstance()

        quit.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
        signinnext.setOnClickListener {
            signinEmail()
        }
    }

    fun signinEmail(){
        auth?.signInWithEmailAndPassword(e_email_edittext.text.toString(), e_password_edittext.text.toString())
            ?.addOnCompleteListener {
                    task->
                if(task.isSuccessful){
                    // 로그인
                    moveMainPage(task.result?.user)
                }else{
                    // 로그인 실패
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }

    fun moveMainPage(user: FirebaseUser?){
        if (user != null){
            startActivity(Intent(this, MainActivity::class.java))
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            finish()
        }
    }

}

package com.pod.airbnb

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActivity : AppCompatActivity() {

    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        auth = FirebaseAuth.getInstance()

        quit.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }

        signin_next.setOnClickListener {
            signinAndSignup()
        }
    }

    fun signinAndSignup(){
        auth?.createUserWithEmailAndPassword(email_edittext.text.toString(), password_edittext.text.toString())?.addOnCompleteListener {
            task ->
                if(task.isSuccessful){
                    val profileUpdates = userProfileChangeRequest {
                        displayName = name_edittext.text.toString()
                    }
                    auth?.currentUser?.updateProfile(profileUpdates)
                    //아이디가 생성되었을 때 실행되는 코드
                    moveMainPage(task.result?.user)
                }else if(task.exception?.message.isNullOrEmpty()){
                    // 에러 메시지를 띄우면 된다.
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }else{
                    // 성공적으로 로그인
                    signinEmail()
                }
        }
    }


    fun signinEmail(){
        auth?.signInWithEmailAndPassword(email_edittext.text.toString(), password_edittext.text.toString())
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

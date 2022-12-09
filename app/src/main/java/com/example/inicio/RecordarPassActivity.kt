package com.example.inicio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.inicio.R.id.txtEmailCambio
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecordarPassActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordar_pass)

        val txtemail : TextView = findViewById(R.id.txtEmailCambio)
        val btnCambiar : Button = findViewById(R.id.btnCambiar)

        btnCambiar.setOnClickListener(){
            reseteoContra(txtemail.text.toString())
        }

        firebaseAuth = Firebase.auth
    }


    private fun reseteoContra(email: String){
        firebaseAuth.sendPasswordResetEmail(email). addOnCompleteListener { task ->
            if(task.isSuccessful){

                Toast.makeText(baseContext,"Se envió el correo de restablecimiento", Toast.LENGTH_SHORT).show()
            }

            else{
                Toast.makeText(baseContext, "Ocurrió un error en el cambio", Toast.LENGTH_SHORT).show()
                Toast.makeText(baseContext, "Éxito", Toast.LENGTH_SHORT).show()


            }
        }
    }

}
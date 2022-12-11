package com.example.inicio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    //CREAMOS EL PAQUETE DE AUTENTICACION DE FIRE BASE
    private lateinit var firebaseAuth: FirebaseAuth

    //
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Declaración de textos y botones de la interfaz

        val singupbutton: Button = findViewById(R.id.singUpButton)
        val txtemail : TextView = findViewById(R.id.txtemail)
        var txtpassword : TextView = findViewById(R.id.txtpassword)
        val btncrearcuenta : TextView = findViewById(R.id.btnCrearCuenta)
        val btnRecordar : TextView = findViewById(R.id.btnOlvidar)

        //TENEMOS QUE INICIALIZAR LA AUTENTICACION

        firebaseAuth = Firebase.auth

        //Funcionalidad de los botones
        singupbutton.setOnClickListener(){
            singIn(txtemail.text.toString(), txtpassword.text.toString())
        }
        btncrearcuenta.setOnClickListener(){
            val i = Intent(this, CrearCuentaActivity::class.java)
            startActivity(i)
        }
        btnRecordar.setOnClickListener(){
            val i = Intent(this, RecordarPassActivity::class.java)
            startActivity(i)
        }
    }

//FUNCION DEL INICIO DE SESION

    private fun singIn(email : String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            //SI LOS DATOS INGRESADOS SON CORRECTOS
            if (task.isSuccessful){
                val user = firebaseAuth.currentUser
                Toast.makeText(baseContext, "Inicio de sesión exitoso !", Toast.LENGTH_SHORT).show()
                //PARA ACCEDER A layout DE LA SEGUNDA PANTALLA
                val i = Intent(this, MainActivity2::class.java)
                startActivity(i)
            }
            else{
                val user = firebaseAuth.currentUser
                Toast.makeText(baseContext,"Email o Contraseña inválido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val user = findViewById<EditText>(R.id.etLoginUser)
        val pass = findViewById<EditText>(R.id.etLoginPass)
        val btn = findViewById<Button>(R.id.btnLogin)
        val goRegister = findViewById<TextView>(R.id.tvGoToRegister)

        // LOGIN BUTTON
        btn.setOnClickListener {
            val u = user.text.toString()
            val p = pass.text.toString()

            if (u.isEmpty() || p.isEmpty()) {
                Toast.makeText(this, "Isi semua field", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
            }
        }

        // PINDAH KE REGISTER
        goRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
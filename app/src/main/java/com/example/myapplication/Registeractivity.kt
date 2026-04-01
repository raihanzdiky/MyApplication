package com.example.myapplication

import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // INPUT
        val name = findViewById<TextInputEditText>(R.id.etName)
        val email = findViewById<TextInputEditText>(R.id.etEmail)
        val pass = findViewById<TextInputEditText>(R.id.etPassword)
        val confirm = findViewById<TextInputEditText>(R.id.etConfirm)

        val btn = findViewById<Button>(R.id.btnRegister)
        val spinner = findViewById<Spinner>(R.id.spMajor)

        // CHECKBOX
        val cb1 = findViewById<CheckBox>(R.id.cbCoding)
        val cb2 = findViewById<CheckBox>(R.id.cbReading)
        val cb3 = findViewById<CheckBox>(R.id.cbGaming)
        val cb4 = findViewById<CheckBox>(R.id.cbTravel)
        val cb5 = findViewById<CheckBox>(R.id.cbOther)

        // ======================
        // SPINNER DATA
        // ======================
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.major_list,
            android.R.layout.simple_spinner_dropdown_item
        )
        spinner.adapter = adapter

        // ======================
        // REAL-TIME VALIDATION
        // ======================
        name.addTextChangedListener {
            if (it.toString().isEmpty()) {
                name.error = "Name required"
            } else {
                name.error = null
            }
        }

        email.addTextChangedListener {
            if (!Patterns.EMAIL_ADDRESS.matcher(it.toString()).matches()) {
                email.error = "Invalid email"
            } else {
                email.error = null
            }
        }

        confirm.addTextChangedListener {
            if (pass.text.toString() != it.toString()) {
                confirm.error = "Password not match"
            } else {
                confirm.error = null
            }
        }

        // ======================
        // BUTTON CLICK
        // ======================
        btn.setOnClickListener {

            val n = name.text.toString()
            val e = email.text.toString()
            val p = pass.text.toString()
            val c = confirm.text.toString()

            val hobbyCount = listOf(cb1, cb2, cb3, cb4, cb5).count { it.isChecked }

            // VALIDASI
            if (n.isEmpty() || e.isEmpty() || p.isEmpty() || c.isEmpty()) {
                Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(e).matches()) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (p != c) {
                Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (hobbyCount < 3) {
                Toast.makeText(this, "Select at least 3 hobbies", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ALERT DIALOG
            AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Are you sure to register?")
                .setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(this, "Registration Success", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        // LONG PRESS
        btn.setOnLongClickListener {
            Toast.makeText(this, "Long Press Activated", Toast.LENGTH_SHORT).show()
            true
        }
    }
}
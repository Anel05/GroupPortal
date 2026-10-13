package com.example.groupportal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: главный экран создан")

        val etName = findViewById<EditText>(R.id.etName)
        val btnOpen = findViewById<Button>(R.id.btnOpen)
        val btnClear = findViewById<Button>(R.id.btnClear)

        btnOpen.setOnClickListener {
            val name = etName.text.toString().trim()

            if (name.isEmpty()) {
                etName.error = getString(R.string.empty_name)
                etName.requestFocus()

                Toast.makeText(
                    this,
                    R.string.empty_name,
                    Toast.LENGTH_SHORT
                ).show()

                Log.w(TAG, "Попытка перехода без имени")
            } else {
                Log.i(TAG, "Открытие списка для пользователя: $name")

                val intent = Intent(this, ListActivity::class.java)
                intent.putExtra(ListActivity.EXTRA_NAME, name)
                startActivity(intent)
            }
        }

        btnClear.setOnClickListener {
            etName.text.clear()
            etName.error = null
            etName.requestFocus()

            Log.d(TAG, "Поле имени очищено")
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}

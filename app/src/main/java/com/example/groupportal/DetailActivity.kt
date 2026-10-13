package com.example.groupportal

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "DetailActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Log.d(TAG, "onCreate: карточка студента открыта")

        val studentName = intent.getStringExtra(
            ListActivity.EXTRA_STUDENT
        ) ?: getString(R.string.unknown_student)

        val programming = intent.getIntExtra(
            ListActivity.EXTRA_PROGRAMMING, -1
        )
        val math = intent.getIntExtra(
            ListActivity.EXTRA_MATH, -1
        )
        val english = intent.getIntExtra(
            ListActivity.EXTRA_ENGLISH, -1
        )

        if (programming !in 0..100 ||
            math !in 0..100 ||
            english !in 0..100
        ) {
            Log.e(TAG, "Не удалось получить корректные оценки")
            finish()
            return
        }

        val average = (programming + math + english) / 3

        findViewById<TextView>(R.id.tvStudentName).text =
            studentName

        findViewById<TextView>(R.id.tvGrade).text =
            getString(R.string.grade_format, average)

        val groupName = intent.getStringExtra(
            ListActivity.EXTRA_GROUP
        ) ?: getString(R.string.group_value)

        findViewById<TextView>(R.id.tvGroup).text =
            groupName

        findViewById<TextView>(R.id.tvProgrammingGrade).text =
            getString(R.string.subject_grade_format, programming)

        findViewById<TextView>(R.id.tvMathGrade).text =
            getString(R.string.subject_grade_format, math)

        findViewById<TextView>(R.id.tvEnglishGrade).text =
            getString(R.string.subject_grade_format, english)

        Log.d(
            TAG,
            "Студент: $studentName, оценки: " +
                    "программирование=$programming, " +
                    "математика=$math, английский=$english, " +
                    "средний балл=$average"
        )

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            Log.d(TAG, "Возврат к прежнему списку студентов")
            finish()
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

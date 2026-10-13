package com.example.groupportal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ListActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ListActivity"
        const val EXTRA_NAME = "EXTRA_NAME"
        const val EXTRA_STUDENT = "EXTRA_STUDENT"
        const val EXTRA_PROGRAMMING = "EXTRA_PROGRAMMING"
        const val EXTRA_MATH = "EXTRA_MATH"
        const val EXTRA_ENGLISH = "EXTRA_ENGLISH"
        const val EXTRA_GROUP = "EXTRA_GROUP"
    }

    data class Student(
        val name: String,
        val programming: Int,
        val math: Int,
        val english: Int
    ) {
        val average: Double
            get() = (programming + math + english) / 3.0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        Log.d(TAG, "onCreate: список студентов открыт")

        val userName = intent
            .getStringExtra(EXTRA_NAME)
            ?.trim()
            .orEmpty()
            .ifBlank { "Студент" }

        findViewById<TextView>(R.id.tvWelcome).text =
            getString(R.string.welcome_format, userName)

        val students = listOf(
            Student("Асанов Айдос", 94, 90, 92),
            Student("Иванова Анна", 86, 90, 88),
            Student("Серикова Алия", 96, 94, 95),
            Student("Петров Максим", 78, 84, 81)
        )

        val container =
            findViewById<LinearLayout>(R.id.studentsContainer)

        findViewById<TextView>(R.id.tvStudentsCount).text =
            getString(R.string.students_count, students.size)

        container.removeAllViews()

        students.forEachIndexed { index, student ->

            val average = student.average
            val displayedAverage = average.toInt()

            val card = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER_VERTICAL
                setPadding(dp(16), dp(14), dp(16), dp(14))
                setBackgroundResource(R.drawable.bg_main_card)
                elevation = dp(2).toFloat()
                isClickable = true
                isFocusable = true
                contentDescription =
                    "${student.name}, средний балл $displayedAverage"
            }

            val nameView = TextView(this).apply {
                text = student.name
                textSize = 17f
                setTextColor(0xFF1E293B.toInt())
                setTypeface(null, android.graphics.Typeface.BOLD)
            }

            val gradeView = TextView(this).apply {
                text = getString(
                    R.string.grade_format,
                    displayedAverage
                )
                textSize = 14f
                setTextColor(0xFF1565C0.toInt())
                setPadding(0, dp(6), 0, 0)
            }

            card.addView(nameView)
            card.addView(gradeView)

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin =
                    if (index == students.lastIndex) 0 else dp(12)
            }

            container.addView(card, params)

            card.setOnClickListener {
                Log.i(TAG, "Выбран студент: ${student.name}")

                val detailIntent = Intent(
                    this@ListActivity,
                    DetailActivity::class.java
                ).apply {
                    putExtra(EXTRA_STUDENT, student.name)
                    putExtra(EXTRA_PROGRAMMING, student.programming)
                    putExtra(EXTRA_MATH, student.math)
                    putExtra(EXTRA_ENGLISH, student.english)

                    // Значение нужно заменить на название
                    // вашей учебной группы в strings.xml.
                    putExtra(
                        EXTRA_GROUP,
                        getString(R.string.group_value)
                    )
                }

                startActivity(detailIntent)
            }
        }

        Log.d(TAG, "Список сформирован: ${students.size} студентов")
    }

    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density).toInt()

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

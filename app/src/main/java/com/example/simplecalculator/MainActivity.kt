package com.example.simplecalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false
    private lateinit var input: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.input)
        val btn0 = findViewById<Button>(R.id.btn_0)
        val btn1 = findViewById<Button>(R.id.btn_1)
        val btn2 = findViewById<Button>(R.id.btn_2)
        val btn3 = findViewById<Button>(R.id.btn_3)
        val btn4 = findViewById<Button>(R.id.btn_4)
        val btn5 = findViewById<Button>(R.id.btn_5)
        val btn6 = findViewById<Button>(R.id.btn_6)
        val btn7 = findViewById<Button>(R.id.btn_7)
        val btn8 = findViewById<Button>(R.id.btn_8)
        val btn9 = findViewById<Button>(R.id.btn_9)
        val btncham = findViewById<Button>(R.id.btn_cham)
        val btncong = findViewById<Button>(R.id.btn_cong)
        val btntru = findViewById<Button>(R.id.btn_tru)
        val btnnhan = findViewById<Button>(R.id.btn_nhan)
        val btnchia = findViewById<Button>(R.id.btn_chia)
        val btnbang = findViewById<Button>(R.id.btn_bang)
        val btnc = findViewById<Button>(R.id.btn_c)
        val btnce = findViewById<Button>(R.id.btn_ce)
        val btnbs = findViewById<Button>(R.id.btn_bs)


        val listener = View.OnClickListener { v ->
            onDigit(v)
        }

        val opListener = View.OnClickListener { v ->
            onOperator(v)
        }

        btnce.setOnClickListener {
            onClear()
        }

        btn0.setOnClickListener(listener)
        btn1.setOnClickListener(listener)
        btn2.setOnClickListener(listener)
        btn3.setOnClickListener(listener)
        btn4.setOnClickListener(listener)
        btn5.setOnClickListener(listener)
        btn6.setOnClickListener(listener)
        btn7.setOnClickListener(listener)
        btn8.setOnClickListener(listener)
        btn9.setOnClickListener(listener)

        btncong.setOnClickListener(opListener)
        btntru.setOnClickListener(opListener)
        btnnhan.setOnClickListener(opListener)
        btnchia.setOnClickListener(opListener)

        btnbang.setOnClickListener {
            onEqual()
        }
    }
     private fun onDigit(view: View) {
            input.append((view as Button).text)
            lastNumeric = true
        }

     private fun onOperator(view: View) {
            if (lastNumeric && !isOperatorAdded(input.text.toString())) {
                input.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }

    private fun onClear() {
            input.text = ""
            lastNumeric = false
            lastDot = false
        }

    private fun isOperatorAdded(value: String): Boolean {
            return if (value.startsWith("-")) false
            else value.contains("/") || value.contains("*") || value.contains("+") || value.contains(
            "-"
            )
       }
    private fun onEqual() {
        if (lastNumeric) {
            var value = input.text.toString()
            var prefix = ""

            try {
                if (value.startsWith("-")) {
                    prefix = "-"
                    value = value.substring(1)
                }

                if (value.contains("-")) {
                    val splitValue = value.split("-")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    input.text =
                        (one.toInt() - two.toInt()).toString()
                } else if (value.contains("+")) {
                    val splitValue = value.split("+")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    input.text =
                        (one.toInt() + two.toInt()).toString()
                } else if (value.contains("*")) {
                    val splitValue = value.split("*")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    input.text =
                        (one.toInt() * two.toInt()).toString()
                } else if (value.contains("/")) {
                    val splitValue = value.split("/")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    input.text =
                        (one.toInt() / two.toInt()).toString()
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

}

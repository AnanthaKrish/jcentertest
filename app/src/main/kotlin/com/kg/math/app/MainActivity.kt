package com.kg.math.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kg.math.mathlib.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun setUP() {

        var mathlib = MathLib.getInstance()

        var datalist = mathlib.getData()

        val model: MathModel = datalist.first()

        print( mathlib.add(model))

    }
}
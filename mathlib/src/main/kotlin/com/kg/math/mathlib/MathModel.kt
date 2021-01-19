package com.kg.math.mathlib

import org.json.JSONObject
import java.lang.Exception

class MathModel( math: JSONObject) {

    var first: Int = 0
    var second: Int = 0

    init {
        try {
            first = math.getInt("first")
            second = math.getInt("second")
        } catch (e: Exception) {
            print(e.localizedMessage)
        }
    }
}
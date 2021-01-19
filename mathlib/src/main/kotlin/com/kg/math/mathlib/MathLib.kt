package com.kg.math.mathlib

import okhttp3.*
import okio.IOException
import org.json.JSONObject


class MathLib {

    private val client = OkHttpClient()
    private var models = mutableListOf<MathModel>()
    companion object {
        private var instance: MathLib? = null

        @JvmStatic fun getInstance(): MathLib {
            if (instance == null) {
                instance = MathLib()
                instance!!.init();
            }

            return instance!!
        }
    }

    fun init(url: String = "http://demo1851551.mockable.io/getmathdata") {
        fetchData(url)
    }

    fun add(model: MathModel): Int {
        return model.first + model.second
    }

    fun substract(model: MathModel): Int {
        return model.first - model.second
    }
    fun multiply(model: MathModel): Int {
        return model.first * model.second
    }

    fun division(model: MathModel): Int {
        return model.first / model.second
    }

    fun getData(): List<MathModel> {
        return models.toList()
    }

    private fun fetchData(url: String) {

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val jsonData: String = response.body.toString()
                    val jsonObject = JSONObject(jsonData)
                    val resultArray = jsonObject.getJSONArray("result")

                    for (i in 0 until resultArray.length()) {
                        val mathmodel: JSONObject = resultArray.getJSONObject(i)
                        val model = MathModel(mathmodel)
                        models.add(model)
                    }
                }
            }
        })
    }
}


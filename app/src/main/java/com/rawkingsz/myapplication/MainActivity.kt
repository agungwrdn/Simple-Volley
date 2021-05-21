package com.rawkingsz.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonStreamParser
import com.rawkingsz.myapplication.model.data
import org.json.JSONException
import org.json.JSONObject
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    val gson = Gson()
    private lateinit var textView: TextView
    private var requestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "KotlinApp"
        textView = findViewById(R.id.textViewResult)
        val button: Button = findViewById(R.id.btnParse)
        requestQueue = Volley.newRequestQueue(this)
        button.setOnClickListener {
            jsonParse()
        }
    }

    private fun jsonParse() {
        val data = data(
                "Martin Garrix",
                "3127872390716349",
                "085232603701",
                "agungwar00@gmail.com",
                "Maju Bersama FBI",
                "",
                "",
                1,
                ""
        )

        val dataJson = gson.toJson(data)
        val url = "http://dev.rms-pi.com/web%20api/Petani/NewEntry"
        val request = object: JsonObjectRequest(Request.Method.POST, url, JSONObject(dataJson), {
            response ->
            Log.d("STATUS SENT DATA", JSONObject(response.toString()).toString())
        }, { error -> Log.d("Status sent data", error.networkResponse.statusCode.toString()) })
        {
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiUFQwMDAwMDY1NzIyIiwianRpIjoiNDhmZWIyZmItNDhlYi00NDdjLTkzMWEtYjhmMTM3ZDUzYzk0IiwiZXhwIjoxNjIxNjkyNzIwLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjUwMTAiLCJhdWQiOiJVc2VyIn0.D3kw3aV53brxG9JAl4QjSsXKCqFfF1oaamXikpmFAbw"
                return headers
            }

            override fun getBodyContentType(): String {
                return "application/json"
            }
        }
        requestQueue?.add(request)
    }
}
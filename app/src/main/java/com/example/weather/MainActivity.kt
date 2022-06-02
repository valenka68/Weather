package com.example.weather

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weather.databinding.ActivityMainBinding
import org.json.JSONObject

const val ApiKey = "16b6f8ba006249fdb76165154223005"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainButton.setOnClickListener {
if (binding.userField.text.toString().isEmpty()) binding.resultinfo.text = "Введите Ваш город" else  getResult(binding.userField.text.toString())

        }
    }
    private fun getResult (name: String){
        val url = "https://api.weatherapi.com/v1/current.json" +
        "?key=$ApiKey&q=$name&aqi=no"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET,
            url,
            {
                response->
                val obj = JSONObject(response)
                val temp = obj.getJSONObject("current")
                Log.d("MyLog", "Response: ${temp.getString("temp_c")}")
                binding.resultinfo.text = "Температура в $name ${temp.getString("temp_c")}"
            },
            {
                Log.d("MyLog","Volley errow: $it")
            }
        )

        queue.add(stringRequest)
    }
}
package com.nawan.clima

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.nawan.clima.retrofit.RetrofitService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buscaClima(view: View) {
        val serviceRetrofit = RetrofitService()
        serviceRetrofit.api?.consulta(latitude.text.toString().toDouble(), longitude.text.toString().toDouble())?.enqueue(object : Callback<Clima> {
            override fun onResponse(call: Call<Clima>?, response: Response<Clima>?) {
                val clima = response?.body();
                if (clima != null) {
                    when {
                        clima.currently?.icon.toString() == "clear-day" -> image_clima.setImageResource(R.drawable.clearday)
                        clima.currently?.icon.toString() == "clear-night" -> image_clima.setImageResource(R.drawable.clearnight)
                        clima.currently?.icon.toString() == "cloudy" -> image_clima.setImageResource(R.drawable.cloudy)
                        clima.currently?.icon.toString() == "fog" -> image_clima.setImageResource(R.drawable.fog)
                        clima.currently?.icon.toString() == "partly-cloudy-day" -> image_clima.setImageResource(R.drawable.partlycloudyday)
                        clima.currently?.icon.toString() == "partly-cloudy-night" -> image_clima.setImageResource(R.drawable.partlycloudynight)
                        clima.currently?.icon.toString() == "rain" -> image_clima.setImageResource(R.drawable.rain)
                        clima.currently?.icon.toString() == "sleet" -> image_clima.setImageResource(R.drawable.sleet)
                        clima.currently?.icon.toString() == "snow" -> image_clima.setImageResource(R.drawable.snow)
                        clima.currently?.icon.toString() == "wind" -> image_clima.setImageResource(R.drawable.wind)
                    }
                }
            }

            override fun onFailure(call: Call<Clima>?, t: Throwable?) {
                Log.e("Erro", "************** erro **********\n"+t?.message.toString())
            }
        })
    }
}


package com.example.updog.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.updog.R
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MainActivity : AppCompatActivity() {

    private val navController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navController.navigateUp()
    }

    inner class ErrorInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request: Request = chain.request()
            val response = chain.proceed(request)

            if (response.code == 200) {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Some server error")
                    .setMessage("Try connecting later")
                    .setPositiveButton("Ok") { dialogInterface, _ ->
                        dialogInterface.dismiss()
                    }
                    .show()
            }
            return response
        }
    }
}
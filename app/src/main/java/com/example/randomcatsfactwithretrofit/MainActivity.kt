package com.example.randomcatsfactwithretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomcatsfactwithretrofit.screens.factfragment.FactFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder, FactFragment()).commit()
    }
}
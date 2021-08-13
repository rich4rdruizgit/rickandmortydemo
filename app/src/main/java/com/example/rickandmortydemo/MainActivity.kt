package com.example.rickandmortydemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.Toast
import com.example.rickandmortydemo.character.ui.getall.CharacterActivity
import com.example.rickandmortydemo.common.Environments

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionBar?.hide()

        RickMortyApplication.init(
            Environments()
        )

        startActivity(Intent(applicationContext, CharacterActivity::class.java))

        //startTimer()
    }

    fun startTimer(){
        object:CountDownTimer(3000,1000){
            override fun onTick(millisUntilFinished: Long) {
                TODO("Not yet implemented")
            }

            override fun onFinish() {
                startActivity(Intent(applicationContext, CharacterActivity::class.java).apply {  })

            }

        }
    }
}

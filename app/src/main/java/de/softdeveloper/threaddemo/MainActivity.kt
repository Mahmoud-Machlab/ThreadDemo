package de.softdeveloper.threaddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import de.softdeveloper.threaddemo.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.lang.Runnable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tvBefore: TextView
    private lateinit var tvAfter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tvBefore = binding.tvBefore
        tvAfter = binding.tvAfter

        binding.btnClear.setOnClickListener {
            tvBefore.text = ""
            tvAfter.text = ""
        }

        binding.btnStart.setOnClickListener {
            doIt4()
        }

    }

    fun doIt(){
        tvBefore.text = "Aufgabe beginnt"
        if(binding.cbSleep.isChecked){
            Thread.sleep(5000)
        }else{
            while (true);
        }
        tvAfter.text = "Aufgabe erledigt"
    }

    fun doIt1(){
        tvBefore.text = "Aufgabe beginnt"
        Thread(Runnable {
            if(binding.cbSleep.isChecked){
                Thread.sleep(5000)
            }else{
                while (true);
            }
            tvAfter.text = "Aufgabe erledigt"
        }).start()
    }

    fun doIt2(){
        val handler = Handler()
        Thread(Runnable {
            handler.post{ tvBefore.text = "Aufgabe beginnt" }
            if(binding.cbSleep.isChecked){
                Thread.sleep(5000)
            }else{
                while (true);
            }
            handler.post{ tvAfter.text = "Aufgabe erledigt" }
        }).start()
    }

    fun doIt3(){
        val thread = Thread(Runnable {
            if(binding.cbSleep.isChecked){
                Thread.sleep(5000)
            }else{
                while (true);
            }
            runOnUiThread{ tvAfter.text = "Aufgabe erledigt" }
        })
        tvBefore.text = "Aufgabe beginnt"
        thread.start()
    }

    fun doIt4(){
        MainScope().launch {
            tvBefore.text = "Aufgabe beginnt"
            withContext(Dispatchers.Default){
                if(binding.cbSleep.isChecked){
                    delay(5000)
                }else{
                    while (true);
                }
            }
            tvAfter.text = "Aufgabe erledigt"
        }
    }
}
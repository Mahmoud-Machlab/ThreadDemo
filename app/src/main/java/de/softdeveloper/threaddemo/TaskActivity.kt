package de.softdeveloper.threaddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.softdeveloper.threaddemo.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
package de.softdeveloper.threaddemo

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import de.softdeveloper.threaddemo.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskBinding
    private val werte = arrayOfNulls<String>(20)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.INVISIBLE

        binding.btnAction.setOnClickListener {
            MyTask().execute("Frank")
        }
    }

    inner class MyTask(): AsyncTask<String,Int,Array<String?>>(){

        lateinit var values:Array<String?>
        override fun doInBackground(vararg params: String?): Array<String?> {
            for(i in values.indices){
                Thread.sleep(250)
                values[i] = params[0]?:""
                publishProgress(i)
            }
            return values
        }

        override fun onPreExecute() {
            values = werte
            binding.progressBar.visibility = View.VISIBLE
        }

        override fun onPostExecute(result: Array<String?>) {
           for (value in values){
               binding.textView.append(value + "\n")
           }
            binding.progressBar.progress = 0
            binding.progressBar.visibility = View.INVISIBLE
        }

        override fun onProgressUpdate(vararg values: Int?) {
           binding.progressBar.progress = values[0]!!
        }
    }
}
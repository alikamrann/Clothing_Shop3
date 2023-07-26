package com.example.workmanagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {
    companion object {
        const val KEY_COUNT_VALUE = "key_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    private fun setOneTimeWorkRequest() {
        val workManager = WorkManager.getInstance(applicationContext)

        val date: Data = Data.Builder().putInt(KEY_COUNT_VALUE, 125).build()
        val constraints = Constraints.Builder().setRequiresCharging(true).build()

        val filteringWorker =
            OneTimeWorkRequest.Builder(FilteringWorker::class.java).build()

        val compressingWorker =
            OneTimeWorkRequest.Builder(CompressingWorker::class.java).build()

        val downloadingWorker =
            OneTimeWorkRequest.Builder(DownloadingWorker::class.java).build()


        val oneTimeWorkRequest =
            OneTimeWorkRequest.Builder(UploadWorker::class.java).setConstraints(constraints)
                .setInputData(date).build()


        val parallelWorker = mutableListOf<OneTimeWorkRequest>()
        parallelWorker.add(downloadingWorker)
        parallelWorker.add(filteringWorker)

        val textureView = findViewById<TextView>(R.id.textView)
        workManager.beginWith(parallelWorker).then(compressingWorker).then(oneTimeWorkRequest)
            .enqueue()
        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.id).observe(this, Observer {
            textureView.text = it.state.name
            if (it.state.isFinished) {
                val data = it.outputData
                val message = data.getString(UploadWorker.KEY_WORKER)
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun uploadBtn(view: View) {
        setOneTimeWorkRequest()
    }
}
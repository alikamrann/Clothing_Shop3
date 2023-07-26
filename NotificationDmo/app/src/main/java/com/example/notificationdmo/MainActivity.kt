package com.example.notificationdmo

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var notificationManager: NotificationManager
    val CHANNEL_ID = "com.example.notificationdmo"
    val notification_id = 100
    private val KEY_REPLY = "key reply"
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                createNotification()
            } else {
                Snackbar.make(
                    findViewById<View>(android.R.id.content).rootView,
                    "Please grant Notification permission from App Settings",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS,
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            createNotification()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }



        createNotificationChannel()
    }

    private fun createNotification(){

        val tapResult = Intent(this,SecondActivity::class.java)
        val pendingIntent :PendingIntent= PendingIntent.getActivity(this,0,tapResult,PendingIntent.FLAG_MUTABLE)

        val intent2 = Intent(this,DetailsActivity::class.java)
        val pendingIntent2 :PendingIntent= PendingIntent.getActivity(this,0,intent2,PendingIntent.FLAG_MUTABLE)

        val action : NotificationCompat.Action = NotificationCompat.Action.Builder(0,"Details",pendingIntent2).build()



        val intent3 = Intent(this,SettingActivity::class.java)
        val pendingIntent3 :PendingIntent= PendingIntent.getActivity(this,0,intent3,PendingIntent.FLAG_MUTABLE)

        val action2 : NotificationCompat.Action = NotificationCompat.Action.Builder(0,"Setting",pendingIntent3).build()


        val remoteInput : RemoteInput  = RemoteInput.Builder(KEY_REPLY).run {
            setLabel("Insert your name here")
                .build()
        }
        val  replyAction : NotificationCompat.Action = NotificationCompat.Action.Builder(
            0,"Reply",pendingIntent
        ).addRemoteInput(remoteInput).build()

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("textTitle")
            .setContentText("textContent")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(action)
            .addAction(action2)
            .addAction(replyAction)
            .build()
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notify(notification_id, builder)
        }


    }
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
             notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun notificationClickListener(view: View) {
        createNotification()
    }
}
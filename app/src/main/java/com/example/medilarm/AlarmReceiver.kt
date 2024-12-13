package com.example.medilarm

import androidx.core.app.ActivityCompat
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            val medicineName = intent?.getStringExtra("medicineName")
            val dosage = intent?.getStringExtra("dosage")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "MedicineReminderChannel"
                val description = "Channel for medicine reminders"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel("medicineReminderChannel", name, importance)
                channel.description = description

                val notificationManager = context.getSystemService(NotificationManager::class.java) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }

            val builder = NotificationCompat.Builder(context, "medicineReminderChannel")
                .setSmallIcon(R.drawable.app_logo)
                .setContentTitle("Medicine Reminder")
                .setContentText("Time to take $medicineName ($dosage)")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
                .setVibrate(longArrayOf(0, 1000, 500, 1000))

            with(NotificationManagerCompat.from(context)) {
                val notificationId = medicineName?.hashCode() ?: 0
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    notify(notificationId, builder.build())
                }
            }

            Toast.makeText(context, "Time to take $medicineName ($dosage)", Toast.LENGTH_LONG).show()
        }
    }
}
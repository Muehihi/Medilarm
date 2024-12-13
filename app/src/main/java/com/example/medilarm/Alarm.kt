package com.example.medilarm

import android.Manifest
import android.app.AlarmManager
import android.content.pm.PackageManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class Alarm : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 100
    }

    private lateinit var medicineNameEditText: EditText
    private lateinit var dosageEditText: EditText
    private lateinit var timePicker: TimePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_alarm)

        medicineNameEditText = findViewById(R.id.medicineName)
        dosageEditText = findViewById(R.id.dosage)
        timePicker = findViewById(R.id.timePicker)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBack: ImageView = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        val saveButton = findViewById<Button>(R.id.btn_save)
        saveButton.setOnClickListener {
            val medicineName = medicineNameEditText.text.toString()
            val dosage = dosageEditText.text.toString()
            val hour = timePicker.hour
            val minute = timePicker.minute

            if (medicineName.isNotEmpty() && dosage.isNotEmpty()) {
                setAlarm(hour, minute, medicineName, dosage)
                Toast.makeText(this, "Alarm set successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter medicine name and dosage.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setAlarm(hour: Int, minute: Int, medicineName: String, dosage: String) {
        val alarmManager = getSystemService(AlarmManager::class.java) as AlarmManager
        val alarmIntent = Intent(this, AlarmReceiver::class.java)
        alarmIntent.putExtra("medicineName", medicineName)
        alarmIntent.putExtra("dosage", dosage)

        val pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, alarmIntent, PendingIntent.FLAG_IMMUTABLE)

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        if (calendar.timeInMillis < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

    }

    private fun requestNotificationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
        }
    }



}
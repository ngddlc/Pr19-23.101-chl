package com.example.pr19_23101_fi

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.switchmaterial.SwitchMaterial

class Setting : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var switchLocation: SwitchMaterial
    private lateinit var switchNotify: SwitchMaterial
    private lateinit var switchNews: SwitchMaterial
    private lateinit var btnReset: MaterialButton
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)

        sharedPrefs = getSharedPreferences("AppSettings", MODE_PRIVATE)

        btnBack = findViewById(R.id.btnBack)
        switchLocation = findViewById(R.id.switchLocation)
        switchNotify = findViewById(R.id.switchNotify)
        switchNews = findViewById(R.id.switchNews)
        btnReset = findViewById(R.id.btnReset)

        btnBack.setOnClickListener {
            finish()
        }

        // Set initial values without triggering listeners
        loadSettings()

        // Setup check listeners
        setupListeners()

        btnReset.setOnClickListener {
            resetSettings()
        }
    }

    private fun loadSettings() {
        switchLocation.isChecked = sharedPrefs.getBoolean("show_location", false)
        switchNotify.isChecked = sharedPrefs.getBoolean("show_notify", false)
        switchNews.isChecked = sharedPrefs.getBoolean("show_news", true)
    }

    private fun setupListeners() {
        switchLocation.setOnCheckedChangeListener { _, isChecked ->
            val text = if (isChecked) {
                "Включено отображение моего местороложения"
            } else {
                "Отключено отображение моего местороложения"
            }
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            sharedPrefs.edit().putBoolean("show_location", isChecked).apply()
        }

        switchNotify.setOnCheckedChangeListener { _, isChecked ->
            val text = if (isChecked) {
                "Включены уведомления о новом запуске"
            } else {
                "Отключены уведомления о новом запуске"
            }
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            sharedPrefs.edit().putBoolean("show_notify", isChecked).apply()
        }

        switchNews.setOnCheckedChangeListener { _, isChecked ->
            val text = if (isChecked) {
                "Включено отображение новостей"
            } else {
                "Отключено отображение новостей"
            }
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            sharedPrefs.edit().putBoolean("show_news", isChecked).apply()
        }
    }

    private fun resetSettings() {
        // Clear listeners to avoid showing multiple Toasts when resetting
        switchLocation.setOnCheckedChangeListener(null)
        switchNotify.setOnCheckedChangeListener(null)
        switchNews.setOnCheckedChangeListener(null)

        // Reset UI state to false (unchecked)
        switchLocation.isChecked = false
        switchNotify.isChecked = false
        switchNews.isChecked = false

        // Update SharedPreferences
        sharedPrefs.edit().apply {
            putBoolean("show_location", false)
            putBoolean("show_notify", false)
            putBoolean("show_news", false)
            apply()
        }

        Toast.makeText(this, "Настройки сброшены", Toast.LENGTH_SHORT).show()

        // Restore listeners
        setupListeners()
    }
}

package com.example.code

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    val locale_german: Locale = Locale("de")
    val locale_english: Locale = Locale("en-US")

    private val app_lifecycle = true;
    private val activityIdentifier = "MainLifecycle -"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSwitch: Button = findViewById(R.id.btnSwitch)
        btnSwitch.visibility = if (app_lifecycle) View.VISIBLE else View.INVISIBLE

        if (app_lifecycle) Log.d("Lifecycle", "$activityIdentifier onRestart callback is called!")



        btnSwitch.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, SecondaryLifeCycle::class.java))
        })

        languageUpdateHandling()
    }

    /**
     * This event is called when the activity becomes visible, but without being in the foreground. (Thus, the user does not yet see it)
     */
    override fun onStart() {
        if (app_lifecycle) Log.d("Lifecycle", "$activityIdentifier onStart callback is called!")
        super.onStart()
    }

    /**
     * This event is triggered when the activity is about to get to the forground, thus can be seen and interacted with by the user.
     */
    override fun onResume() {
        if (app_lifecycle) Log.d("Lifecycle", "$activityIdentifier  onResume callback is called!")
        super.onResume()
    }

    /**
     * This event is triggered when the app is no longer in the foreground, but still visible. (thus, it is in the background and an other activity is in the foreground
     */
    override fun onPause() {
        if (app_lifecycle) Log.d("Lifecycle", "$activityIdentifier onPause callback is called!")
        super.onPause()
    }

    /**
     * This event is called when the app is no longer visible, which occours either when an other activity has been launched or when the app is being minizmied.
     */
    override fun onStop() {
        if (app_lifecycle) Log.d("Lifecycle", "$activityIdentifier onStop callback is called!")
        super.onStop()
    }

    /**
     * This event is called when the app is terminated, thus closed, or closed by the system in order to free up memory.
     */
    override fun onDestroy() {
        if (app_lifecycle) Log.d("Lifecycle", "$activityIdentifier onDestroy callback is called!")
        super.onDestroy()
    }

    /**
     * This event is called once an activity has been started, then left and is then "reopened" again. For instance, when pressing the home button or when switching between activities.
     */
    override fun onRestart() {
        if (app_lifecycle) Log.d("Lifecycle", "$activityIdentifier onRestart callback is called!")
        super.onRestart()
    }

    private fun languageUpdateHandling() {
        val txtText: TextView = findViewById(R.id.txtText)
        val hint: TextView = findViewById(R.id.txtChoose)
        val checkGerman: CheckBox = findViewById(R.id.checkGerman)
        val checkEnglish: CheckBox = findViewById(R.id.checkEnglish)

        // Display views.
        txtText.visibility = View.VISIBLE;
        hint.visibility = View.VISIBLE;
        checkGerman.visibility = View.VISIBLE;
        checkEnglish.visibility = View.VISIBLE;


        // Select current language
        checkGerman.isChecked = applicationContext.resources.configuration.locale.toLanguageTag() == "de"
        checkEnglish.isChecked = applicationContext.resources.configuration.locale.toLanguageTag() == "en-US"


        checkGerman.setOnCheckedChangeListener { _, isChecked ->
            checkEnglish.isChecked = !isChecked;

            if (isChecked) {
                updateLocaleSettings(locale_german)
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        checkEnglish.setOnCheckedChangeListener { _, isChecked ->
            checkGerman.isChecked = !isChecked;

            if (isChecked) {
                updateLocaleSettings(locale_english)
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        txtText.setText(R.string.message)
        hint.setText(R.string.choose)
    }


    /**
     * This function updates the [Locale] configuration of the app by setting it to the given [locale].
     */
    private fun updateLocaleSettings(locale: Locale) {
        val config = applicationContext.resources.configuration
        config.setLocale(locale)
    }


    /**
     * This function logs different messages to Logcat using its security levels.
     */
    fun logLevelDemo() {
        val tag = "AppD"

        Log.d(tag, "Thats a debug message!")
        Log.e(tag, "Thats a error message!")
        Log.i(tag, "Thats a info message!")
        Log.w(tag, "Thats a warning message!")
        Log.v(tag, "Thats a verbose message!")
    }

}
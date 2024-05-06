package com.example.chapter_4.Model

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {
    private var sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()


//    init {
//        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//    }


    companion object {
//        const val SHARED_PREFS = "shared_prefs"
        const val USERNAME_KEY = "username"
        const val EMAIL_KEY = "email"
        const val PASSWORD_KEY = "password"
    }

    fun saveUserData(username: String, email: String, password: String) {
        editor.putString(USERNAME_KEY, username)
        editor.putString(EMAIL_KEY, email)
        editor.putString(PASSWORD_KEY, password)
        editor.apply()
    }

    fun getUsername(): String? {
        return sharedPreferences.getString(USERNAME_KEY, "")
    }

    fun getEmail(): String? {
        return sharedPreferences.getString(EMAIL_KEY, "")
    }

    fun getPassword(): String? {
        return sharedPreferences.getString(PASSWORD_KEY, "")
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.contains(USERNAME_KEY) && sharedPreferences.contains(PASSWORD_KEY)
    }

    fun isValidLogin(username: String, password: String): Boolean {
        val savedUsername = sharedPreferences.getString(USERNAME_KEY, "")
        val savedPassword = sharedPreferences.getString(PASSWORD_KEY, "")
        return username == savedUsername && password == savedPassword
    }

    fun clearUserData() {
        editor.remove(USERNAME_KEY)
        editor.remove(EMAIL_KEY)
        editor.remove(PASSWORD_KEY)
        editor.apply()
    }
}
package com.loancalculator.app.ui.sharedpref

import android.content.Context

class SharedPref(private val context: Context) {

    val sharedPref = context.getSharedPreferences("loancalculator_pref", Context.MODE_PRIVATE)
    val editor = sharedPref.edit()


    val CURLANGUAGE = "CurLanguage"
    val ISCOLUMN1VISIBLE = "isColumn1Visible"
    val ISCOLUMN2VISIBLE = "isColumn2Visible"
    val ISCOLUMN3VISIBLE = "isColumn3Visible"
    val ISCOLUMN4VISIBLE = "isColumn4Visible"


    fun setLanguage(language: String) {
        editor.apply {
            putString(CURLANGUAGE, language)
            apply()
        }
    }

    fun getLanguage(): String? {
        return sharedPref.getString(CURLANGUAGE, "Norwegian")
    }


    fun setColumn1Visible(isVisible : Boolean) {
        editor.apply {
            putBoolean(ISCOLUMN1VISIBLE, isVisible)
            apply()
        }
    }

    fun getColumn1Visible(): Boolean {
        return sharedPref.getBoolean(ISCOLUMN1VISIBLE, true)
    }


    fun setColumn2Visible(isVisible : Boolean) {
        editor.apply {
            putBoolean(ISCOLUMN2VISIBLE, isVisible)
            apply()
        }
    }

    fun getColumn2Visible(): Boolean {
        return sharedPref.getBoolean(ISCOLUMN2VISIBLE, true)
    }


    fun setColumn3Visible(isVisible : Boolean) {
        editor.apply {
            putBoolean(ISCOLUMN3VISIBLE, isVisible)
            apply()
        }
    }

    fun getColumn3Visible(): Boolean {
        return sharedPref.getBoolean(ISCOLUMN3VISIBLE, true)
    }



    fun setColumn4Visible(isVisible : Boolean) {
        editor.apply {
            putBoolean(ISCOLUMN4VISIBLE, isVisible)
            apply()
        }
    }

    fun getColumn4Visible(): Boolean {
        return sharedPref.getBoolean(ISCOLUMN4VISIBLE, true)
    }



}
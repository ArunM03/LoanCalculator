package com.loancalculator.data

import android.os.Parcelable
import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class LoanData(
    val loanType : String = "",
    val loanAmount : String = "",
    val interest : String = "",
    val numOfYears : String = ""
) : Serializable
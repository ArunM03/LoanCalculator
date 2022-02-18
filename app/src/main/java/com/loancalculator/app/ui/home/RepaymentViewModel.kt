package com.loancalculator.app.ui.home

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.widget.TableLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loancalculator.app.R
import com.loancalculator.app.ui.sharedpref.SharedPref
import com.loancalculator.data.RepaymentData
import java.text.DecimalFormat

class RepaymentViewModel(val context : Application) : AndroidViewModel(context) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text


    val sharedPref = SharedPref(context)

    val isColumn1Visible  = sharedPref.getColumn1Visible()
    val isColumn2Visible  = sharedPref.getColumn2Visible()
    val isColumn3Visible = sharedPref.getColumn3Visible()
    val isColumn4Visible = sharedPref.getColumn4Visible()



    fun createTable(tablelayout : TableLayout, it :  List<RepaymentData>){
        tablelayout.removeAllViews()
        var titleEntered = false
        for ((pos, session) in it.withIndex()) {
            val customview =
                LayoutInflater.from(context.applicationContext).inflate(R.layout.table_items, null, false)
            val customviewtitle =
                LayoutInflater.from(context.applicationContext).inflate(R.layout.table_title, null, false)
            if (!titleEntered){
                tablelayout.addView(customviewtitle)
                titleEntered = true
            }


            val amount1 = customview.findViewById<TextView>(R.id.tv_item1)
            val amount2 = customview.findViewById<TextView>(R.id.tv_item2)
            val amount3 = customview.findViewById<TextView>(R.id.tv_item3)
            val amount4 = customview.findViewById<TextView>(R.id.tv_item4)

            amount4.visibility = View.VISIBLE

            val title1 = customviewtitle.findViewById<TextView>(R.id.tv_title1)
            val title2 = customviewtitle.findViewById<TextView>(R.id.tv_title2)
            val title3 = customviewtitle.findViewById<TextView>(R.id.tv_title3)
            val title4 = customviewtitle.findViewById<TextView>(R.id.tv_title4)

            if (!isColumn1Visible){
                amount1.visibility = View.GONE
                title1.visibility = View.GONE
            }

            if (!isColumn2Visible){
                amount2.visibility = View.GONE
                title2.visibility = View.GONE
            }

            if (!isColumn3Visible){
                amount3.visibility = View.GONE
                title3.visibility = View.GONE
            }

            if (!isColumn4Visible){
                amount4.visibility = View.GONE
                title4.visibility = View.GONE
            }


            title4.visibility = View.VISIBLE

            val decimalFormat = DecimalFormat("##.##")
            val yearTermAmount = decimalFormat.format(session.yearTermAmount)
            val interest = decimalFormat.format(session.interest)
            val installment = decimalFormat.format(session.installment)
            val remainingDebt = decimalFormat.format(session.remainingDebt)

            amount1.text = yearTermAmount.toString()
            amount2.text = interest.toString()
            amount3.text = installment.toString()
            amount4.text = remainingDebt.toString()

            if (pos>0){
                if (pos%2 != 0){
                    val color = ContextCompat.getColor(context.applicationContext, R.color.purple_200)
                    amount1.setBackgroundColor(color)
                    amount2.setBackgroundColor(color)
                    amount3.setBackgroundColor(color)
                    amount4.setBackgroundColor(color)
                }
            }
            tablelayout.addView(customview)
        }
    }
}
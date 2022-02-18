package com.loancalculator.app.ui.loan

import com.loancalculator.data.LoanData
import com.loancalculator.data.RepaymentData
import java.text.DecimalFormat

class Loan {

    fun getAnnuityLoanList(loanData: LoanData) : List<RepaymentData>{

        val percent = loanData.interest.toFloat() / 100
        val numOfYears = loanData.numOfYears.toDouble()
        val loan = loanData.loanAmount.toFloat()

        val yearPerTerm = calculateYearPerTerm(percent, loan, numOfYears)

        val repaymentDataList = mutableListOf<RepaymentData>()

        var remainingDebtForCalc = loan

        for (i in 1..numOfYears.toInt()){

            val payment = yearPerTerm

            val interest = calculateInterest(remainingDebtForCalc, percent)

            val installment = calculateInstallments(payment, interest)

            val remainingDebt = calculateRemainingDebt(remainingDebtForCalc, installment)

            remainingDebtForCalc = remainingDebt.toFloat()

            val repaymentData = RepaymentData(payment.toFloat(),interest.toFloat(),installment.toFloat(),remainingDebt.toFloat())

            repaymentDataList.add(repaymentData)
        }

        return repaymentDataList

    }

     fun calculateInstallments(payment: Float, interest: Float) : Float {
        return  payment - interest
     }

    fun calculateRemainingDebt(remainingDebtForCalc: Float, installment: Float) : Float {
        return  remainingDebtForCalc - installment
    }

    fun calculateYearPerTerm(
        percent: Float,
        loan: Float,
        numOfYears: Double
    ) : Float {
        return calculateInterest(percent, loan) / (1 - (Math.pow(1.0 + percent, -numOfYears))).toFloat()
     }

    fun getSerialLoanList(loanData: LoanData) : List<RepaymentData>{

        val percent = loanData.interest.toFloat() / 100
        val numOfYears = loanData.numOfYears.toDouble()
        val loan = loanData.loanAmount.toFloat()

        val repaymentDataList = mutableListOf<RepaymentData>()

        var remainingDebtForCalc = loan

        val payment = getYearPerTermForSerialLoan(loan, numOfYears)

        for (i in 1..numOfYears.toInt()){

            val remainingDebt = remainingDebtForCalc - payment

            remainingDebtForCalc = remainingDebt.toFloat()

            val interest = calculateInterest(remainingDebtForCalc, percent)

            val installment = payment + interest

            val repaymentData = RepaymentData(payment.toFloat(),interest.toFloat(),installment.toFloat(),remainingDebt.toFloat())

            repaymentDataList.add(repaymentData)
        }

        return repaymentDataList

    }

     fun getYearPerTermForSerialLoan(
        loan: Float,
        numOfYears: Double
    ) : Float {
       return loan / numOfYears.toFloat()
    }

    fun calculateInterest(
        remainingDebtForCalc: Float,
        percent: Float
    ) : Float {
        return  remainingDebtForCalc * percent
     }
}
package com.loancalculator.app

import com.loancalculator.app.ui.loan.Loan
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LoanCalculationUnitTest {


    @Test
    fun calculateYearPerTerm_isCorrect() {
        val result = Loan().calculateYearPerTerm(0.036f,200000f,12.0)
        assertEquals(result,20818.781f)
    }

    @Test
    fun calculateInterest_isCorrect() {
        val result = Loan().calculateInterest(200000f,0.036f)
        assertEquals(result,7199.9995f)
    }

    @Test
    fun calculateInstallment_isCorrect() {
        val result = Loan().calculateInstallments(20818.78f,7200f)
        assertEquals(result,13618.779f)
    }

    @Test
    fun calculateRemainingDebt_isCorrect() {
        val result = Loan().calculateRemainingDebt(200000f,13618.78f)
        assertEquals(result,186381.22f)
    }


    @Test
    fun calculateYearPerTermForSerialLoan_isCorrect() {
        val result = Loan().getYearPerTermForSerialLoan(200000f,12.0)
        assertEquals(result,16666.666f)
    }







}
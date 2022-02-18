package com.loancalculator.data

data class RepaymentData(
    var yearTermAmount : Float = 0f,
    var interest  : Float = 0f,
    var installment  : Float = 0f,
    var remainingDebt  : Float = 0f
)
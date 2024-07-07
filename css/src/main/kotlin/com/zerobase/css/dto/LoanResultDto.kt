package com.zerobase.css.dto

class LoanResultDto {
    data class ResponseDto(
        val userKey: String,
        val limitAmount: Long,
        val interest: Double
    )
}
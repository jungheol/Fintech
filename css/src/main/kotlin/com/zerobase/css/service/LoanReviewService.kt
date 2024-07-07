package com.zerobase.css.service

import com.zerobase.css.dto.LoanRequestDto
import com.zerobase.css.dto.LoanResultDto
import org.springframework.stereotype.Service

@Service
class LoanReviewService {
    fun loanReview(loanRequestDto: LoanRequestDto.RequestInputDto): LoanResultDto.ResponseDto {
        if (loanRequestDto.userIncomeAmount < 0) throw RuntimeException("Invalid userIncomeAmount Param")
        if (loanRequestDto.userIncomeAmount < 10000000) return LoanResultDto.ResponseDto(loanRequestDto.userKey, 10000000, 0.0)
        if (loanRequestDto.userIncomeAmount < 20000000) return LoanResultDto.ResponseDto(loanRequestDto.userKey, 20000000, 10.0)
        if (loanRequestDto.userIncomeAmount < 30000000) return LoanResultDto.ResponseDto(loanRequestDto.userKey, 30000000, 9.0)
        if (loanRequestDto.userIncomeAmount < 40000000) return LoanResultDto.ResponseDto(loanRequestDto.userKey, 40000000, 8.0)
        if (loanRequestDto.userIncomeAmount < 50000000) return LoanResultDto.ResponseDto(loanRequestDto.userKey, 50000000, 7.0)
        if (loanRequestDto.userIncomeAmount >= 50000000) return LoanResultDto.ResponseDto(loanRequestDto.userKey, 60000000, 6.0)
        throw RuntimeException("Invalid userIncomeAmount Param")
    }
}
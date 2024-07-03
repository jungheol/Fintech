package com.zerobase.api.loan.request

import com.zerobase.api.loan.GenerateKey
import com.zerobase.api.loan.encrypt.EncryptComponent
import com.zerobase.domain.repository.UserInfoRepository
import org.springframework.stereotype.Service

@Service
class LoanRequestServiceImpl(
    private val generateKey: GenerateKey,
    private val userInfoRepository: UserInfoRepository,
    private val encryptComponent: EncryptComponent
) : LoanRequestService {
    override fun loanRequestMain(
        loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): LoanRequestDto.LoanRequestResponseDto {
        // key 발급
        val userKey = generateKey.generateUserKey()

        // 주민등록번호 암호화
        loanRequestInputDto.userRegistrationNumber =
            encryptComponent.encryptString(loanRequestInputDto.userRegistrationNumber)

        // 유저 정보 저장
        saveUserInfo(loanRequestInputDto.toUserInfoDto(userKey))

        // kafka로 보내서 심사 요청
        loanRequestReview(userKey)

        return LoanRequestDto.LoanRequestResponseDto(userKey)
    }

    override fun saveUserInfo(userInfoDto: UserInfoDto) = userInfoRepository.save(userInfoDto.toEntity())

    override fun loanRequestReview(userKey: String) {
        TODO("Not yet implemented")
    }
}
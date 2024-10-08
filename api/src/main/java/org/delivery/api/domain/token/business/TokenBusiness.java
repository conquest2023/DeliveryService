package org.delivery.api.domain.token.business;


import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.converter.TokenConverter;
import org.delivery.api.domain.token.service.TokenService;
import org.delivery.api.exception.ApiException;
import org.delivery.db.user.UserEntity;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class TokenBusiness {

    private  final TokenService tokenService;
    private  final TokenConverter tokenConverter;

    //user entity user Id 추출
    //access,refresh token 발행
    //converter ->token  response 로변경

    public TokenResponse issueToken(UserEntity userEntity){

        return Optional.ofNullable(userEntity)
                .map(ue-> {
                    return ue.getId();
                })
                .map(userId->{
                    var accessToken=tokenService.issueAccessToken(userId);
                    var refreshToken=tokenService.issueRefreshToken(userId);
                    return  tokenConverter.toResponse(accessToken,refreshToken);
                }).orElseThrow(
                        ()->new ApiException(ErrorCode.NULL_POINT));

    }





}

package org.delivery.api.domain.token.helper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.TokenErrorCode;
import org.delivery.api.domain.token.ifs.TokenHelperIfs;
import org.delivery.api.domain.token.model.TokenDto;
import org.delivery.api.exception.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenHelper implements TokenHelperIfs {

    @Value("${token.secret.key}")
    private  String secretKey;

    @Value("${token.access-token.plus-hour}")
    private Long accessTokenPlusHour;
    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenPlusHour;
    @Override
    public TokenDto issueAccessToken(Map<String, Object> data) {
        var expiredLocalDateTime= LocalDateTime.now().plusHours(accessTokenPlusHour);

        var expiredAt= Date.from(
                expiredLocalDateTime.atZone(
                        ZoneId.systemDefault()).toInstant());


        var key= Keys.hmacShaKeyFor(secretKey.getBytes());

        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)  // 서명 설정
                .addClaims(data)                          // 클레임 추가
                .setExpiration(expiredAt)                 // 만료 시간 설정
                .compact();
        return TokenDto.builder()
                .token(jwtToken)
                .expireAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public TokenDto issueRefreshToken(Map<String, Object> data) {
        var expiredLocalDateTime= LocalDateTime.now().plusHours(refreshTokenPlusHour);

        var expiredAt= Date.from(
                expiredLocalDateTime.atZone(
                        ZoneId.systemDefault()).toInstant());


        var key= Keys.hmacShaKeyFor(secretKey.getBytes());

        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)  // 서명 설정
                .addClaims(data)                          // 클레임 추가
                .setExpiration(expiredAt)                 // 만료 시간 설정
                .compact();
        return TokenDto.builder()
                .token(jwtToken)
                .expireAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {
        var key=Keys.hmacShaKeyFor(secretKey.getBytes());

        var parser=Jwts.parser()
                .setSigningKey(key)
                .build();



        try{

            var result=parser.parseClaimsJws(token);
            return  new HashMap<String,Object>(result.getBody());
        }catch (Exception e){
            if(e instanceof SignatureException){
                throw  new ApiException(TokenErrorCode.INVALID_TOKEN,e);
                //토큰이 유효하지 않을때
            }else  if(e instanceof ExpiredJwtException){
                //만료된 토큰
                throw  new ApiException(TokenErrorCode.EXPIRED_TOKEN,e);
            }else {

                throw new ApiException(TokenErrorCode.TOKEN_EXCEPTION,e);
                //그 외 오류
            }
        }
    }
}

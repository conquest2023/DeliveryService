package org.delivery.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(req, res);


        // request 정보
        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder();


        headerNames.asIterator().forEachRemaining(headerKey -> {
            var headerValue = req.getHeader(headerKey);

            // authorization-token: ???, user-agent: ???
            headerValues.append("[")
                    .append(headerKey)
                    .append(":")
                    .append(headerValues)
                    .append("]");


        });

        var requestBody = new String(req.getContentAsByteArray());

        var url=req.getRequestURL();
        var method=req.getMethod();

        log.info(">>>> url:{}, method:{} header:{},body:{}",url,method,
                headerValues, requestBody);


        //response 정보
        var responseHeaderValues = new StringBuilder();

        res.getHeaderNames().forEach(headerKey -> {
            var headerValue = res.getHeader(headerKey);

            responseHeaderValues.append("[")
                    .append(headerKey)
                    .append(":")
                    .append(headerValues)
                    .append("]");
        });

        var responseBody = new String(res.getContentAsByteArray());

        log.info("<<<< url:{} ,method:{}, header:{}, body:{}",url,method, responseHeaderValues, responseBody);

        //이거 안쓰면 바디가 비어져서 옴
        res.copyBodyToResponse();
    }

}

package com.organization.mgmt.component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Long startTime = System.currentTimeMillis();

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request,1024 * 1024);

        ContentCachingResponseWrapper wrappedResponse =
                new ContentCachingResponseWrapper(response);


        try{
            filterChain.doFilter(wrappedRequest,wrappedResponse);
        }finally {
            Long duration = System.currentTimeMillis() - startTime;
            String method = wrappedRequest.getMethod();
            String path = wrappedRequest.getRequestURI();
            int Status = wrappedResponse.getStatus();
            String requestBody = getRequestBody(wrappedRequest);
            String responseBody = getResponse(wrappedResponse);

            log.info("""
                    Starting {} Request for path {}
                    ===== HTTP REQUEST =====
                    BODY   : {}
                    ---- HTTP RESPONSE ----
                    STATUS : {}, TIME   : {} ms, BODY   : {}
                    ========================================================================
                    """,
                    method,
                    path,
                    requestBody,
                    Status,
                    duration,
                    responseBody
            );

            wrappedResponse.copyBodyToResponse();


        }


    }

    private String getRequestBody(ContentCachingRequestWrapper request){
        byte[] buf = request.getContentAsByteArray();
        return buf.length == 0 ? "no Body" : new String(buf, StandardCharsets.UTF_8).trim();
    }
    private String getResponse(ContentCachingResponseWrapper response){
        byte[] buf = response.getContentAsByteArray();
        return buf.length == 0 ? "no Body" : new String(buf, StandardCharsets.UTF_8).trim();
    }
}

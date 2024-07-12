package dropwizard.springboot.dropwizardToSpringboot.service;

import dropwizard.springboot.dropwizardToSpringboot.utility.IdempotencyResponseWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class IdempotencyFilter extends OncePerRequestFilter {

    @Autowired
    private IdempotencyService idempotencyService;

    public IdempotencyFilter(IdempotencyService idempotencyService) {
        this.idempotencyService = idempotencyService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String idempotencyKey = request.getHeader("Idempotency-Key");

        if (idempotencyKey != null) {
            String cachedResponse = idempotencyService.checkCacheForIdempotencyKey(idempotencyKey);

            if (cachedResponse != null) {
                // Return the cached response
                response.setStatus(HttpStatus.OK.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().write(cachedResponse);
            }
            else{
                // Capture the response to cache it later
                IdempotencyResponseWrapper responseWrapper = new IdempotencyResponseWrapper(response);
                filterChain.doFilter(request, responseWrapper);

                // Cache the response
                String responseBody = new String(responseWrapper.getDataStream());
                idempotencyService.cacheResponse(idempotencyKey, responseBody);
                //send back the response
                response.setStatus(HttpStatus.OK.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().write(responseBody);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}

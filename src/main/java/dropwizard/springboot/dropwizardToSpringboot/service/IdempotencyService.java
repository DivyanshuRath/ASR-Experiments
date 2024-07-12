package dropwizard.springboot.dropwizardToSpringboot.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class IdempotencyService {

    @Cacheable(value = "idempotency", key = "#idempotencyKey")
    public String checkCacheForIdempotencyKey(String idempotencyKey){
        return null;
    }

    @CachePut(value = "idempotency", key = "#idempotencyKey")
    public String cacheResponse(String idempotencyKey, String response) {
        return response;
    }
}

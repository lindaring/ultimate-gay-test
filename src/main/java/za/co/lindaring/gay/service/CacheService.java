package za.co.lindaring.gay.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import za.co.lindaring.gay.config.CacheConfig;
import za.co.lindaring.gay.model.GetAllUsersResponse;
import za.co.lindaring.gay.model.GetQuestionsReponse;

import java.util.Optional;

@Service
public class CacheService {

    @CachePut(value = CacheConfig.GAY_USER_CACHE, key = "#key")
    public GetAllUsersResponse putUsers(String key, GetAllUsersResponse users) {
        return users;
    }

    @CachePut(value = CacheConfig.GAY_USER_CACHE, key = "#key")
    public GetQuestionsReponse putQuestions(String key, GetQuestionsReponse questions) {
        return questions;
    }

    @Cacheable(value = CacheConfig.GAY_USER_CACHE, key = "#key")
    public Optional<GetAllUsersResponse> getUsers(String key) {
        return Optional.empty();
    }

    @Cacheable(value = CacheConfig.GAY_USER_CACHE, key = "#key")
    public Optional<GetQuestionsReponse> getQuestions(String key) {
        return Optional.empty();
    }

}

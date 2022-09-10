package com.example.ehcache.ehcache.service;

import com.example.ehcache.ehcache.entity.UserInfo;
import com.example.ehcache.ehcache.repository.UserInfoRepository;
import com.example.ehcache.ehcache.request.CreateUserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserInfoRepository repository;

    @Autowired
    RedisTemplate<String , Object> redisTemplate;

    private static final String KEY_PREFIX = "userCache::";


    public UserInfo createUser(CreateUserRequestDto requestDto){
        UserInfo userInfo = requestDto.toUserInfo();
        return saveOrUpdate(userInfo);
    }



    /**
     *
     *  check
     *      if data exists in the cache
     *                  (GET data from cache)
     *                  RequestMethod.GET (GET)
     *              --> return
     *      else
     *          make a call to DB
     *          result if not null
     *              --> set to cache (findByEmail)
     *              REQUEST METHOD. POST (SET)
     *          return result to service
     *
     */
    public Optional<UserInfo> fetchUserByEmail(String email){
        Object value = redisTemplate.opsForValue().get(getKeyValue(email));
        if(Objects.nonNull(value)){
            return Optional.of((UserInfo) value);
        }
        Optional<UserInfo> byEmail = repository.findByEmail(email);
        byEmail.ifPresent(userInfo -> redisTemplate.opsForValue().set(getKeyValue(email), userInfo, Duration.ofMinutes(2)));
        return byEmail;
    }


    private String getKeyValue(String email){
        return KEY_PREFIX + email;
    }



    private UserInfo saveOrUpdate(UserInfo userInfo){
        return repository.save(userInfo);
    }
}

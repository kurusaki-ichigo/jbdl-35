package com.example.ehcache.ehcache.repository;

import com.example.ehcache.ehcache.entity.UserInfo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo , Long> {


//    @Cacheable(cacheNames = "userCache", unless = "#result == null")
    Optional<UserInfo> findByEmail(String email);


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
     *
     *
     *
     */

}

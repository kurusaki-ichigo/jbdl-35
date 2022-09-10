//package com.example.ehcache.ehcache.listener;
//
//import lombok.extern.slf4j.Slf4j;
//import org.ehcache.event.CacheEvent;
//import org.ehcache.event.CacheEventListener;
//
//@Slf4j
//public class MyCacheListener implements CacheEventListener<Object, Object> {
//
//    @Override
//    public void onEvent(CacheEvent<?, ?> cacheEvent) {
//        log.info(" key {}  || event {} || old value {} :::::: new value {}", cacheEvent.getKey(),
//                cacheEvent.getType(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
//    }
//}

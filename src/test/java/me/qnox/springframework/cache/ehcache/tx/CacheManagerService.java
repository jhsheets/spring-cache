/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.qnox.springframework.cache.ehcache.tx;

import org.springframework.cache.CacheManager;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CacheManagerService {

    private CacheManager cacheManager;

    public CacheManagerService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void doTxLogic(String key) {
        cacheManager.getCache("localTx").put(key, 1L);
        cacheManager.getCache("noTx").put(key, 1L);
    }

    public void doTxLogicWithException(String key) {
        cacheManager.getCache("localTx").put(key, 1L);
        cacheManager.getCache("noTx").put(key, 1L);
        throw new RuntimeException();
    }

    public Object getValueFromLocalTxCache(String key) {
        return cacheManager.getCache("localTx").get(key);
    }

    public Object getValueFromNoTxCache(String key) {
        return cacheManager.getCache("noTx").get(key);
    }

}

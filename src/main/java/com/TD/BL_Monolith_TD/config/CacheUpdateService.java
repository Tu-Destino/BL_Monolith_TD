package com.TD.BL_Monolith_TD.config;

import com.TD.BL_Monolith_TD.api.dto.response.PlaceResponse;
import com.TD.BL_Monolith_TD.api.dto.response.PostDiscoverResponse;
import com.TD.BL_Monolith_TD.infrastructure.services.PlaceService;
import com.TD.BL_Monolith_TD.infrastructure.services.PostDiscoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CacheUpdateService {

    private final PostDiscoverService postDiscoverService;
    private final PlaceService placeService;
    private final CacheManager cacheManager;

    @Autowired
    public CacheUpdateService(PostDiscoverService postDiscoverService, PlaceService placeService, CacheManager cacheManager) {
        this.postDiscoverService = postDiscoverService;
        this.placeService = placeService;
        this.cacheManager = cacheManager;
    }

    @Scheduled(fixedRate = 300000) // Actualiza cada 60 segundos
    public void refreshPostListCache() {
        Cache cache = cacheManager.getCache("postList");
        if (cache != null) {
            cache.clear();
            List<PostDiscoverResponse> posts = postDiscoverService.getAll();
            cache.put("postList", posts);
        }
    }

    @Scheduled(fixedRate = 14400000) // Actualiza cada 60 segundos
    public void refreshTitleListCache() {
        Cache cache = cacheManager.getCache("titleList");
        if (cache != null) {
            cache.clear();
            List<String> titles = placeService.getListNamePlace();
            cache.put("titleList", titles);
        }
    }

    @Scheduled(fixedRate = 14400000) // Actualiza cada 60 segundos
    public void refreshPlaceListCache() {
        Cache cache = cacheManager.getCache("placeList");
        if (cache != null) {
            cache.clear();
            List<PlaceResponse> places = placeService.getAll();
            cache.put("placeList", places);
        }
    }
}

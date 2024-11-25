package com.example.NIA.Calories;


import com.example.NIA.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient( configuration = FeignClientConfig.class, url ="https://api.collectapi.com/food/calories" , name = "NIA")
public interface NIApiClient {
    @GetMapping()
    ResponseEntity<NIAResponse> getCalories(
            @RequestParam(name = "query", required = true) String kal);


}

package com.example.NIA.whenFoods;

import com.example.NIA.FeignClientConfigF;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient( configuration = FeignClientConfigF.class, url ="https://api.collectapi.com/food/whenFoods" , name = "Nia")
public interface NIApiClientFood {
    @GetMapping()
    ResponseEntity<NiaResponse> getFood(
            @RequestParam(name = "query", required = true) String ay);

}

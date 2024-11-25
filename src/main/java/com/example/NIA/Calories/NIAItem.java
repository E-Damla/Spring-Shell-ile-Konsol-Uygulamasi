package com.example.NIA.Calories;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NIAItem(@JsonProperty("kcal") String kcal,
                      @JsonProperty("name") String name,
                      @JsonProperty("weight") String weight)
{

}


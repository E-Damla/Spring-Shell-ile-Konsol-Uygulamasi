package com.example.NIA.whenFoods;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NiaItem(@JsonProperty("ay") int ay) {
}

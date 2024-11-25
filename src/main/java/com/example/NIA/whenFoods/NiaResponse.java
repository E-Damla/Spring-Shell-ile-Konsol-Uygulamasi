package com.example.NIA.whenFoods;

import java.util.List;
import java.util.Map;

public record NiaResponse(Map<String, List<String>> result) {
}

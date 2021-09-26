package com.github.agabrys.warehouse;

import java.util.HashMap;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryController {

  @GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_VALUE)
  public Object search(
    @RequestParam List<String> metrics,
    @RequestParam(required = false) List<String> dimensions,
    @RequestParam(required = false) List<String> dimensionFilters) {
    var map = new HashMap<String, Object>();
    map.put("metrics", metrics);
    map.put("dimensions", dimensions);
    return map;
  }
}

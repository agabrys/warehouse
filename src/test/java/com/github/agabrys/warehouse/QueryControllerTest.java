package com.github.agabrys.warehouse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class QueryControllerTest {

  @Autowired
  private QueryController controller;
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void verify() throws Exception {
    var expectedJson = new JSONObject();
    expectedJson.put("metrics", "a,b");
    expectedJson.put("dimensions", null);

    mockMvc.perform(get("/query?metrics=clicks&dimensions=Datasource"))
      .andExpect(status().isOk())
      .andExpect(content().json(expectedJson.toString()));
  }
}

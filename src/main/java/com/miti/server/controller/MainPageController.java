package com.miti.server.controller;

import com.miti.server.model.MainPageResponse;
import com.miti.server.util.MainPageConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainPageController {

  private final MainPageConstructor mainPageConstructor;

  @GetMapping("/mainPage")
  public @ResponseBody
  MainPageResponse getMainPageElements(@RequestParam Long calories, @RequestParam int time) {
    return mainPageConstructor.mainPage(calories, time);
  }
}

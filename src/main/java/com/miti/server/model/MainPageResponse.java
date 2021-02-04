package com.miti.server.model;

import com.miti.server.model.entity.Category;
import com.miti.server.model.entity.Recipe;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MainPageResponse {

  public Recipe recipeOfTheDay;
  public List<Category> categoryList;
  public List<Recipe> lowCalories;
  public List<Recipe> fastAndDelicious;
}

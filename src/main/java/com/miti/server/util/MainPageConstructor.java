package com.miti.server.util;

import com.miti.server.model.MainPageResponse;
import com.miti.server.model.entity.CalorieContent;
import com.miti.server.model.entity.Category;
import com.miti.server.model.entity.Recipe;
import com.miti.server.service.CalorieContentService;
import com.miti.server.service.CategoryService;
import com.miti.server.service.RecipeService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainPageConstructor {

  private final RecipeService recipeService;
  private final CategoryService categoryService;
  private final CalorieContentService calorieContentService;
  private final SearchFilter searchFilter;

  public MainPageResponse mainPage(Long calories, int time) {
    Recipe recipeOfTheDay = getRecipeOfTheDay(new Date());
    List<Category> categories = categoryService.getAllCategories();
    List<Recipe> lowCalories = getRecipesWithLowCalories(calories);
    List<Recipe> fastAndDelicious = getFastAndDelicious(time);

    return new MainPageResponse(recipeOfTheDay, categories, lowCalories, fastAndDelicious);
  }

  private Recipe getRecipeOfTheDay(Date today) {
    Date firstDate = new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * 3);
    List<Recipe> recipes = recipeService.getRecipesByCreateDateBetween(firstDate, today);
    List<Double> avg = searchFilter.getAverageRating(recipes);
    List<Recipe> sortedList = new ArrayList<>(recipes);

    sortedList.sort(Comparator.comparing(s -> avg.get(recipes.indexOf(s))));

    return sortedList.get(0);
  }

  private List<Recipe> getRecipesWithLowCalories(Long calories) {
    List<CalorieContent> calorieContentList = calorieContentService
        .getCalorieContentsByCaloriesLessThan(calories);
    List<Recipe> recipesLowCalories = new ArrayList<>();
    for (CalorieContent calorie : calorieContentList) {
      recipesLowCalories.add(recipeService.getRecipeByCalorie(calorie));
    }

    return recipesLowCalories;
  }

  private List<Recipe> getFastAndDelicious(int time) {
    List<Recipe> recipes = recipeService.getRecipesByTimeLessThanEqual(time);
    List<Double> avg = searchFilter.getAverageRating(recipes);
    List<Recipe> sortedList = new ArrayList<>(recipes);

    sortedList.sort(Comparator.comparing(s -> avg.get(recipes.indexOf(s))));

    return sortedList;
  }
}
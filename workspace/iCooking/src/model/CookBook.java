package model;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Cookbook Manager provides methods for user to get recipe by the name of
 * recipe, edit specific recipe, delete specific recipe, add new recipe.
 * 
 * @author Xu Chen
 * 
 * @version 1.0
 *
 */

public class CookBook implements Serializable {

	private String cookBookName = null;
	DBConnector toDB;
	private ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
	private ArrayList<Recipe> temp = new ArrayList<Recipe>();
	private ArrayList<Recipe> recipeResult_search = new ArrayList<Recipe>();
	private ArrayList<Recipe> recipeResult_filter = new ArrayList<Recipe>();
	private boolean searched = false; // whether the recipeList is a result
										// after searching
	private boolean filtered = false; // whether the recipeList is a result
										// after filter
	private TimeComparator tc = new TimeComparator();

	/**
	 * Constructor of cookbook with a specific name.
	 * 
	 * @param name
	 *            the name of the cookbook to set
	 */
	public CookBook(String name) {
		this.cookBookName = name;
		toDB = new DBConnector();
	}

	/**
	 * Gets the name of the cookbook.
	 * 
	 * @return the name of the cookbook
	 */
	public String getCookBookName() {
		return cookBookName;
	}

	/**
	 * Sets the name of the cookbook.
	 * 
	 * @param cookBookName
	 *            name of the cookbook to set
	 */
	public void setCookBookName(String cookBookName) {
		this.cookBookName = cookBookName;
	}

	/**
	 * @return the recipe result list
	 */
	public ArrayList<Recipe> getRecipeList() {
		return recipeList;
	}

	/**
	 * @return the recipe result list from searching
	 */
	public ArrayList<Recipe> getRecipeResult_search() {
		return recipeResult_search;
	}

	/**
	 * @return the recipe result list from filter
	 */
	public ArrayList<Recipe> getRecipeResult_filter() {
		return recipeResult_filter;
	}

	/**
	 * @return whether the recipe results are from searching
	 */
	public boolean isSearched() {
		return searched;
	}

	/**
	 * @param searched
	 *            whether recipe results are from searching to set
	 */
	public void setSearched(boolean searched) {
		this.searched = searched;
	}

	/**
	 * @return whether the recipe results are from filter
	 */
	public boolean isFiltered() {
		return filtered;
	}

	/**
	 * @param filtered
	 *            whether recipe results are from filter to set
	 */
	public void setFiltered(boolean filtered) {
		this.filtered = filtered;
	}

	/**
	 * Searches the recipe(s) based on a specific name.
	 * 
	 * @param name
	 *            the name of the recipe to search
	 * @return the recipe of the name
	 */
	public Recipe getRecipe(String name) {
		Recipe recipeResult = null;
		for (int i = 0; i < recipeList.size(); i++) {
			if (recipeList.get(i).getRecipeName().equals(name)) {
				recipeResult = recipeList.get(i);
			}
		}
		return recipeResult;
	}

	/**
	 * Searches for the recipes with keywords in recipe name or in ingredient
	 * names.
	 * 
	 * @author Shen Duan
	 * @param keywords
	 *            the keywords used for searching
	 * @return the list of corresponding recipes
	 */
	public ArrayList<Recipe> searchByKeyWords(String keywords) {
		this.recipeResult_search = toDB.search(keywords);
		if (filtered) {
			intersact();
		} else {
			recipeList.clear();
			recipeList.addAll(recipeResult_search);
		}
		resortByTime();
		return recipeList;
	}

	/**
	 * Gets the intersection of a searched result and a filtered result without
	 * changing the recipe result of searching and filter.
	 * 
	 * @author Shen Duan
	 * @param recipeResult_search
	 * @param recipeResult_filter
	 * @return the final intersection of two lists
	 */
	public ArrayList<Recipe> intersact() {
		recipeList.clear();
		temp.clear();
		temp.addAll(recipeResult_search);
		recipeResult_search.retainAll(recipeResult_filter);
		recipeList.addAll(recipeResult_search);
		recipeResult_search.clear();
		recipeResult_search.addAll(temp);
		resortByTime();
		return recipeList;
	}

	public void edit(Recipe recipe) {

	}

	public void delete(Recipe recipe) {

	}

	public void add(Recipe recipe) {
		recipeList.add(recipe);
	}

	/**
	 * Filters by the given category for a list of recipes.
	 * 
	 * @author Shen Duan
	 * @param category
	 *            the category used for filter
	 * @return the corresponding list of recipes
	 */
	public ArrayList<Recipe> filter(String category) {
		this.recipeResult_filter = toDB.filter(category);
		if (searched) {
			intersact();
		} else {
			recipeList.clear();
			recipeList.addAll(recipeResult_filter);
		}
		resortByTime();
		return recipeList;
	}

	/**
	 * Recalculates the ingredient amounts, preparation time and cooking time of
	 * the specific recipe, according to the request serving amount.
	 * 
	 * @param recipe
	 *            the chosen recipe
	 * @param servings
	 *            the aim serve amount
	 */
	public void recalculateServings(Recipe recipe, int servings) {
		double temp = recipe.getServings();
		recipe.setServings(servings);
		int tempPreparationTime = recipe.getPreparationTime();
		int tempCookingTime = recipe.getCookingTime();
		for (int i = 0; i <= recipe.getIngredientList().size() - 1; i++) {
			double tempAmount = recipe.getIngredientList().get(i).getIngredientQuantity();
			recipe.getIngredientList().get(i).setIngredientAmount(tempAmount * servings / temp);
		}
		recipe.setPreparationTime((int) (tempPreparationTime * servings / temp));
		recipe.setCookingTime((int) (tempCookingTime * servings / temp));

	}

	/**
	 * This class provides a comparator to compare two recipes by their time
	 * (preparation time + cooking time) in order to sort the recipe result
	 * list.
	 * 
	 * @author Shen Duan
	 *
	 */
	class TimeComparator implements Comparator<Recipe> {

		/**
		 * Compares its two arguments for order.
		 * 
		 * @param r1
		 *            the first recipe to be compared
		 * @param r2
		 *            the second recipe to be compared
		 * @return 1 or -1 as the time of the first recipe is greater than, or,
		 *         equal to or less than the time of the second recipe
		 */
		public int compare(Recipe r1, Recipe r2) {

			if ((r1.getPreparationTime() + r1.getCookingTime()) > (r2.getPreparationTime() + r2.getCookingTime())) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	/**
	 * Resorts the recipe list by time (preparation time + cooking time) in
	 * ascending order
	 * 
	 * @author Shen Duan
	 */
	public void resortByTime() {
		Collections.sort(recipeList, tc);
	}

	/**
	 * Overrides toString method
	 * 
	 * @return new format of CookBook
	 */
	public String toString() {
		return "CookBook [recipeList=" + recipeList + ", cookBookName=" + cookBookName + "]";
	}
}

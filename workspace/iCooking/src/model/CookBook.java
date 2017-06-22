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
 * @author Xu Chen, Shen Duan
 * 
 * @version 1.0
 *
 */

public class CookBook implements Serializable {

	private String cookBookName = null;
	private DBConnector toDB;
	private ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
	// private ArrayList<Recipe> temp = new ArrayList<Recipe>();
	// private ArrayList<Recipe> recipeResult_search = new ArrayList<Recipe>();
	// private ArrayList<Recipe> recipeResult_filter = new ArrayList<Recipe>();
	private String keyWord = null; // the keyword for searching
	private String filterName = null; // the category id for filter
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

	// /**
	// * @return the recipe result list from searching
	// */
	// public ArrayList<Recipe> getRecipeResult_search() {
	// return recipeResult_search;
	// }
	//
	// /**
	// * @return the recipe result list from filter
	// */
	// public ArrayList<Recipe> getRecipeResult_filter() {
	// return recipeResult_filter;
	// }

	/**
	 * @return the keyWord
	 */
	public String getKeyWord() {
		return keyWord;
	}

	/**
	 * @param keyWord
	 *            the keyWord
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	/**
	 * Sets the key word null.
	 */
	public void clearKeyWord() {
		this.keyWord = null;
	}

	/**
	 * @return the name of the filter
	 */
	public String getFilterName() {
		return filterName;
	}

	/**
	 * @param filterName
	 *            the name of the filter
	 */
	public void setFilterName(String filter) {
		this.filterName = filter;
	}

	/**
	 * Sets the filter name to null.
	 */
	public void clearFilterName() {
		this.filterName = null;
	}

	/**
	 * Gets the recipe by its name.
	 * 
	 * @param name
	 *            the name of the recipe
	 * @return the recipe with the given name
	 */
	public Recipe getRecipe(String name) {
		Recipe r = null;
		r = toDB.getRecipe(name);
		return r;
	}

	/**
	 * Searches for the recipes with keywords in recipe name or in ingredient
	 * and the last filter. names.
	 * 
	 * @author Shen Duan
	 * @param keyWord
	 *            the keywords used for searching
	 * @return the list of corresponding recipes
	 */
	public ArrayList<Recipe> searchByKeyWords(String keyWord) {
		setKeyWord(keyWord);
		this.recipeList = toDB.search(keyWord, filterName);
		resortByTime();
		return recipeList;
	}

	// /**
	// * Gets the intersection of a searched result and a filtered result
	// without
	// * changing the recipe result of searching and filter.
	// *
	// * @author Shen Duan
	// * @param recipeResult_search
	// * @param recipeResult_filter
	// * @return the final intersection of two lists
	// */
	// public ArrayList<Recipe> intersact() {
	// recipeList.clear();
	// temp.clear();
	// temp.addAll(recipeList);
	// recipeList.retainAll(recipeResult_filter);
	// recipeList.addAll(recipeList);
	// recipeList.clear();
	// recipeList.addAll(temp);
	// //resortByTime();
	// return recipeList;
	// }

	public void edit(Recipe recipe) {
		toDB.editRecipe(recipe);
	}

	/**
	 * Deletes the recipe from the database.
	 * 
	 * @param recipe
	 *            the recipe to be deleted
	 */
	public void delete(Recipe recipe) {
		toDB.deleteRecipe(recipe);
	}

	public void add(Recipe recipe) {
		toDB.addRecipe(recipe);
	}

	/**
	 * Filters by the given category for a list of recipes.
	 * 
	 * @author Shen Duan
	 * @param category
	 *            the category used for filter
	 * @return the corresponding list of recipes
	 */
	public ArrayList<Recipe> filter(String filterName) {
		setFilterName(filterName);
		this.recipeList = toDB.filter(keyWord, filterName);
		resortByTime();
		return recipeList;
	}

	/**
	 * Recalculates the ingredient amounts, preparation time and cooking time of
	 * the specific recipe, according to the request serving amount.
	 * 
	 * @param r
	 *            the chosen recipe
	 * @param servings
	 *            the aim serving amount
	 */
	public void recalculateServings(Recipe r, double servings) {
		double temp = r.getServings();
		r.setServings(servings);
		int tempPreparationTime = r.getPreparationTime();
		int tempCookingTime = r.getCookingTime();
		for (int i = 0; i <= r.getIngredientList().size() - 1; i++) {
			double tempAmount = r.getIngredientList().get(i).getIngredientQuantity();
			r.getIngredientList().get(i).setIngredientQuantity(tempAmount * servings / temp);
		}
		r.setPreparationTime((int) (tempPreparationTime * servings / temp));
		r.setCookingTime((int) (tempCookingTime * servings / temp));

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
	 * Add a category to database.
	 * @param c
	 * the category to be added
	 */
	public void addCategory(Category c){
		toDB.addCategory(c);
	}
	
	
	public void addUnit(String unit){
		toDB.addUnit(unit);
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

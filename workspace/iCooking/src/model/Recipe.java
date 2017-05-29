package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class provides an implementation of Recipe. It defines the attributes
 * and methods about Recipe.
 * 
 * @author Yue Wang
 *
 * @version 1.0
 */
class Recipe implements Serializable {

	private int recipeID;
	private String recipeName;

	// cuisine style of recipe
	private String cuisine;

	// the number of servers
	private double servings;
	private String author;

	// special attribute of recipe
	private Category category;
	private int preparationTime;
	private int cookingTime;

	// the list of ingredients needed for the recipe
	private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();

	// the list of preparationsteps needed for the recipe
	private ArrayList<String> preparationStepList = new ArrayList<String>();

	/**
	 * constructor of Recipe
	 * 
	 * @param name
	 * @param cuisine
	 *            the style of the dish
	 * @param servings
	 *            the number of servers
	 */
	public Recipe(String name, String cuisine, double servings) {
		this.recipeName = name;
		this.cuisine = cuisine;
		this.servings = servings;
	}

	/**
	 * get ID of recipe
	 * 
	 * @return recipeID
	 */
	public int getRecipeID() {
		return recipeID;
	}

	/**
	 * set ID of recipe
	 * 
	 * @param recipeID
	 */
	public void setRecipeID(int recipeID) {
		this.recipeID = recipeID;
	}

	/**
	 * get name of recipe
	 * 
	 * @return recipeName
	 */
	public String getRecipeName() {
		return recipeName;
	}

	/**
	 * set name of recipe
	 * 
	 * @param recipeName
	 */
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * get cuisine style of recipe
	 * 
	 * @return cuisine
	 */
	public String getCuisine() {
		return cuisine;
	}

	/**
	 * set cuisine style of recipe
	 * 
	 * @param cuisine
	 */
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	/**
	 * get the number of servers
	 * 
	 * @return servings
	 */
	public double getServings() {
		return servings;
	}

	/**
	 * set the number of servers
	 * 
	 * @param servings
	 */
	public void setServings(double servings) {
		this.servings = servings;
	}

	/**
	 * get the author of recipe
	 * 
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * set the author of recipe
	 * 
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * get the category of recipe
	 * 
	 * @return category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * set the category of recipe
	 * 
	 * @param category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * get time of cooking
	 * 
	 * @return cookingTime
	 */
	public int getCookingTime() {
		return cookingTime;
	}

	/**
	 * set time of cooking
	 * 
	 * @param time
	 */
	public void setCookingTime(int time) {
		this.cookingTime = time;
	}

	/**
	 * get time of preparating for the recipe
	 * 
	 * @return preparationTime
	 */
	public int getPreparationTime() {
		return preparationTime;
	}

	/**
	 * set time of preparating for the recipe
	 * 
	 * @param preparationTime
	 */
	public void setPreparationTime(int preparationTime) {
		this.preparationTime = preparationTime;
	}

	/**
	 * get the list of ingredients
	 * 
	 * @return ingredientList
	 */
	public ArrayList<Ingredient> getIngredientList() {
		return ingredientList;
	}

	/**
	 * set the list of ingredients
	 * 
	 * @param ingredientList
	 */
	public void setIngredientList(ArrayList<Ingredient> ingredientList) {
		this.ingredientList = ingredientList;
	}

	/**
	 * get the list of preparationsteps
	 * 
	 * @return preparationStepList
	 */
	public ArrayList<String> getPreparationStepList() {
		return preparationStepList;
	}

	/**
	 * set the list of preparationsteps
	 * 
	 * @param preparationStepList
	 */
	public void setPreparationStepList(ArrayList<String> preparationStepList) {
		this.preparationStepList = preparationStepList;
	}

	/**
	 * add ingredients into ingredientList
	 * 
	 * @param ingredient
	 */
	public void addIngredient(Ingredient ingredient) {
		ingredientList.add(ingredient);
	}

	/**
	 * add preparation step into preparationstepList
	 * 
	 * @param preparationStep
	 */
	public void addPreparationStep(String preparationStep) {
		preparationStepList.add(preparationStep);
	}

	/**
	 * add recipe and its content to database
	 * 
	 * @param dbconnector
	 *            connector connecting database and java
	 */
	public void addtoDatabase(DBConnector dbconnector) {
		dbconnector.insert(this);
	}

	@Override
	public String toString() {
		return "Recipe [recipeID=" + recipeID + ", recipeName=" + recipeName + ", cuisine=" + cuisine + ", servings="
				+ servings + ", author=" + author + ", category=" + category + ", preparationTime=" + preparationTime
				+ ", cookingTime=" + cookingTime + ", ingredientList=" + ingredientList + ", preparationStepList="
				+ preparationStepList + "]";
	}

}

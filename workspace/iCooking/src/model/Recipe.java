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
public class Recipe implements Serializable {

	private int recipeID;
	private String recipeName;

	// cuisine style of recipe
	private String cuisine;

	// the number of servers
	private double servings;
	private String author;

	// special attribute of recipe
	private int category_id;
	private double preparationTime;
	private double cookingTime;

	// the list of ingredients needed for the recipe
	private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();

	// the list of preparation steps needed for the recipe
	private ArrayList<String> preparationStepList = new ArrayList<String>();

	/**
	 * Creates a Recipe object with its name, cuisine and servings. 
	 * 
	 * @param name
	 * the name of the recipe
	 * @param cuisine
	 *            the cuisine of the dish
	 * @param servings
	 *            how many people does the recipe serve
	 */
	public Recipe(String name, String cuisine, double servings) {
		this.recipeName = name;
		this.cuisine = cuisine;
		this.servings = servings;
	}

	/**
	 * Default constructor
	 */
	public Recipe() {
		
	}

	/**
	 * Gets the ID of the recipe.
	 * 
	 * @return recipeID
	 */
	public int getRecipeID() {
		return recipeID;
	}
	
	/**
	 * Sets the ID of the recipe.
	 * @param id
	 * the ID of the recipe
	 */
	public void setRecipeID(int id) {
		this.recipeID = id;
		
	}


	/**
	 * Gets the name of the recipe.
	 * 
	 * @return recipeName
	 */
	public String getRecipeName() {
		return recipeName;
	}

	/**
	 * Sets the name of the recipe.
	 * 
	 * @param name
	 */
	public void setRecipeName(String name) {
		this.recipeName = name;
	}

	/**
	 * Gets the cuisine of the recipe.
	 * 
	 * @return cuisine
	 */
	public String getCuisine() {
		return cuisine;
	}

	/**
	 * Sets the cuisine of the recipe.
	 * 
	 * @param cuisine
	 */
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	/**
	 * Gets the servings of the recipe.
	 * 
	 * @return servings
	 */
	public double getServings() {
		return servings;
	}

	/**
	 * Sets the servings of the recipe.
	 * 
	 * @param servings
	 */
	public void setServings(double servings) {
		this.servings = servings;
	}

	/**
	 * Gets the author of recipe.
	 * 
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 *Sets the author of recipe.
	 * 
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets the category of recipe.
	 * 
	 * @return category
	 */
	public int getCategoryID() {
		return category_id;
	}

	/**
	 * Sets the category of recipe.
	 * 
	 * @param category
	 */
	public void setCategoryID(int category) {
		this.category_id = category;
	}

	/**
	 * Gets the cooking time of the recipe
	 * 
	 * @return cookingTime
	 */
	public double getCookingTime() {
		return cookingTime;
	}

	/**
	 * Sets the cooking time of the recipe
	 * 
	 * @param time
	 */
	public void setCookingTime(int time) {
		this.cookingTime = time;
	}

	/**
	 * Gets the preparation time of the recipe.
	 * 
	 * @return preparationTime
	 */
	public double getPreparationTime() {
		return preparationTime;
	}

	/**
	 * Sets the preparation time of the recipe
	 * 
	 * @param time
	 */
	public void setPreparationTime(int time) {
		this.preparationTime = time;
	}

	/**
	 * Gets the list of ingredients.
	 * 
	 * @return ingredientList
	 */
	public ArrayList<Ingredient> getIngredientList() {
		return ingredientList;
	}

	/**
	 * Sets the list of ingredients.
	 * 
	 * @param ingredientList
	 */
	public void setIngredientList(ArrayList<Ingredient> ingredientList) {
		this.ingredientList = ingredientList;
	}

	/**
	 * Gets the list of preparation steps.
	 * 
	 * @return preparationStepList
	 */
	public ArrayList<String> getPreparationStepList() {
		return preparationStepList;
	}

	/**
	 * Sets the list of preparation steps.
	 * 
	 * @param preparationStepList
	 */
	public void setPreparationStepList(ArrayList<String> preparationStepList) {
		this.preparationStepList = preparationStepList;
	}

	/**
	 * Adds ingredients into ingredientList
	 * 
	 * @param ingredient
	 */
	public void addIngredient(Ingredient ingredient) {
		ingredientList.add(ingredient);
	}

	/**
	 * Adds preparation step into preparationstepList
	 * 
	 * @param preparationStep
	 */
	public void addPreparationStep(String preparationStep) {
		preparationStepList.add(preparationStep);
	}

	@Override
	public String toString() {
		return "Recipe [recipeID=" + recipeID + ", recipeName=" + recipeName + ", cuisine=" + cuisine + ", servings="
				+ servings + ", author=" + author + ", category_id=" + category_id + ", preparationTime=" + preparationTime
				+ ", cookingTime=" + cookingTime + ", ingredientList=" + ingredientList + ", preparationStepList="
				+ preparationStepList + "]";
	}



}

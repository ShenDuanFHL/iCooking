package model;

import java.io.Serializable;
import java.sql.*;

/**
 * This class implements different ingredients.
 * 
 * @author Guanyu Li
 * @version 1.0
 */
class Ingredient implements Serializable {

	private String ingredientName;
	private double ingredientQuantity;
	private String ingredientDescription;
	private String ingredientUnit;
	private int ingredientID;

	/**
	 * Creates an Ingredient object with its name, amount, and description.
	 * 
	 * @param name
	 *            name of the ingredient
	 * @param quantity
	 *            quantity of the ingredient
	 * @param description
	 *            description of the ingredient
	 */
	public Ingredient(String name, double quantity, String unit) {
		this.ingredientName = name;
		this.ingredientQuantity = quantity;
		this.ingredientUnit = unit;
	}

	/**
	 * Creates an Ingredient object with its name, quantity, unit and description.
	 * 
	 * @param name
	 *            name of the ingredient
	 * @param quantity
	 *            quantity of the ingredient
	 * @param unit
	 *            unit of the ingredient
	 * @param description
	 *            description of the ingredient
	 */
	public Ingredient(String name, double quantity, String unit, String description) {
		this.ingredientName = name;
		this.ingredientQuantity = quantity;
		this.ingredientUnit = unit;
		this.ingredientDescription = description;
	}

	public Ingredient() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the name of the ingredient.
	 * 
	 * @return the name of the ingredient
	 */
	public String getIngredientName() {
		return ingredientName;
	}

	/**
	 * Sets the name of the ingredient.
	 * 
	 * @param ingredientName
	 *            the name of the ingredient
	 */
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	/**
	 * Gets the quantity of the ingredient.
	 * 
	 * @return the quantity of the ingredient
	 */
	public double getIngredientQuantity() {
		return ingredientQuantity;
	}

	/**
	 * Sets the quantity of the ingredient.
	 * 
	 * @param ingredientQuantity
	 *            the quantity of the ingredient
	 */
	public void setIngredientAmount(double ingredientQuantity) {
		this.ingredientQuantity = ingredientQuantity;
	}

	/**
	 * Gets the description of the ingredient.
	 * 
	 * @return the description of the ingredient
	 */
	public String getIngredientDescription() {
		return ingredientDescription;
	}

	/**
	 * Sets the description of the ingredient.
	 * 
	 * @param ingredientDescription
	 *            the description of the ingredient
	 */
	public void setIngredientDescription(String ingredientDescription) {
		this.ingredientDescription = ingredientDescription;
	}

	/**
	 * Gets the unit of the ingredient.
	 * 
	 * @return the unit of the ingredient
	 */
	public String getIngredientUnit() {
		return ingredientUnit;
	}

	/**
	 * Sets the unit of the ingredient.
	 * 
	 * @param unit
	 *            the unit of the ingredient
	 */
	public void setIngredientUnit(String unit) {
		this.ingredientUnit = unit;
	}

	/**
	 * Gets the ID of the ingredient.
	 * 
	 * @return the ID of the ingredient
	 */
	public long getIngredientID() {
		return ingredientID;
	}

	/**
	 * Sets the ID of the ingredient.
	 * 
	 * @param ingredientID
	 *            the ID of the ingredient
	 */
	public void setIngredientID(int ingredientID) {
		this.ingredientID = ingredientID;
	}

	@Override
	public String toString() {
		return "Ingredient [name=" + ingredientName + ", quantity=" + ingredientQuantity + ", description="
				+ ingredientDescription + ", unit=" + ingredientUnit + ", ingredientID=" + ingredientID + "]";
	}

}

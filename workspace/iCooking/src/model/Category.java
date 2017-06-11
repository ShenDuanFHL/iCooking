package model;

import java.io.Serializable;
import java.sql.*;

/**
 * This class provides an implementation of category. Every Recipe belongs to a
 * specific category. A category is defined by its name and the description.
 * 
 * @author Yiwei Ma
 * @version 1.0
 *
 */

class Category implements Serializable {
	private int categoryID;
	private String categoryName;
	private String categoryDescription;

	
	
	/**
	 * Creates an Category with the specific.
	 * 
	 * @param name
	 *            the name of the category
	 */
	public Category(int categoryID,String name) {
		this.categoryID = categoryID;
		this.categoryName = name;

	}
	/**
	 * Creates an Category with the specific name and description.
	 * 
	 * @param name
	 *            the name of the category
	 * @param description
	 *            the description of category
	 */
	public Category(String name, String description) {
		this.categoryName = name;
		this.categoryDescription = description;

	}

	/**
	 * Gets the ID of the category.
	 * 
	 * @return categoryID
	 */
	public int getCategoryID() {
		return categoryID;
	}

	/**
	 * Sets the ID of the category.
	 * 
	 * @param categoryID
	 *            the ID of the category
	 */
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	/**
	 * Gets the name of the category.
	 * 
	 * @return name
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Sets the name of the category.
	 * 
	 * @param name
	 */
	public void setCategoryName(String name) {
		this.categoryName = name;
	}

	/**
	 * Gets the description of the category.
	 * 
	 * @return description
	 */
	public String getCategoryDescription() {
		return categoryDescription;
	}

	/**
	 * Sets the description of the category.
	 * 
	 * @param description
	 */
	public void setCategoryDescription(String description) {
		this.categoryDescription = description;
	}

	@Override
	public String toString() {
		return "Category [categoryID=" + categoryID + ", categoryName=" + categoryName + ", categoryDescription="
				+ categoryDescription + "]";
	}

	

	
}

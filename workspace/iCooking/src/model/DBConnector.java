package model;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class implements a connection to MySQL server. It realizes retrieving,
 * updating, deleting... the basic SQL queries. results and updating.
 * 
 * @author Shen Duan
 * @version 1.0
 *
 */
public class DBConnector {

	/**
	 * Gets the access to the database.
	 * 
	 * @return the connection to the database
	 * @throws SQLException
	 *             if a database access error occurs or this method is called on
	 *             a closed connection
	 */
	private Connection access() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cookbook", "root", "");
		return con;
	}

	/**
	 * Retrieves the recipe results in an Array List from the Database,
	 * according to the recipe ID.
	 * 
	 * @param rset_rID
	 *            the result set of recipe id
	 * @return the corresponding list of recipe
	 */
	public ArrayList<Recipe> retrieveRecipe(ArrayList<Integer> rIDList) {
		ArrayList<Recipe> recipeList = null;
		try {
			Connection con = access();
			Statement stmt1 = con.createStatement();
			Statement stmt2 = con.createStatement();
			Statement stmt3 = con.createStatement();
			recipeList = new ArrayList<Recipe>();
			for (int i = 0; i < rIDList.size(); i++) {
				int rID = rIDList.get(i);

				ResultSet rset = stmt1.executeQuery("SELECT * FROM recipe where recipe_id = " + rID + ";");
				while (rset.next()) {
					Recipe r = new Recipe();
					r.setRecipeName(rset.getString("name"));
					r.setRecipeID(rset.getInt("recipe_id"));
					// r.setCuisine(rset.getString("cuisine"));
					r.setServings(rset.getDouble("servings"));
					// r.setAuthor(rset.getString("author"));
					r.setPreparationTime(rset.getInt("preparationTime"));
					r.setCookingTime(rset.getInt("cookingTime"));

					ResultSet rset_ingredient = stmt2
							.executeQuery("SELECT * FROM ingredient where recipe_id = " + rID + ";");
					ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();

					while (rset_ingredient.next()) {
						Ingredient ingr = new Ingredient();
						ingr.setIngredientName(rset_ingredient.getString("name"));
						ingr.setIngredientAmount(rset_ingredient.getDouble("quantity"));
						ingr.setIngredientUnit(rset_ingredient.getString("unit"));
						ingr.setIngredientDescription(rset_ingredient.getString("description"));
						ingredientList.add(ingr);
					}

					ResultSet rset_prepStep = stmt3
							.executeQuery("SELECT * FROM preparation_step where recipe_id = " + rID + ";");
					ArrayList<String> prepStepList = new ArrayList<String>();

					while (rset_prepStep.next()) {
						prepStepList.add(rset_prepStep.getString("description"));
					}

					r.setIngredientList(ingredientList);
					r.setPreparationStepList(prepStepList);

					recipeList.add(r);
				}
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipeList;
	}

	/**
	 * Searches for the recipes whose recipe name or ingredient name contain the
	 * given keywords.
	 * 
	 * @param keywords
	 *            the keywords used for searching
	 * @return the corresponding list of recipes
	 */
	public ArrayList<Recipe> search(String keywords) {
		ArrayList<Recipe> recipeList = null;
		try {
			Connection con = access();
			Statement stmt = con.createStatement();
			// get the corresponding recipe id
			ResultSet rset_rID = stmt.executeQuery(
					"SELECT distinct recipe.recipe_id from recipe, ingredient where recipe.recipe_id = ingredient.recipe_id and ( recipe.name LIKE'%"
							+ keywords + "%' or ingredient.name LIKE '%" + keywords + "%')");
			ArrayList<Integer> rID = new ArrayList<Integer>();
			while (rset_rID.next()) {
				rID.add(rset_rID.getInt(1));
			}

			recipeList = retrieveRecipe(rID);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipeList;
	}

	/**
	 * Filters the recipes according to the given category.
	 * 
	 * @param category
	 *            the category used for filter
	 * @return the corresponding recipe list
	 */
	public ArrayList<Recipe> filter(String category) {
		ArrayList<Recipe> recipeList = null;
		try {
			Connection con = access();
			Statement stmt = con.createStatement();
			ResultSet rset_rID = stmt.executeQuery(
					"SELECT recipe_id from recipe,category where recipe.category_id = category.category_id and category.name = '"
							+ category + "';");
			ArrayList<Integer> rID = new ArrayList<Integer>();
			while (rset_rID.next()) {
				rID.add(rset_rID.getInt(1));
			}
			recipeList = retrieveRecipe(rID);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipeList;
	}

	// /**
	// * Updates the name and servings.
	// *
	// */
	// public void update() {
	// try {
	// Connection con = access();
	// Statement stmt = con.createStatement();
	// stmt.executeUpdate("INSERT INTO recipe (name, servings) VALUES('Xiao
	// Longbao',4)");
	// con.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * Adds a recipe to database. Adds recipe information to recipe table;
	 * preparation step list to preparation_step table; ingredient list to
	 * ingredient table. Recipe ID is assigned automatically by the database.
	 * 
	 * @param r
	 *            the recipe
	 */
	public void addRecipe(Recipe r) {
		try {
			Connection con = access();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO recipe (name, servings, preparationTime, cookingTime,category_id) VALUES('"
					+ r.getRecipeName() + "', " + r.getServings() + ", " + r.getCookingTime() + ", "
					+ r.getPreparationTime() + "," + r.getCategoryID() + ")");
			// get the last added recipe
			ResultSet rset = stmt.executeQuery("select * from recipe order by recipe_id desc limit 1");

			if (rset.next()) {
				r.setRecipeID(rset.getInt(1));
			}

			ArrayList<String> prepStep = r.getPreparationStepList();
			for (int i = 0; i < prepStep.size(); i++) {
				stmt.executeUpdate("INSERT INTO preparation_step (recipe_id, step, description) VALUES ("
						+ r.getRecipeID() + ", " + i + ", '" + prepStep.get(i) + "')");
			}

			ArrayList<Ingredient> ingList = r.getIngredientList();
			for (int i = 0; i < ingList.size(); i++) {
				stmt.executeUpdate("INSERT INTO ingredient (recipe_id, name, quantity, unit, description) VALUES ("
						+ r.getRecipeID() + ", '" + ingList.get(i).getIngredientName() + "', "
						+ ingList.get(i).getIngredientQuantity() + ", '" + ingList.get(i).getIngredientUnit() + "', '"
						+ ingList.get(i).getIngredientDescription() + "')");
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a category to database.
	 * 
	 * @param c
	 *            the category being added
	 */
	public void addCategory(Category c) {
		try {
			Connection con = access();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO category (category_id, name, description) VALUES(" + c.getCategoryID()
					+ ", '" + c.getCategoryName() + "', '" + c.getCategoryDescription() + "'); ");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds unit to database.
	 * 
	 * @param singular
	 *            the singular form of the unit
	 * @param plural
	 *            the plural form of the unit
	 */
	public void addUnit(String singular, String plural) {
		try {
			Connection con = access();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO unit (singular, plural) VALUES('" + singular + "', '" + plural + "');");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds an Ingredient to database.
	 * 
	 * @param name
	 *            the name of the ingredient
	 */
	public void addIngredients(String name) {
		try {
			Connection con = access();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO ingredients (name) VALUES('" + name + "');");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

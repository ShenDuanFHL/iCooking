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
	 * Gets a recipe with the certain recipe name.
	 * 
	 * @param name
	 *            the name of the recipe
	 * @return the recipe
	 */
	public Recipe getRecipe(String name) {
		Recipe r = new Recipe();
		ArrayList<Ingredient> ingrList = new ArrayList<Ingredient>();
		ArrayList<String> prepStep = new ArrayList<String>();
		int recipe_id = 0;
		try {
			Connection con = access();
			Statement stmt1 = con.createStatement();
			ResultSet rset1 = stmt1.executeQuery("SELECT * from recipe where recipe.name = '" + name + "';");
			while (rset1.next()) {
				r.setRecipeName(rset1.getString("name"));
				recipe_id = rset1.getInt("recipe_id");
				r.setRecipeID(recipe_id);
				r.setCuisine(rset1.getString("cuisine"));
				r.setServings(rset1.getDouble("servings"));
				r.setAuthor(rset1.getString("author"));
				r.setPreparationTime(rset1.getInt("preparationTime"));
				r.setCookingTime(rset1.getInt("cookingTime"));
				r.setCategoryID(rset1.getInt("category_id"));
			}
			Statement stmt2 = con.createStatement();
			ResultSet rset2 = stmt2.executeQuery("SELECT * from ingredient where recipe_id = " + recipe_id + ";");
			while (rset2.next()) {
				Ingredient ingr = new Ingredient();
				ingr.setIngredientName(rset2.getString("name"));
				ingr.setIngredientQuantity(rset2.getDouble("quantity"));
				ingr.setIngredientUnit(rset2.getString("unit"));
				ingr.setIngredientDescription(rset2.getString("description"));
				ingrList.add(ingr);
			}
			r.setIngredientList(ingrList);
			Statement stmt3 = con.createStatement();
			ResultSet rset3 = stmt3.executeQuery("SELECT * from preparation_step where recipe_id = " + recipe_id + ";");
			while (rset3.next()) {
				prepStep.add(rset3.getString("description"));
			}
			r.setPreparationStepList(prepStep);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
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
					r.setCuisine(rset.getString("cuisine"));
					r.setServings(rset.getDouble("servings"));
					r.setAuthor(rset.getString("author"));
					r.setCategoryID(rset.getInt("category_id"));
					r.setPreparationTime(rset.getInt("preparationTime"));
					r.setCookingTime(rset.getInt("cookingTime"));

					ResultSet rset_ingredient = stmt2
							.executeQuery("SELECT * FROM ingredient where recipe_id = " + rID + ";");
					ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();

					while (rset_ingredient.next()) {
						Ingredient ingr = new Ingredient();
						ingr.setIngredientName(rset_ingredient.getString("name"));
						ingr.setIngredientQuantity(rset_ingredient.getDouble("quantity"));
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
	 * @param filter
	 * @return the corresponding list of recipes
	 */
	public ArrayList<Recipe> search(String keywords, String filter) {
		ArrayList<Recipe> recipeList = null;
		try {
			Connection con = access();
			Statement stmt = con.createStatement();
			// get the corresponding recipe id
			ResultSet rset_rID;
			if(filter == null){
				rset_rID = stmt.executeQuery(
						"SELECT distinct recipe.recipe_id from recipe, ingredient where recipe.recipe_id = ingredient.recipe_id and ( recipe.name LIKE'%"
								+ keywords + "%' or ingredient.name LIKE '%" + keywords + "%')");
			}else{
				rset_rID = stmt.executeQuery(
						"SELECT distinct recipe.recipe_id from recipe, ingredient, category where recipe.recipe_id = ingredient.recipe_id and ( recipe.name LIKE'%"
								+ keywords + "%' or ingredient.name LIKE '%" + keywords + "%') and recipe.category_id = category.category_id and category.name = '" + filter +"';");
			}
			
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
	 * @param filter
	 * @return the corresponding recipe list
	 */
	public ArrayList<Recipe> filter(String keyWord, String filter) {
		ArrayList<Recipe> recipeList = null;
		try {
			Connection con = access();
			Statement stmt = con.createStatement();
			// get the corresponding recipe id
			ResultSet rset_rID;
			if(keyWord == null){
				rset_rID= stmt.executeQuery("SELECT recipe_id from recipe,category where recipe.category_id = category.category_id and category.name = '" + filter + "';");
			}else{
				rset_rID = stmt.executeQuery("SELECT distinct recipe.recipe_id from recipe, ingredient, category where recipe.recipe_id = ingredient.recipe_id and ( recipe.name LIKE'%"
						+ keyWord + "%' or ingredient.name LIKE '%" + keyWord + "%') and recipe.category_id = category.category_id and category.name = '" + filter +"';");
			}
			
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
	 * Adds a recipe to database. Adds recipe information to recipe table;
	 * preparation step list to preparation_step table; ingredient list to
	 * ingredient table. Recipe ID is assigned automatically by the database.
	 * 
	 * @param r
	 *            the recipe
	 */
	public void addRecipe(Recipe r) {
		try {
			int i = 0;
			int j = 0;
			Connection con = access();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(
					"INSERT INTO recipe (name, servings, cuisine, preparationTime, cookingTime,category_id) VALUES('"
							+ r.getRecipeName() + "', " + r.getServings() + ", '" + r.getCuisine() + "',"
							+ r.getCookingTime() + ", " + r.getPreparationTime() + "," + r.getCategoryID() + ")");
			// get the last added recipe
			ResultSet rset = stmt.executeQuery("select * from recipe order by recipe_id desc limit 1");

			if (rset.next()) {
				r.setRecipeID(rset.getInt(1));
			}

			ArrayList<Ingredient> ingList = r.getIngredientList();
			for (i = 0; i < ingList.size(); i++) {
				stmt.executeUpdate("INSERT INTO ingredient (recipe_id, name, quantity, unit, description) VALUES ("
						+ r.getRecipeID() + ", '" + ingList.get(i).getIngredientName() + "', "
						+ ingList.get(i).getIngredientQuantity() + ", '" + ingList.get(i).getIngredientUnit() + "', '"
						+ ingList.get(i).getIngredientDescription() + "')");
			}
			ArrayList<String> prepStep = r.getPreparationStepList();
			for (j = 0; j < prepStep.size(); j++) {
				stmt.executeUpdate("INSERT INTO preparation_step (recipe_id, step, description) VALUES ("
						+ r.getRecipeID() + ", " + j + ", '" + prepStep.get(j) + "')");
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
	 * Adds a unit to database.
	 *
	 * @param unit
	 *            the singular form of the unit
	 */
	public void addUnit(String unit) {
		try {
			Connection con = access();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO unit (singular) VALUES('" + unit + "');");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void editRecipe(Recipe r) {
		try {
			int i = 0;
			int j = 0;
			Connection con = access();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE recipe SET name = '" + r.getRecipeName() + "', cuisine = '" + r.getCuisine()
					+ "', servings = " + r.getServings() + ", preparationTime = " + r.getPreparationTime()
					+ ", cookingTime = " + r.getCookingTime() + ", category = " + r.getCategoryID() + ", author = '" + r.getAuthor() + "' where recipe_id = "
					+ r.getRecipeID() + ";");
			deleteIngredientList(r);
			ArrayList<Ingredient> ingList = new ArrayList<Ingredient>();
			ingList.addAll(r.getIngredientList());
			for (i = 0; i < ingList.size(); i++) {
				stmt.executeUpdate("INSERT INTO ingredient (recipe_id, name, quantity, unit, description) VALUES ("
						+ r.getRecipeID() + ", '" + ingList.get(i).getIngredientName() + "', "
						+ ingList.get(i).getIngredientQuantity() + ", '" + ingList.get(i).getIngredientUnit() + "', '"
						+ ingList.get(i).getIngredientDescription() + "')");
			}
			deletePrepStepList(r);
			ArrayList<String> prepStep = new ArrayList<String>();
			prepStep.addAll(r.getPreparationStepList());
			for (j = 0; j < prepStep.size(); j++) {
				stmt.executeUpdate("INSERT INTO preparation_step (recipe_id, step, description) VALUES ("
						+ r.getRecipeID() + ", " + j + ", '" + prepStep.get(j) + "')");
			}

			Statement stmt3 = con.createStatement();
			stmt3.executeUpdate("DELETE from preparation_step where recipe_id = " + r.getRecipeID() + ";");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a recipe from the database.
	 * 
	 * @param r
	 *            the recipe that is going to be deleted
	 */
	public void deleteRecipe(Recipe r) {
		try {
			Connection con = access();
			Statement stmt1 = con.createStatement();
			stmt1.executeUpdate("DELETE from recipe where recipe_id = " + r.getRecipeID() + ";");
			Statement stmt2 = con.createStatement();
			stmt2.executeUpdate("DELETE from ingredient where recipe_id = " + r.getRecipeID() + ";");
			Statement stmt3 = con.createStatement();
			stmt3.executeUpdate("DELETE from preparation_step where recipe_id = " + r.getRecipeID() + ";");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete the ingredients of a recipe from the database.
	 * 
	 * @param r
	 *            the Recipe in which the ingredients will be deleted
	 */
	public void deleteIngredientList(Recipe r) {
		try {
			Connection con = access();
			Statement stmt1 = con.createStatement();
			stmt1.executeUpdate("DELETE from ingredient where recipe_id = " + r.getRecipeID() + ";");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete the preparation step list from the database.
	 * 
	 * @param r
	 *            the Recipe in which the preparation steps will be deleted
	 */
	public void deletePrepStepList(Recipe r) {
		try {
			Connection con = access();
			Statement stmt1 = con.createStatement();
			stmt1.executeUpdate("DELETE from preparation_step where recipe_id = " + r.getRecipeID() + ";");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isRnameUQ(Recipe r) {
		try {
			Connection con = access();
			Statement stmt1 = con.createStatement();
			ResultSet rset1 = stmt1.executeQuery("Select name from recipe where name = '" + r.getRecipeName() + "';");
			con.close();
			return rset1.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}

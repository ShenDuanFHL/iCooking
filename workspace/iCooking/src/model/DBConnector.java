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
	public Connection access() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cookbook", "root", "");
		return con;
	}

	
	
	/**
	 * Retrieves all recipe results from the Database.
	 * 
	 */
	public void retrieveResults() {
		try {
			Connection con = access();
			Statement stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM recipe;");
			while (rset.next()) {
				String recipeName = rset.getString("name");
				int servingCount = rset.getInt("servings");
				int prepTime = rset.getInt("preparationTime");
				int cookingTime = rset.getInt("cookingTime");
				System.out.println("recipe name: " + recipeName + ", servings: " + servingCount + ", preparation time: "
						+ prepTime + ", cooking time: " + cookingTime);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * Updates the name and servings.
	 * 
	 */
	public void update() {
		try {
			Connection con = access();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO recipe (name, servings) VALUES('Xiao Longbao',4)");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * Adds a recipe to database. Adds recipe information to recipe table;
	 * preparation step list to preparation_step table; ingredient list to
	 * ingredient table. Recipe ID is assigned automatically by the database.
	 * 
	 * @param r
	 *            the recipe
	 */
	public void insert(Recipe r) {
		try {
			Connection con = access();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO recipe (name, servings, preparationTime, cookingTime) VALUES('"
					+ r.getRecipeName() + "', " + r.getServings() + ", " + r.getCookingTime() + ", "
					+ r.getPreparationTime() + ")");
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
						+ ingList.get(i).getIngredientAmount() + ", '" + ingList.get(i).getIngredientUnit() + "', '"
						+ ingList.get(i).getIngredientDescription() + "')");
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

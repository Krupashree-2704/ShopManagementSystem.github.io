package com.qsp.shop.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.postgresql.Driver;

import com.qsp.shop.model.Product;

public class ShopController {
	static Product product = new Product();
	public int addRecords(int id, String name, int price, int quantity,boolean availability) {
		Connection connection=null;
		int rowAffected=0;
		try {
			
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop",properties);
			String query="insert into product values(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,id);
			preparedStatement.setString(2, name);
			preparedStatement.setInt(3,price);
			preparedStatement.setInt(4,quantity);
			preparedStatement.setBoolean(5, availability);
			rowAffected = preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return rowAffected;
	}
	
	
	public void addMultipleProducts(ArrayList<Product> products)
	{
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop",properties );
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product VALUES(?,?,?,?,?)");
			for (Product product : products) {
				preparedStatement.setInt(1,product.getP_id());
				preparedStatement.setString(2,product.getP_name());
				preparedStatement.setInt(3,product.getP_price());
				preparedStatement.setInt(4,product.getP_quantity());
				preparedStatement.setBoolean(5, product.isP_availability());
				preparedStatement.addBatch();
				 }
				preparedStatement.executeBatch();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			if (connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	public ResultSet fetchProduct(int id) {
		Connection connection=null;
		ResultSet resultSet=null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop",properties );
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM product WHERE p_id=?");
			prepareStatement.setInt(1, id);//try with execute()
			resultSet = prepareStatement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultSet;
		
	}
	public int removeProduct(int id) {
		Connection connection=null;
		int idDeleted=0;//local to removeProduct()
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop",properties );
			PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM product WHERE p_id=?");
			prepareStatement.setInt(1, id);//try with execute()
			idDeleted = prepareStatement.executeUpdate();
			
			
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return idDeleted;
	}
	public int updateProductName(int id,String name) {
		Connection connection=null;
		int rowAffectedbyUpdate=0;
		try {
			
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop",properties);
			String query="update product set p_name=? where p_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);//Here set values according to paramater given to delimiter 
			preparedStatement.setString(1,name);
			preparedStatement.setInt(2, id);
			//1,2 accd to delimeter give position
			rowAffectedbyUpdate = preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return rowAffectedbyUpdate;
	}
	public int updateProductQuantity(int id,int quantity) {
		Connection connection=null;
		int rowAffectedbyUpdateQuantity=0;
		try {
			
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop",properties);
			String query="update product set p_quantity=? where p_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);//Here set values according to paramater given to delimiter 
			preparedStatement.setInt(1,quantity);
//			if (quantity>0) {
//				product.availability=true;
//			} 
			preparedStatement.setInt(2, id);
			//1,2 accd to delimeter give position
			rowAffectedbyUpdateQuantity = preparedStatement.executeUpdate();
			if (quantity>0) {
				product.setP_availability(true);			} 
			else
			{
				product.setP_availability(false);	
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return rowAffectedbyUpdateQuantity;
	}
	public int updateProductPrice(int id,int price) {
		Connection connection=null;
		int rowAffectedbyUpdatePrice=0;
		try {
			
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop",properties);
			String query="update product set p_price=? where p_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);//Here set values according to paramater given to delimiter 
			preparedStatement.setInt(1,price);
			preparedStatement.setInt(2, id);
			//1,2 accd to delimeter give position
			rowAffectedbyUpdatePrice = preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return rowAffectedbyUpdatePrice;
	}
}



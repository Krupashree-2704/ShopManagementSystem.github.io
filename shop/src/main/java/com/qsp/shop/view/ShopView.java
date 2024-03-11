package com.qsp.shop.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import com.qsp.shop.controller.ShopController;
import com.qsp.shop.model.Product;

public class ShopView {
	static Scanner myInput=new Scanner(System.in);
	static Product product = new Product();
	static ShopController shopController = new ShopController();

	public static void main(String[] args) {
		//Driver Class:View Class
		do {
			System.out.println("Select Operation to perform:");
			System.out.println("1.Add Product \n2.Remove Product \n3.Update Products Details \n4.Fetch Product\n0.Exit");
			System.out.print("Enter digit respective to desired option:");
			int userInput=myInput.nextInt();
			myInput.nextLine();
			switch (userInput) {
			case 0:
				myInput.close();
				System.out.println("- - - -Exited- - - -");
				System.exit(0);
				break;
			case 1:
				System.out.println("How many products you want to add ?\n1.Single Product\n2.Multiple Products: ");
				int productsCount=myInput.nextInt();
				myInput.nextLine();
				if (productsCount==1) {
					System.out.println("Enter Product ID:");
					int i_p_id=myInput.nextInt();
					myInput.nextLine();
					System.out.println("Enter Product Name:");
					String i_p_name=myInput.nextLine();
					System.out.println("Enter Product Price:");
					int i_p_price=myInput.nextInt();
					myInput.nextLine();
					System.out.println("Enter Product Quantity:");
					int i_p_quantity=myInput.nextInt();
					myInput.nextLine();
					boolean i_p_availability=false;
					//availability is dependent on quantity
					if (i_p_quantity>0) {
						i_p_availability=true;
					} 					
					if((shopController.addRecords(i_p_id,i_p_name,i_p_price,i_p_quantity,i_p_availability))!=0)		
					{
						System.out.println("Product added Successfully");
					}
					else
					{
						System.out.println("Product not added Successfully");
					}
				} else {
					boolean toContinue=true;
					ArrayList <Product>products=new ArrayList<Product>();//to temporary store all the products and their details
					do{
						Product product=new Product();
						System.out.println("Enter Product ID:");
						product.setP_id(myInput.nextInt());//setters:in Product class we have attributes:private
															//to set them:setters are used
						myInput.nextLine();
						System.out.println("Enter Product Name:");
						product.setP_name(myInput.nextLine());
						System.out.println("Enter Product Price:");
						product.setP_price(myInput.nextInt());
						myInput.nextLine();
						System.out.println("Enter Product Quantity:");
						int quantity=myInput.nextInt();
						product.setP_quantity(quantity);
						myInput.nextLine();
						boolean i_p_availability=false;
						if (quantity>0) {
							i_p_availability=true;
						} 	
						product.setP_availability(i_p_availability);
						products.add(product);
						System.out.println("Enter 1 to continue adding product enter 0 to stop adding product");
						int toAdd=myInput.nextInt();
						myInput.nextLine();
						if (toAdd==0) {
							toContinue = false;
						}
						
					}while(toContinue);
					shopController.addMultipleProducts(products);
				}
				
				break;
			case 2:
				System.out.print("Enter Product id to remove: ");
				int productidtoremove = myInput.nextInt();
				myInput.nextLine();
				
				if (shopController.removeProduct(productidtoremove)>0) {
					System.out.println("Product with ID "+productidtoremove+" Exists and Removed Successfully");
				} else {
					System.out.println("Product with ID "+productidtoremove+" Does not Exists No remove operation performed");
				}
				System.out.println();

				break;
			case 3:
				System.out.print("Enter Product ID to update:");
				int productIdToUpdate = myInput.nextInt();
				myInput.nextLine();
				ResultSet productUp = shopController.fetchProduct(productIdToUpdate);
				//ResultSet has next(),if record exist ,next poitn to record fetched from d/b
				try {
					if (productUp.next()) {
						System.out.println("What you want to update: ");
						System.out.println("1.Name\n2.Price\n3.Quantity");
						System.out.println("Enter number respective to desired option: ");
						byte updateOption = myInput.nextByte();
						myInput.nextLine();
						switch (updateOption) {
						case 1:
							System.out.println("Enter Name to update:");
							String nameToUpdate = myInput.nextLine();
							if (shopController.updateProductName(productIdToUpdate, nameToUpdate)>0) {
								System.out.println("Product with ID "+productIdToUpdate+" Exists and Name Updated Successfully");
							} else {
								System.out.println("Product with ID "+productIdToUpdate+" Does not Exists No Update operation performed");
							}
							
							
							break;
						case 2:
							System.out.println("Enter Price to change:");
							int priceToUpdate = myInput.nextInt();
							myInput.nextLine();
							if (shopController.updateProductPrice(productIdToUpdate, priceToUpdate)>0) {
								System.out.println("Product with ID "+productIdToUpdate+" Exists and Price Updated Successfully");
							} else {
								System.out.println("Product with ID "+productIdToUpdate+" Does not Exists No Update operation performed");
							}
							
							break;
						case 3:
							System.out.println("Enter Quantity to change:");
							int quantityToUpdate = myInput.nextInt();
							myInput.nextLine();
							if (shopController.updateProductQuantity(productIdToUpdate, quantityToUpdate)>0) {
								System.out.println("Product with ID "+productIdToUpdate+" Exists and Quantity Updated Successfully");
							} else {
								System.out.println("Product with ID "+productIdToUpdate+" Does not Exists No Update operation performed");
							}
							break;

						default:
							System.out.println("Invalid Selection!!!");
							break;
						}
					} else {
						System.out.println("Product with given ID does not exist,Update Operation cannot be performed");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;
			case 4:
				//fetching product by id
				//here we are going in resultset object and printing all the values
				System.out.print("enter product id to fetch :");
				int productidtofind=myInput.nextInt();
				myInput.nextLine();
				ResultSet fetchProduct = shopController.fetchProduct(productidtofind);
//				System.out.println(fetchProduct);//if true,false resultSet tayar hoga,address rahega
				//to point to record:.next()
				//if else use karto=>ekach record aahe mhanun
				boolean next;
				try {
					next = fetchProduct.next();
					if (next) {
						System.out.println("Product Details");
						System.out.println("ID:"+fetchProduct.getInt(1));
						System.out.println("Name:"+fetchProduct.getString(2));
						System.out.println("Price:"+fetchProduct.getInt(3));
						System.out.println("Quantity:"+fetchProduct.getInt(4));
						if (fetchProduct.getBoolean(5)) {
							System.out.println("availablitity: Available");
						} else {
							System.out.println("availablitity: Not Available");
						}
					} else {

					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				break;
			

			default:
				System.out.println("- - - -Invalid Selection- - - -");
				break;
			}
			
		} while (true);

	}

	

}

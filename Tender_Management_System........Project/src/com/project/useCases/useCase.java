package com.project.useCases;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.project.bean.Tender;
import com.project.bean.Vendor;
import com.project.dao.AdminDao;
import com.project.dao.AdminDaoImp;
import com.project.exceptions.VendorException;

public class useCase {

	public static void main(String[] args) {
		System.out.println("Enter 1 for login as Administrator");
		System.out.println("Enter 2 for login as Vendor");
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		AdminDao vdao = new AdminDaoImp();
		if(x==1) {
			System.out.println("Please enter username");
			String u = sc.next();
			System.out.println("Please enter your password");
			String p = sc.next();
			boolean flag=true;
			boolean b;
			try {
				b = vdao.loginAdmin(u, p);
				if(b) {
					System.out.println("Login successfully!!...");
				}
			} catch (VendorException e1) {
				System.out.println(e1.getMessage());
				flag=false;
			}
			while(flag) {
				try {
					
					if(vdao.loginAdmin(u, p)) {
						System.out.println("=================================");
						System.out.println("Enter 1 for register new Vendor");
						System.out.println("Enter 2 for view all Vendors");
						System.out.println("Enter 3 for create new Tender");
						System.out.println("Enter 4 for view all tenders");
						System.out.println("Enter 5 for view all bids of a tender");
						System.out.println("Enter 6 to assign a tender");
						System.out.println("Enter 7 to Exit..");
						int choice = sc.nextInt();
						switch(choice) {
						case 1 :
							System.out.println("Enter Vendor Name:");
							String vname=sc.next();
							
							System.out.println("Enter Email:");
							String email=sc.next();
							
							System.out.println("Enter Password:");
							String pass=sc.next();
							
							System.out.println("Enter Your Company:");
							String comp=sc.next();
							
							System.out.println("Enter Address:");
							String add=sc.next();
							Vendor vendor = new Vendor();
							vendor.setVname(vname);
							vendor.setEmail(email);
							vendor.setPassword(pass);
							vendor.setCompany(comp);
							vendor.setAddress(add);
							AdminDao tdao = new AdminDaoImp();
							String result = tdao.registerVendor(vendor);
							System.out.println(result);
							System.out.println("===========================");
							break;
						case 2:
							AdminDao vd = new AdminDaoImp();
							try {
								List<Vendor> vendors = vd.viewAllVendor();
								vendors.forEach(v->{
									System.out.println("Vendor name : "+ v.getVname());
									System.out.println("Vendor email : "+v.getEmail());
									System.out.println("Vendor company : "+v.getCompany());
									System.out.println("vendor address : "+v.getAddress());
									System.out.println("===============================");
								});
							} catch (VendorException v) {
								System.out.println(v.getMessage());
							}
							break;
						case 3:
							System.out.println(" Enter Tender id");
							int tid = sc.nextInt();
							System.out.println("Enter falt Type in BHK");
							int fType = sc.nextInt();
							System.out.println("Enter tender location");;
							String l = sc.next();
							System.out.println("Enter tender start date in yyyy-mm-dd");
							String sdate = sc.next();
							System.out.println("Enter tender end date in yyyy-mm-dd");
							String edate = sc.next();
							System.out.println("Enter base price");
							double price = sc.nextDouble();
							Tender tender = new Tender(tid, fType, l, sdate, edate, price);
							AdminDao ad = new AdminDaoImp();
							String tresult = ad.createTender(tender);
							System.out.println(tresult);
							System.out.println("===========================================");
							break;
						case 4:
							AdminDao adao = new AdminDaoImp();
							try {
								List<Tender> tenders = adao.viewTender();
								tenders.forEach(t ->{
	                          System.out.println("Tender id is : "+t.getTid());
	                          System.out.println("Flat type is : "+ t.getFlatType()+" BHK");
	                          System.out.println("Tender location : "+t.getLocation());
	                          System.out.println("Tender start date : "+t.getStartDate());
	                          System.out.println("Tenser end date : "+t.getEndDate());
	                          System.out.println("Tender base price : "+t.getBasePrice());
	                          System.out.println("======================================================");	
								});
							} catch (VendorException e) {
								System.out.println(e.getMessage());
							}
							break;
						case 5:
						    AdminDao a = new AdminDaoImp();
							System.out.println("Enter tender id to see all bids");
							int t= sc.nextInt();
							a.viewBid(t);
							System.out.println("==========================================");
							break;
						case 6:
							AdminDao ab = new AdminDaoImp();
							System.out.println("Enter tender id ..");
							int ti = sc.nextInt();
							String res = ab.assignTender(ti);
							System.out.println(res);
							break;
						case 7:
							flag=false;
							System.out.println("Thankyou!!....");
							break;
						}
					}
				
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}	
			}
			
		}else if(x==2) {
			System.out.println("Please enter your email..");
			String email = sc.next();
			System.out.println("Enter your password");
			String pass = sc.next();
			boolean flag=true;
			boolean b;
			try {
				b = vdao.loginVendor(email, pass);
				if(b) {
					System.out.println("Login successfully!!...");
				}
			} catch (VendorException e1) {
				System.out.println(e1.getMessage());
				flag=false;
			}
			while(flag) {
				try {
					if(vdao.loginVendor(email, pass)) {
						System.out.println();
						System.out.println("Enter 1 for view all current tenders");
						System.out.println("Enter 2 for place a bid against a tender");
						System.out.println("Enter 3 for view status of bid");
						System.out.println("Enter 4 for view own bid history");
						System.out.println("Enter 5 for Exit..");
						int choice = sc.nextInt();
						switch(choice) {
						case 1:
							AdminDao adao = new AdminDaoImp();
							try {
								List<Tender> tenders = adao.viewTender();
								tenders.forEach(t ->{
	                          System.out.println("Tender id is : "+t.getTid());
	                          System.out.println("Flat type is : "+ t.getFlatType()+" BHK");
	                          System.out.println("Tender location : "+t.getLocation());
	                          System.out.println("Tender start date : "+t.getStartDate());
	                          System.out.println("Tenser end date : "+t.getEndDate());
	                          System.out.println("Tender base price : "+t.getBasePrice());
	                           System.out.println("======================================================");
								});
							} catch (VendorException e) {
								System.out.println(e.getMessage());
							}
							break;
						case 2:
							System.out.println("Welcome to place a bid...");
							System.out.println("Enter tender Id");
							int tid = sc.nextInt();
							System.out.println("Enter Vendor Id");
							int vid = sc.nextInt();
							System.out.println("Enter your bid offer");
							double offer = sc.nextDouble();
							AdminDao dao = new AdminDaoImp();
			                 try {
							String result=dao.placeBid(tid, vid, offer);
							System.out.println(result);
							} catch (Exception e) {
							System.out.println(e.getMessage());
							}
			                 break;
						case 3:
							AdminDao ab = new AdminDaoImp();
							System.out.println("Enter tender id ..");
							int ti = sc.nextInt();
							String res = ab.assignTender(ti);
							System.out.println(res);
							break;
						case 4:
							System.out.println("Enter your Vender id ");
							AdminDao abc = new AdminDaoImp();
							int vi = sc.nextInt();
							abc.viewBidHistory(vi);
							break;
						case 5:
							System.out.println("Thankyou!!...");
							flag = false;
							return;
						}
				}
					} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
				
		}else {
			System.out.println("Please enter valid number");
		}

	}

}

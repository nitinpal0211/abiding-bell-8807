package com.project.useCases;

import java.util.List;

import com.project.bean.Vendor;
import com.project.dao.AdminDao;
import com.project.dao.AdminDaoImp;
import com.project.exceptions.VendorException;

public class viewAllVendorUseCase {

	public static void main(String[] args) {
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

	}

}

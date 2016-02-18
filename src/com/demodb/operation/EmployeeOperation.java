package com.demodb.operation;

import java.util.ArrayList;

import com.demodb.dbhelper.DatabaseHandler;
import com.demodb.utils.EmpolyeeDetails;

import android.content.Context;
import android.util.Log;

public class EmployeeOperation {
	private Context mCtx;
	private DatabaseHandler mDatabaseHandler;

	public EmployeeOperation(Context ctx) {
		mCtx = ctx;
		Log.i(EmployeeOperation.class.toString(),
				"EmployeeOperation Call DatabaseHandler");
		mDatabaseHandler = new DatabaseHandler(mCtx);
	}

	public String insertEmpData(EmpolyeeDetails empDetails) {
		return mDatabaseHandler.inserEmployeeData(empDetails);
	}

	public ArrayList<EmpolyeeDetails> getEmployeeData(String empId) {
		return mDatabaseHandler.selecedtEmpData(empId);
	}

	public String updateMobileNo(String empId, String empMobNo) {
		return mDatabaseHandler.upDateMobNo(empId, empMobNo);
	}

	public String deleteAllEmpData() {
		try {
			return mDatabaseHandler.deleteAllEmployee();
		} catch (Exception e) {
			return "error";
		}
	}

	public String deleteEmployee(String empId) {
		try {
			return mDatabaseHandler.deleteSpecificEmpId(empId);
		} catch (Exception e) {
			return "error";
		}
	}

}

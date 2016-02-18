package com.demodb.dbhelper;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.demodb.utils.EmpolyeeDetails;

public class DatabaseHandler extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "DemoDb.db";
	public static final String TABLE_NAME_EMPLOYEE = "employee";
	public static final String KEY_ID = "id";
	public static final String EMP_ID = "empId";
	public static final String EMP_NAME = "empName";
	public static final String EMP_MOBNO = "empMobileNo";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_NAME_EMPLOYEE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
				+ EMP_ID + " TEXT," + EMP_NAME + " TEXT," + EMP_MOBNO + " TEXT"
				+ ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
		Log.i(DatabaseHandler.class.toString(), "DatabaseHandler Create Table");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS employee");
		this.onCreate(db);
	}

	public String inserEmployeeData(EmpolyeeDetails employeeDetails) {
		String response = null;
		try {
			Log.e(DatabaseHandler.class.toString(),
					"DatabaseHandler inserEmployeeData is"
							+ employeeDetails.getEmpId());
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues insertEmpData = new ContentValues();
			insertEmpData.put(EMP_ID, employeeDetails.getEmpId());
			insertEmpData.put(EMP_NAME, employeeDetails.getEmpName());
			insertEmpData.put(EMP_MOBNO, employeeDetails.getEmpMob());
			response = String.valueOf(db.insert(TABLE_NAME_EMPLOYEE, null,
					insertEmpData));

		} catch (Exception e) {
			Log.e(DatabaseHandler.class.toString(),
					"DatabaseHandler Exception is" + e.getMessage());
		}
		if (response != null && response.equalsIgnoreCase("-1")) {
			return "error";
		} else {
			return "success";
		}

	}

	public ArrayList<EmpolyeeDetails> selecedtEmpData(String empId) {
		try {
			ArrayList<EmpolyeeDetails> list = new ArrayList<EmpolyeeDetails>();
			EmpolyeeDetails empolyeeDetails = new EmpolyeeDetails();
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME_EMPLOYEE
					+ " WHERE " + EMP_ID + "= ?", new String[] { empId });

			if (c.moveToFirst()) {
				do {
					empolyeeDetails.setEmpId(c.getString(c
							.getColumnIndex(EMP_ID)));
					empolyeeDetails.setEmpName(c.getString(c
							.getColumnIndex(EMP_NAME)));
					empolyeeDetails.setEmpMob(c.getString(c
							.getColumnIndex(EMP_MOBNO)));
					list.add(empolyeeDetails);
					// Do something Here with values
				} while (c.moveToNext());
			}
			c.close();
			db.close();
			return list;
		} catch (Exception e) {
			Log.e(DatabaseHandler.class.toString(),
					"DatabaseHandler selecedtEmpData Exp is" + e.getMessage());
		}
		return null;

	}

	public String upDateMobNo(String empId, String empMobNo) {
		String response = null;
		try {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(EMP_MOBNO, empMobNo);
			response = String.valueOf(db.update(TABLE_NAME_EMPLOYEE, values,
					EMP_ID + " = ?", new String[] { empId }));
			Log.i(DatabaseHandler.class.toString(),
					"DatabaseHandler Number of Affected Row" + response);

		} catch (Exception e) {
			Log.e(DatabaseHandler.class.toString(),
					"DatabaseHandler upDateMobNo Excep is" + e.getMessage());
		}
		return response;

	}

	public String deleteAllEmployee() {

		try {
			SQLiteDatabase db = this.getWritableDatabase();
			db.execSQL("delete from " + TABLE_NAME_EMPLOYEE);
			return "success";
		} catch (Exception e) {
			Log.e(DatabaseHandler.class.toString(),
					"DatabaseHandler Delete Exception" + e.getMessage());
			return "error";
		}
	}

	public String deleteSpecificEmpId(String empId) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			return String.valueOf(db.delete(TABLE_NAME_EMPLOYEE, EMP_ID + "=?",
					new String[] { empId }));
		} catch (Exception e) {
			Log.e(DatabaseHandler.class.toString(),
					"DatabaseHandler Delete Exception" + e.getMessage());
			return "error";
		}
	}

}

package com.demodb.utils;

public class EmpolyeeDetails {

	private String mStrEmpName, mStrEmpId, mStrEmpMob;

	public EmpolyeeDetails() {
	}

	public EmpolyeeDetails(String mStrEmpId,String mStrEmpName,
			String mStrEmpMob) {
		this.mStrEmpName = mStrEmpName;
		this.mStrEmpId = mStrEmpId;
		this.mStrEmpMob = mStrEmpMob;
	}

	public String getEmpName() {
		return mStrEmpName;
	}

	public void setEmpName(String mStrEmpName) {
		this.mStrEmpName = mStrEmpName;
	}

	public String getEmpId() {
		return mStrEmpId;
	}

	public void setEmpId(String mStrEmpId) {
		this.mStrEmpId = mStrEmpId;
	}

	public String getEmpMob() {
		return mStrEmpMob;
	}

	public void setEmpMob(String mStrEmpMob) {
		this.mStrEmpMob = mStrEmpMob;
	}

}

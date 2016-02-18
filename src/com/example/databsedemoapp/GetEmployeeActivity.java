package com.example.databsedemoapp;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demodb.operation.EmployeeOperation;
import com.demodb.utils.EmpolyeeDetails;

public class GetEmployeeActivity extends AppCompatActivity implements
		OnClickListener {
	private RelativeLayout mRrEmpDetails;
	private TextView mTxtEmpId, mTxtEmpName, mTxtEmpMob, mTxtTitle;
	private Button mBtnget;
	private EditText mEdtGetEmpId;
	private EmployeeOperation mEmpOperation;
	private ArrayList<EmpolyeeDetails> empolyeeDetailsLst;
	private Toolbar mToolBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_employee);
		initViews();
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi") private void initViews() {
		mToolBar = (Toolbar) findViewById(R.id.tool_bar);
		mTxtTitle = (TextView) findViewById(R.id.txt_title);
		if (mToolBar != null) {
			setSupportActionBar(mToolBar);
			mTxtTitle.setText("Get Selected Employee Data");
			mToolBar.setTitleTextColor(0xFFFFFFFF);
			getSupportActionBar().setHomeButtonEnabled(true);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		mEmpOperation = new EmployeeOperation(this);
		mRrEmpDetails = (RelativeLayout) findViewById(R.id.rr_emp_details);
		mEdtGetEmpId = (EditText) findViewById(R.id.edt_get_empId);
		mRrEmpDetails.setVisibility(View.GONE);
		mTxtEmpId = (TextView) findViewById(R.id.txt_empId);
		mTxtEmpName = (TextView) findViewById(R.id.txt_empName);
		mTxtEmpMob = (TextView) findViewById(R.id.txt_empMob);
		mBtnget = (Button) findViewById(R.id.btn_get);
		mBtnget.setOnClickListener(this);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			final Drawable upArrow = getResources().getDrawable(
					R.drawable.abc_ic_ab_back_mtrl_am_alpha,
					getApplicationContext().getTheme());
			upArrow.setColorFilter(getResources().getColor(R.color.white),
					PorterDuff.Mode.SRC_ATOP);
			getSupportActionBar().setHomeAsUpIndicator(upArrow);
		} else {
			final Drawable upArrow = getResources().getDrawable(
					R.drawable.abc_ic_ab_back_mtrl_am_alpha);
			upArrow.setColorFilter(getResources().getColor(R.color.white),
					PorterDuff.Mode.SRC_ATOP);
			getSupportActionBar().setHomeAsUpIndicator(upArrow);
		}

		mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_get:
			if (mEdtGetEmpId != null && mEdtGetEmpId.length() > 0) {
				empolyeeDetailsLst = mEmpOperation.getEmployeeData(mEdtGetEmpId
						.getText().toString());
				Log.i(GetEmployeeActivity.class.toString(),
						"GetEmployeeActivity Arraylist size"
								+ empolyeeDetailsLst.size());
				if (empolyeeDetailsLst != null && empolyeeDetailsLst.size() > 0) {
					if (empolyeeDetailsLst != null
							&& empolyeeDetailsLst.size() > 0) {
						for (int i = 0; i < empolyeeDetailsLst.size(); i++) {
							mRrEmpDetails.setVisibility(View.VISIBLE);
							mTxtEmpId.setText("Employee Id: "
									+ empolyeeDetailsLst.get(i).getEmpId());
							mTxtEmpName.setText("Employee Name: "
									+ empolyeeDetailsLst.get(i).getEmpName());
							mTxtEmpMob.setText("Employee MobNo: "
									+ empolyeeDetailsLst.get(i).getEmpMob());
						}
					}

				} else {
					Toast.makeText(getApplicationContext(),
							"No Data Found For That Id!!!", Toast.LENGTH_SHORT)
							.show();
				}
			}
			break;

		default:
			break;
		}
	}

}

package com.example.team8.urlms;

import android.database.Cursor;
import android.os.Looper;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by ericvuong on 2017-10-17.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testViewStaffList(){
        //tests refresh button====================
//        Looper.prepare();
//        mActivity.refresh();
//        mActivity.refreshButton.callOnClick();
//        assertEquals(mActivity.toDisplay.getText().toString(), "");



        //tests viewStaffList button==============
        Looper.prepare();

        mActivity.viewStaffMembers();
        mActivity.viewStaffButton.callOnClick();
        //retrieve the expected result from current database
        Cursor cursor = mActivity.myDb.getAllData();
        StringBuffer expected = new StringBuffer();
        while(cursor.moveToNext()){
            expected.append("ID :" + cursor.getString(0)+"\n");
            expected.append("Name :" + cursor.getString(1)+"\n\n");
        }
        //checks if expected matches what actually displays on the screen
        assertEquals(mActivity.toDisplay.getText().toString(), expected.toString());

    }

    @After
    public void tearDown() throws Exception {
    mActivity = null;
    }

}
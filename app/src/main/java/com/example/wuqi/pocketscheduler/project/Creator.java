package com.example.wuqi.pocketscheduler.project;

import java.util.Date;

/**
 * Created by RapHaeL on 2017/5/24.
 */
public class Creator {
    private String mSName;
    private int mSName2;
    private String mLName;
    private String mDate;
    private String mCreator;
    private String mType;

    public Creator(String mShortName, String mName, String mDDate, String mCreator1, String mType1) {
        mSName = mShortName;
        mLName = mName;
        mDate = mDDate;
        mCreator = mCreator1;
        mType = mType1;
    }

    public Creator(String mShortName, String mName, String mDDate) {
        mSName = mShortName;
        mLName = mName;
        mDate = mDDate;
    }

    public Creator(int mShortName, String mName, String mDDate, String mCreator1, String mType1) {
        mSName2 = mShortName;
        mLName = mName;
        mDate = mDDate;
        mCreator = mCreator1;
        mType = mType1;
    }


    public String getmSName() {
        return mSName;
    }
    public int getProjectId(){return mSName2;}
    public String getmLName() {
        return mLName;
    }
    public String getmDate() {
        return mDate;
    }
    public String getmCreator(){return mCreator;}
    public String getmType(){return mType;}
}

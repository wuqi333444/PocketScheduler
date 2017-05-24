package com.example.wuqi.pocketscheduler;

/**
 * Created by RapHaeL on 2017/5/24.
 */
public class Creator {
    private String mSName;
    private String mLName;
    private String mDate;

    public Creator(String mShortName, String mName, String mDDate) {
        mSName = mShortName;
        mLName = mName;
        mDate = mDDate;
    }

    public String getmSName() {
        return mSName;
    }
    public String getmLName() {
        return mLName;
    }
    public String getmDate() {
        return mDate;
    }
}

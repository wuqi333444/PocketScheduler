package com.example.wuqi.pocketscheduler.data;

import android.provider.BaseColumns;

/**
 * Created by RapHaeL on 2017/6/4.
 */

public final class Contract {
    public static abstract class PetsEntry implements BaseColumns {
        public static final String TABLE_NAME = "pets";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_BREED = "breed";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_WEIGHT = "weight";

        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
        public static final int GENDER_UNKNOWN = 0;
    }
}

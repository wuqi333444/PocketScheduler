package com.example.wuqi.pocketscheduler.data;

import android.provider.BaseColumns;

/**
 * Created by RapHaeL on 2017/6/4.
 */

public final class Contract {
    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_USERID = "userid";
    }

    public static abstract class ProjectEntry implements BaseColumns{
        public static final String TABLE_NAME = "project";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PROJECTID = "projectid";
        public static final String COLUMN_CREATOR = "creator";
        public static final String COLUMN_BEGINTIME = "begintime";
        public static final String COLUMN_TITLE = "title";
        public static final String COLLUMN_DESCRIPTION = "description";
    }

    public static abstract class EventEntry implements BaseColumns{
        public static final String TABLE_NAME = "event";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_EVENTID = "eventid";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_STARTTIME = "starttime";
        public static final String COLUMN_PROJECTID = "projectid";
        public static final String COLUMN_ENDTIME = "endtime";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_ISLONG = "islong";
        public static final String COLUMN_EDITPRIORITY = "editpriority";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_ISFINISHED = "isfinished";
        public static final String COLUMN_ISSHARED = "isshared";
        public static final String COLUMN_CREATORID = "creatorid";
        public static final String COLUMN_LOCATION = "location";

        public static final int TYPE_ENTERTAIMENT = 1;
        public static final int TYPE_STUDY = 2;
        public static final int TYPE_JOB = 3;
        public static final int TYPE_FRIEND = 4;
        public static final int TYPE_FAMILY = 5;
        public static final int TYPE_TRIP = 6;

        public static final int EDITPRIORITY_CREATOR = 0;
        public static final int EDITPRIORITY_ALL = 1;
    }

    public static abstract class DraftboxEntry implements BaseColumns{
        public static final String TABLE_NAME = "draftbox";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_EVENTID = "eventid";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_STARTTIME = "starttime";
        public static final String COLUMN_PROJECTID = "projectid";
        public static final String COLUMN_ENDTIME = "endtime";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_ISLONG = "islong";
        public static final String COLUMN_EDITPRIORITY = "editpriority";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_ISFINISHED = "isfinished";
        public static final String COLUMN_ISSHARED = "isshared";
        public static final String COLUMN_CREATORID = "creatorid";
        public static final String COLUMN_LOCATION = "location";

        public static final int TYPE_ENTERTAIMENT = 1;
        public static final int TYPE_STUDY = 2;
        public static final int TYPE_JOB = 3;
        public static final int TYPE_FRIEND = 4;
        public static final int TYPE_FAMILY = 5;
        public static final int TYPE_TRIP = 6;

        public static final int EDITPRIORITY_CREATOR = 0;
        public static final int EDITPRIORITY_ALL = 1;
    }

    public static abstract class MemberEntry implements BaseColumns{
        public static final String TABLE_NAME = "member";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_MEMBERID = "memberid";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ISFRIEND = "isfriend";
    }

    public static abstract class Project_Members_pairEntry implements BaseColumns{
        public static final String TABLE_NAME = "project_members_pair";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PROJECTID = "projectid";
        public static final String COLUMN_MEMBERID = "memberid";
    }

    public static abstract class Event_Candidates_pairEntry implements BaseColumns{
        public static final String TABLE_NAME = "event_candidates_pair";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_EVENTID = "eventid";
        public static final String COLUMN_CANDIDATEID = "candidateid";
    }

    public static abstract class Event_Editable_pairEntry implements BaseColumns{
        public static final String TABLE_NAME = "event_editable_pair";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_EVENTID = "eventid";
        public static final String COLUMN_EDITABLEID = "editableid";
    }
    public static abstract class AccountEntry implements BaseColumns{
        public static final String TABLE_NAME = "account";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
    }
}

package com.example.wuqi.pocketscheduler.calender;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wuqi.pocketscheduler.R;
import com.example.wuqi.pocketscheduler.data.Contract;
import com.example.wuqi.pocketscheduler.data.PocketDBHelper;
import com.example.wuqi.pocketscheduler.event.EventAdapter;
import com.example.wuqi.pocketscheduler.event.Event;
import com.example.wuqi.pocketscheduler.utils.TimeConvertUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static android.R.attr.onClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalendarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        final CalendarView cv = (CalendarView) rootView.findViewById(R.id.calendarView);
        //final SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                PocketDBHelper mDbHelper = new PocketDBHelper(getActivity());
                final SQLiteDatabase db = mDbHelper.getReadableDatabase();
                SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm");
                //Toast.makeText(getContext(),Integer.toString(year) + Integer.toString(month)+Integer.toString(dayOfMonth),Toast.LENGTH_SHORT).show();
                long tmp_start = TimeConvertUtils.dateToLongConverter(year,month,dayOfMonth,0,0);
                long tmp_end = TimeConvertUtils.dateToLongConverter(year,month,dayOfMonth,24,0);
                //Toast.makeText(getContext(),formatter.format(tmp_start),Toast.LENGTH_SHORT).show();
                Cursor cursor = db.query(Contract.EventEntry.TABLE_NAME,null,Contract.EventEntry.COLUMN_STARTTIME + ">=? AND "+ Contract.EventEntry.COLUMN_ENDTIME + "<=?",new String[]{Long.toString(tmp_start),Long.toString(tmp_end)},null,null,null);
//                Cursor cursor = db.query(Contract.EventEntry.TABLE_NAME,null,null,null,null,null,null);
                final ArrayList<Event> eventArrayList = new ArrayList<>();

                try{
                    int idColumnIndex = cursor.getColumnIndex(Contract.EventEntry._ID);
                    int nameColumnIndex = cursor.getColumnIndex(Contract.EventEntry.COLUMN_TITLE);
                    int startColumnIndex = cursor.getColumnIndex(Contract.EventEntry.COLUMN_STARTTIME);
                    int endColumnIndex = cursor.getColumnIndex(Contract.EventEntry.COLUMN_ENDTIME);
                    while(cursor.moveToNext()){
                        int currentId = cursor.getInt(idColumnIndex);
                        String currentTitle = cursor.getString(nameColumnIndex);
                        long start_time = cursor.getLong(startColumnIndex);
                        long end_time = cursor.getLong(endColumnIndex);
                        eventArrayList.add(new Event(currentTitle,formatter.format(start_time),formatter.format(end_time),currentId));
                    }
                } finally {
                    EventAdapter adapter = new EventAdapter(getActivity(), eventArrayList);
                    ListView listView = (ListView) rootView.findViewById(R.id.id_eventlist);
//                    System.out.println(Boolean.toString(listView == null));
                    listView.setAdapter(adapter);
                    cursor.close();
                }
            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

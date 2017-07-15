package com.example.wuqi.pocketscheduler.event;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wuqi.pocketscheduler.R;
import com.example.wuqi.pocketscheduler.data.Contract;
import com.example.wuqi.pocketscheduler.data.PocketDBHelper;
import com.example.wuqi.pocketscheduler.project.Creator;
import com.example.wuqi.pocketscheduler.project.CustomAdapter;
import com.example.wuqi.pocketscheduler.project.Project_part2;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static com.example.wuqi.pocketscheduler.R.id.ra;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventComingSoonFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventComingSoonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventComingSoonFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EventComingSoonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventComingSoonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventComingSoonFragment newInstance(String param1, String param2) {
        EventComingSoonFragment fragment = new EventComingSoonFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_events_comingsoon, container, false);
        PocketDBHelper mDbHelper = new PocketDBHelper(getActivity());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(Contract.ProjectEntry.TABLE_NAME,null,null,null,null,null,null);
        try{
            final ArrayList<Event> eventArrayList = new ArrayList<>();
            int idColumnIndex = cursor.getColumnIndex(Contract.ProjectEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(Contract.ProjectEntry.COLUMN_TITLE);
            while(cursor.moveToNext()){
                final int currentId = cursor.getInt(idColumnIndex);
                String currentTitle = cursor.getString(nameColumnIndex);
                eventArrayList.add(new Event(currentTitle));
                EventAdapter adapter = new EventAdapter(getActivity(), eventArrayList);
                ListView listView = (ListView) rootView.findViewById(R.id.comingsoon_list);
                listView.setAdapter(adapter);
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
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
        try {
            Field childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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

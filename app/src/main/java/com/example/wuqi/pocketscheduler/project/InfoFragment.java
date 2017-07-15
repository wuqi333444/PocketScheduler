package com.example.wuqi.pocketscheduler.project;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wuqi.pocketscheduler.R;
import com.example.wuqi.pocketscheduler.data.Contract;
import com.example.wuqi.pocketscheduler.data.PocketDBHelper;

import java.util.ArrayList;

import static android.content.Intent.getIntent;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.wuqi.pocketscheduler.R.id.ra;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
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
        View ra = inflater.inflate(R.layout.fragment_info, container, false);
        PocketDBHelper mDbHelper = new PocketDBHelper(getActivity());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(Contract.ProjectEntry.TABLE_NAME, null, null, null, null, null, null);
        Intent intent = getActivity().getIntent();
        int cursorId = intent.getIntExtra("cursorId",0);
        try {
            int idColumnIndex = cursor.getColumnIndex(Contract.ProjectEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(Contract.ProjectEntry.COLUMN_TITLE);
            int descriptionColumnIndex = cursor.getColumnIndex(Contract.ProjectEntry.COLUMN_DESCRIPTION);
            int beginColumnIndex = cursor.getColumnIndex(Contract.ProjectEntry.COLUMN_BEGINTIME);
            int creatorColumnIndex = cursor.getColumnIndex(Contract.ProjectEntry.COLUMN_CREATOR);
            int typeColumnIndex = cursor.getColumnIndex(Contract.ProjectEntry.COLUMN_TYPE);
            cursor.moveToPosition(cursorId);
            int currentId = cursor.getInt(idColumnIndex);
            String currentTitle = cursor.getString(nameColumnIndex);
            String currentType = cursor.getString(typeColumnIndex);
            String currentDescription = cursor.getString(descriptionColumnIndex);
            String currentBegin = cursor.getString(beginColumnIndex);
            String currentCreator = cursor.getString(creatorColumnIndex);
            TextView info1 = (TextView) ra.findViewById(R.id.infoCreator);
            TextView info2 = (TextView) ra.findViewById(R.id.infoDescription);
            TextView info3 = (TextView) ra.findViewById(R.id.infoMembers);
            info1.setText(currentCreator);
            info2.setText(currentDescription);
            info3.setText(currentType);
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
        return ra;
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

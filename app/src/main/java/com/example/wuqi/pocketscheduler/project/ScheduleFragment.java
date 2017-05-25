package com.example.wuqi.pocketscheduler.project;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wuqi.pocketscheduler.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScheduleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScheduleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduleFragment newInstance(String param1, String param2) {
        ScheduleFragment fragment = new ScheduleFragment();
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
        View ra = inflater.inflate(R.layout.fragment_schedule, container, false);
        final ArrayList<Creator> word = new ArrayList<>();
        word.add(new Creator("A","Title: Start","Progress: working on assemble computer"));
        word.add(new Creator("B","Title: Rec Center Basketball","Progress: 15%"));
        word.add(new Creator("C","Title: Pocket Scheduler Design","Progress: 20%"));
        word.add(new Creator("D","Title: Fishing Day","Progress: 78%"));
        word.add(new Creator("E","Title: Angel's Birthday","Progress: finished"));
        ListView listView = (ListView) ra.findViewById(R.id.list_schedule);
        final Custom1Adapter simpleAdapter = new Custom1Adapter(getActivity(),word,R.color.colorPrimary);
        listView.setAdapter(simpleAdapter);
        return ra;
    }

    public class Custom1Adapter extends ArrayAdapter {
        private int backgroundColor;
        public Custom1Adapter(Activity context, ArrayList<Creator> creator, int mbackgroundColor) {
            super(context, 0,creator);
            backgroundColor = mbackgroundColor;
        }
        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View listItemView = convertView;
            if(listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.schedulelist_project, parent, false);
            }
            Creator ra1 = (Creator) getItem(position);
            TextView ra2 = (TextView)listItemView.findViewById(R.id.ra1);
            ra2.setText(ra1.getmSName());
            TextView ra3 = (TextView)listItemView.findViewById(R.id.ra6);
            ra3.setText(ra1.getmLName());
            TextView ra4 = (TextView) listItemView.findViewById(R.id.ra3);
            ra4.setText(ra1.getmDate());
            return listItemView;
        }
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

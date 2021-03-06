package com.example.wuqi.pocketscheduler.project;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.wuqi.pocketscheduler.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.TimeZone;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProjectParticipatorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProjectParticipatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectParticipatorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProjectParticipatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProjectParticipatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProjectParticipatorFragment newInstance(String param1, String param2) {
        ProjectParticipatorFragment fragment = new ProjectParticipatorFragment();
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
        View ra = inflater.inflate(R.layout.fragment_project_participator, container, false);
        final ArrayList<Creator> word = new ArrayList<>();
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        format.setTimeZone(TimeZone.getDefault());
        try {
            date = format.parse("5-6-2016 07:18");
        }catch (ParseException e){
            e.printStackTrace();
        }

        word.add(new Creator("PSD","Pocket Scheduler Design",date,"Raphael Ma", "Entertaiment"));

        ListView listView = (ListView) ra.findViewById(R.id.list_participator);
        final CustomAdapter simpleAdapter = new CustomAdapter(getActivity(),word,R.color.colorPrimaryDark);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView adapterView,View view,int position,long l){
                Creator ra1 = (Creator) simpleAdapter.getItem(position);
                Intent info = new Intent(getActivity(),Project_part2.class);
                startActivity(info);
            }
        });
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

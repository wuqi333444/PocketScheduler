package com.example.wuqi.pocketscheduler.event;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wuqi.pocketscheduler.R;
import com.example.wuqi.pocketscheduler.project.ProjectCreatorFragment;
import com.example.wuqi.pocketscheduler.project.ProjectParticipatorFragment;

import java.lang.reflect.Field;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TabLayout tabEvent;
    private EventPreviousFragment mEventPreviousFragment;
    private EventComingSoonFragment mEventComingSoonFragment;
    private EventDraftboxFragment mEventDraftboxFragment;
    private EventPendingboxFragment mEventPendingboxFragment;

    private OnFragmentInteractionListener mListener;

    public EventsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsFragment newInstance(String param1, String param2) {
        EventsFragment fragment = new EventsFragment();
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
        View ra = inflater.inflate(R.layout.fragment_events, container, false);
        setupEventTab(ra);
        setDefaultFragment();
        return ra;
    }

    private void setDefaultFragment(){
        mEventComingSoonFragment = new EventComingSoonFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.event_content, mEventComingSoonFragment);
        transaction.addToBackStack(null).commit();
    }
    private void setupEventTab(View v){
        tabEvent = (TabLayout) v.findViewById(R.id.tab_event);
        tabEvent.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    private void setCurrentTabFragment(int tabPosition){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        switch (tabPosition)
        {
            case 0 :
                if(mEventComingSoonFragment == null){
                    mEventComingSoonFragment = new EventComingSoonFragment();
                }
                transaction.replace(R.id.event_content, mEventComingSoonFragment).addToBackStack(null).commit();
                break;
            case 1 :
                if(mEventPreviousFragment == null){
                    mEventPreviousFragment = new EventPreviousFragment();
                }
                transaction.replace(R.id.event_content, mEventPreviousFragment).addToBackStack(null).commit();
                break;
            case 2:
                if(mEventDraftboxFragment == null){
                    mEventDraftboxFragment = new EventDraftboxFragment();
                }
                transaction.replace(R.id.event_content, mEventDraftboxFragment).addToBackStack(null).commit();
                break;
            case 3:
                if(mEventPendingboxFragment == null){
                    mEventPendingboxFragment = new EventPendingboxFragment();
                }
                transaction.replace(R.id.event_content, mEventPendingboxFragment).addToBackStack(null).commit();
                break;
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

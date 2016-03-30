package com.studybranch.NestedFragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.studybranch.Adapters.AssignmentsExpandableListAdapter;
import com.studybranch.Objects.ClassItem;
import com.studybranch.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClassExpandableListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClassExpandableListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassExpandableListFragment extends Fragment {

    AssignmentsExpandableListAdapter adapter;







    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AssignmentsNestedListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClassExpandableListFragment newInstance() {
        ClassExpandableListFragment fragment = new ClassExpandableListFragment();
        return fragment;
    }

    public ClassExpandableListFragment() {
        // Required empty public constructor
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_class_expandable_list, container, false);
        ExpandableListView expandableList = (ExpandableListView) v.findViewById(R.id.class_expandable_list);
        List<ClassItem> savedClasses = ClassItem.listAll(ClassItem.class);
        adapter = new AssignmentsExpandableListAdapter(getActivity(),savedClasses);
        expandableList.setAdapter(adapter);
            return v;
    }

    public void refresh() {
        List<ClassItem> savedClasses = ClassItem.listAll(ClassItem.class);
        adapter = new AssignmentsExpandableListAdapter(getActivity(),savedClasses);
        adapter.notifyDataSetChanged();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}

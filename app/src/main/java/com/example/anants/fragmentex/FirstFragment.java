package com.example.anants.fragmentex;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Double bmi;
    TextView bmi_value;
    TextView bmi_result;

    private OnFragmentInteractionListener mListener;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
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
        View v=inflater.inflate(R.layout.fragment_first, container, false);
        final EditText height_feet= (EditText) v.findViewById(R.id.height_in_feet);
        final EditText height_inc = (EditText) v.findViewById(R.id.height_in_inc);
        final EditText weight=(EditText) v.findViewById(R.id.weight);
        Button cal = (Button) v.findViewById(R.id.calculate);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double feet= Double.parseDouble(height_feet.getText().toString());
                Double inc = Double.parseDouble(height_inc.getText().toString());
                Double wt = Double.parseDouble(weight.getText().toString());
                Double ht= feet*0.3048+inc*0.0254;
                bmi = (double) Math.round( wt/(ht*ht));
                bmi_value=(TextView) getActivity().findViewById(R.id.result);
                bmi_result=(TextView) getActivity().findViewById(R.id.type);
                bmi_value.setText(bmi.toString());
               /*
               The resource as reference
               http://www.calculator.net/bmi-calculator.html?ctype=metric&cage=25&csex=m&cheightfeet=5&cheightinch=5&cpound=160&cheightmeter=165&ckg=55&printit=0&x=90&y=12
               */
                if (bmi<16){
                    bmi_result.setText(getResources().getString(R.string.severe_thinness));
                }
                else if(bmi>=16 && bmi<17){
                    bmi_result.setText(getResources().getString(R.string.moderate_thinness));
                }
                else if(bmi>=17 && bmi<18.5){
                    bmi_result.setText(getResources().getString(R.string.mild_thinness));
                }
                else if(bmi>=18.5 && bmi<30){
                    bmi_result.setText(getResources().getString(R.string.normal));
                }
                else if(bmi>=25 && bmi<17){
                    bmi_result.setText(getResources().getString(R.string.overweight));
                }
                else if(bmi>=30 && bmi<35){
                    bmi_result.setText(getResources().getString(R.string.obese_class_1));
                }
                else if(bmi>=35 && bmi<40){
                    bmi_result.setText(getResources().getString(R.string.obese_class_2));
                }
                else if(bmi>=40){
                    bmi_result.setText(getResources().getString(R.string.obese_class_3));
                }
                else {
                    bmi_result.setText(getResources().getString(R.string.error));
                }
            }
        });
        return v;
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

package layout;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import nimbl3.com.sokies.Activity.accept_adapter;
import nimbl3.com.sokies.Activity.accept_list;
import nimbl3.com.sokies.Activity.assigned_adapter;
import nimbl3.com.sokies.Activity.assigned_list;
import nimbl3.com.sokies.R;
import nimbl3.com.sokies.interfaces.API_Result;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Acceptedorder.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Acceptedorder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Acceptedorder extends Fragment {
    Spinner selection;
    private List<accept_list> listItemsList = new ArrayList<>();




    accept_adapter address_adapter;
    String query;
    InputStream is = null;
    String result = "";
    String stock_list, advisor_name;
    JSONObject jsonobject, jArray2;
    JSONArray jsonarray;
    public static String currentState;
    public static String tempSStateCheck;
    public String mEmailValidCheck;
    Button payment;
    public Integer iCountryId;
    TextView go_login_page,get_date1;
    int customer_id;
    String str_company_name,person_name,item_name,rate,remarks,date,data;
    DatePickerDialog dp;
    ArrayList<String> worldlist= new ArrayList<String>();
    Button submit;
    Spinner My_spinner;
    RecyclerView mRecyclerView1;

    EditText get_person_name, get_item_name, get_rate, get_remarks, get_date, get_description, get_client_name;
    Spinner get_stock_list, mySpinner;

    ImageView name, email, address, password, iv_stock_list, phone_no, advisor;
    ProgressDialog progressDialog;
    API_Result api_result;
    private DatePickerDialog.OnDateSetListener listener;
    View v;
    Context context;
    RequestQueue queue1;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Acceptedorder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Acceptedorder.
     */
    // TODO: Rename and change types and number of parameters
    public static Acceptedorder newInstance(String param1, String param2) {
        Acceptedorder fragment = new Acceptedorder();
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
        v= inflater.inflate(R.layout.fragment_assignedorder, container, false);
        mRecyclerView1 = (RecyclerView)v.findViewById(R.id.home_list_recycler_view1);
        mRecyclerView1.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).color(Color.BLUE).build());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView1.setLayoutManager(linearLayoutManager);
        queue1 = Volley.newRequestQueue(getActivity());
        Load_Advisor_advise1();
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
    public void Load_Advisor_advise1() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://gearboxindia.co.in/accepted_orderform.php", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());

                try {
                    JSONArray sportarraydata2 = response.getJSONArray("data");
                    for (int i = 0; i < sportarraydata2.length(); i++) {
                        JSONObject sportarraydata = sportarraydata2.getJSONObject(i);
                        accept_list advisor_customer_group_list = new accept_list();
                        advisor_customer_group_list.setOrder_no1(sportarraydata.getString("order_no"));



                        listItemsList.add(advisor_customer_group_list);
                        address_adapter = new accept_adapter(getActivity(), listItemsList);
                        mRecyclerView1.setAdapter(address_adapter);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // adapter.notifyDataSetChanged();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }});
        queue1.add(jsonObjectRequest);
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

package nimbl3.com.sokies.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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

import nimbl3.com.sokies.R;
import nimbl3.com.sokies.interfaces.API_Result;

public class AcceptedOrderHistory extends AppCompatActivity {

    private List<adminaccepted_list> listItemsList = new ArrayList<>();
    adminaccepted_adapter address_adapter;
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
    RecyclerView mRecyclerView;

    EditText get_person_name, get_item_name, get_rate, get_remarks, get_date, get_description, get_client_name;
    Spinner get_stock_list, mySpinner;

    ImageView name, email, address, password, iv_stock_list, phone_no, advisor;
    ProgressDialog progressDialog;
    API_Result api_result;
    private DatePickerDialog.OnDateSetListener listener;
    View v;
    Context context;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_accepted_form);
        mRecyclerView = (RecyclerView) findViewById(R.id.home_list_recycler_view);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).color(Color.BLUE).build());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        queue = Volley.newRequestQueue(AcceptedOrderHistory.this);
        Load_Advisor_advise();

    }
    public void Load_Advisor_advise() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,"http://gearboxindia.co.in/accepted_orderform.php", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());

                try {
                    JSONArray sportarraydata2 = response.getJSONArray("data");
                    for (int i = 0; i < sportarraydata2.length(); i++) {
                        JSONObject sportarraydata = sportarraydata2.getJSONObject(i);
                        adminaccepted_list advisor_customer_group_list = new adminaccepted_list();
                        advisor_customer_group_list.setOrder_no1(sportarraydata.getString("order_no"));


                        listItemsList.add(advisor_customer_group_list);
                        address_adapter = new adminaccepted_adapter(AcceptedOrderHistory.this, listItemsList);
                        mRecyclerView.setAdapter(address_adapter);

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
        queue.add(jsonObjectRequest);
    }


}
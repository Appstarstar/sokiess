package nimbl3.com.sokies.Activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
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

import java.util.ArrayList;
import java.util.List;

import nimbl3.com.sokies.R;

public class Update_orderform extends AppCompatActivity {
    private static String TAG = "Under_process_Data";
    private List<order_list> listItemsList = new ArrayList<>();

    private RecyclerView mRecyclerView;

    order_adapter address_adapter;
    ProgressDialog progressDialog;
    Button save;
    private String[] arraySpinner;

    GridLayoutManager linearLayoutManager;
    RequestQueue queue;
    SearchView searchView;
    Button btn_search_view;
    int PLACE_PICKER_REQUEST = 1;
    ImageView image1;
    Menu menu1;
    String user_name;
    SharedPreferences pref;
    SharedPreferences.Editor edt;
    String shop_id;

    String str_latitude,str_longtitude;
    ImageButton image;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_orderform);
        mRecyclerView = (RecyclerView) findViewById(R.id.home_list_recycler_view);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).color(Color.BLUE).build());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        queue = Volley.newRequestQueue(Update_orderform.this);
        Load_Advisor_advise();
    }
    public void Load_Advisor_advise() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://gearboxindia.co.in/fetch_orderform.php", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());

                try {
                    JSONArray sportarraydata2 = response.getJSONArray("data");
                    for (int i = 0; i < sportarraydata2.length(); i++) {
                        JSONObject sportarraydata = sportarraydata2.getJSONObject(i);
                        order_list  advisor_customer_group_list = new order_list();
                        advisor_customer_group_list.setProject_manager(sportarraydata.getString("project_manager"));
                        advisor_customer_group_list.setOrder_no(sportarraydata.getString("order_no"));
                        advisor_customer_group_list.setDate1(sportarraydata.getString("order_date"));

                        listItemsList.add(advisor_customer_group_list);
                        address_adapter = new order_adapter(Update_orderform.this, listItemsList);
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

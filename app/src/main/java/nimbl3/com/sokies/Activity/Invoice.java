package nimbl3.com.sokies.Activity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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

import static java.security.AccessController.getContext;

public class Invoice extends AppCompatActivity {
    private static String TAG = "Under_process_Data";
    private List<invoice_list> listItemsList = new ArrayList<>();
    private List<search_list> listItemsList1 = new ArrayList<>();

    private RecyclerView mRecyclerView, mRecyclerView1;

    invoice_adapter address_adapter;
    ProgressDialog progressDialog;
    Button save;
    private String[] arraySpinner;

    GridLayoutManager linearLayoutManager;
    RequestQueue queue;
search_adapter search;
    Button btn_search_view;
    int PLACE_PICKER_REQUEST = 1;
    ImageView image1;
    Menu menu1;
    String user_name;
    SharedPreferences pref;
    SharedPreferences.Editor edt;
    String shop_id,query;

    String str_latitude,str_longtitude;
    ImageButton image;
    TextView text;
SearchView searchView;
    SearchManager searchManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        mRecyclerView = (RecyclerView) findViewById(R.id.home_list_recycler_view);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).color(Color.BLUE).build());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView1 = (RecyclerView) findViewById(R.id.home_list_recycler_view);
        mRecyclerView1.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).color(Color.BLUE).build());
        final LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView1.setLayoutManager(linearLayoutManager1);
        queue = Volley.newRequestQueue(Invoice.this);
        Load_Advisor_advise();
        searchView = (SearchView) findViewById(R.id.search_company);
        searchManager = (SearchManager) this.getSystemService(getApplicationContext().SEARCH_SERVICE);
        searchView.setSearchableInfo((searchManager.getSearchableInfo(this.getComponentName())));
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });






    }
    public void search(){
        SpannableStringBuilder query2=(SpannableStringBuilder) searchView.getQuery();
        query = query2.toString();
        Load_Advisor_advise1();

    }
    public void Load_Advisor_advise() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://gearboxindia.co.in/fetch_invoice.php", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());

                try {
                    JSONArray sportarraydata2 = response.getJSONArray("data");
                    for (int i = 0; i < sportarraydata2.length(); i++) {
                        JSONObject sportarraydata = sportarraydata2.getJSONObject(i);
                       invoice_list advisor_customer_group_list = new invoice_list();
                        advisor_customer_group_list.setName(sportarraydata.getString("name"));
                        advisor_customer_group_list.setInvoice(sportarraydata.getString("invoice_no"));
                      /*  advisor_customer_group_list.setAddress(sportarraydata.getString("address"));
                        advisor_customer_group_list.setFax_no(sportarraydata.getString("fax_no"));
                        advisor_customer_group_list.setKind_attention(sportarraydata.getString("kind_attention"));
                        advisor_customer_group_list.setMobile(sportarraydata.getString("mobile_no"));
                        advisor_customer_group_list.setEmail(sportarraydata.getString("email"));
                        advisor_customer_group_list.setDate(sportarraydata.getString("date"));
                        advisor_customer_group_list.setPrepared_name(sportarraydata.getString("prepared_name"));
                        advisor_customer_group_list.setPrepared_mobile(sportarraydata.getString("prepared_mobile_no"));
                        advisor_customer_group_list.setPacking_percentage(sportarraydata.getString("prepared_designation"));
                        advisor_customer_group_list.setRemark(sportarraydata.getString("remark"));
                        advisor_customer_group_list.setTotal_amount(sportarraydata.getString("total_amount"));
                        advisor_customer_group_list.setPacking_percentage(sportarraydata.getString("packing_percentage"));
                        advisor_customer_group_list.setPacking_rate(sportarraydata.getString("packing_rate"));
                        advisor_customer_group_list.setGst_percentage(sportarraydata.getString("gst_percentage"));
                        advisor_customer_group_list.setGst_rate(sportarraydata.getString("gst_rate"));
                        advisor_customer_group_list.setOther_charges(sportarraydata.getString("other_charges"));
                        advisor_customer_group_list.setNet_amount(sportarraydata.getString("net_amount"));*/
                        listItemsList.add(advisor_customer_group_list);
                        address_adapter = new invoice_adapter(Invoice.this, listItemsList);
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
    public void Load_Advisor_advise1() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://gearboxindia.co.in/fetch_searchpurchase.php?name="+query, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());

                try {
                    JSONArray sportarraydata2 = response.getJSONArray("data");
                    if(search!=null){
                        search.clearAdapter();
                        for (int i = 0; i < sportarraydata2.length(); i++) {
                            JSONObject sportarraydata = sportarraydata2.getJSONObject(i);
                            search_list advisor_customer_group_list1 = new search_list();
                            advisor_customer_group_list1.setName1(sportarraydata.getString("name"));
                            advisor_customer_group_list1.setInvoice1(sportarraydata.getString("invoice_no"));
                      /*advisor_customer_group_list.setAddress(sportarraydata.getString("address"));
                        advisor_customer_group_list.setFax_no(sportarraydata.getString("fax_no"));
                        advisor_customer_group_list.setKind_attention(sportarraydata.getString("kind_attention"));
                        advisor_customer_group_list.setMobile(sportarraydata.getString("mobile_no"));
                        advisor_customer_group_list.setEmail(sportarraydata.getString("email"));
                        advisor_customer_group_list.setDate(sportarraydata.getString("date"));
                        advisor_customer_group_list.setPrepared_name(sportarraydata.getString("prepared_name"));
                        advisor_customer_group_list.setPrepared_mobile(sportarraydata.getString("prepared_mobile_no"));
                        advisor_customer_group_list.setPacking_percentage(sportarraydata.getString("prepared_designation"));
                        advisor_customer_group_list.setRemark(sportarraydata.getString("remark"));
                        advisor_customer_group_list.setTotal_amount(sportarraydata.getString("total_amount"));
                        advisor_customer_group_list.setPacking_percentage(sportarraydata.getString("packing_percentage"));
                        advisor_customer_group_list.setPacking_rate(sportarraydata.getString("packing_rate"));
                        advisor_customer_group_list.setGst_percentage(sportarraydata.getString("gst_percentage"));
                        advisor_customer_group_list.setGst_rate(sportarraydata.getString("gst_rate"));
                        advisor_customer_group_list.setOther_charges(sportarraydata.getString("other_charges"));
                        advisor_customer_group_list.setNet_amount(sportarraydata.getString("net_amount"));*/
                            listItemsList1.add(advisor_customer_group_list1);
                            search = new search_adapter(Invoice.this, listItemsList1);
                            mRecyclerView1.setAdapter(search);}}
                    else {
                        for (int i = 0; i < sportarraydata2.length(); i++) {
                            JSONObject sportarraydata = sportarraydata2.getJSONObject(i);
                            search_list advisor_customer_group_list1 = new search_list();
                            advisor_customer_group_list1.setName1(sportarraydata.getString("name"));
                            advisor_customer_group_list1.setInvoice1(sportarraydata.getString("invoice_no"));
                      /*advisor_customer_group_list.setAddress(sportarraydata.getString("address"));
                        advisor_customer_group_list.setFax_no(sportarraydata.getString("fax_no"));
                        advisor_customer_group_list.setKind_attention(sportarraydata.getString("kind_attention"));
                        advisor_customer_group_list.setMobile(sportarraydata.getString("mobile_no"));
                        advisor_customer_group_list.setEmail(sportarraydata.getString("email"));
                        advisor_customer_group_list.setDate(sportarraydata.getString("date"));
                        advisor_customer_group_list.setPrepared_name(sportarraydata.getString("prepared_name"));
                        advisor_customer_group_list.setPrepared_mobile(sportarraydata.getString("prepared_mobile_no"));
                        advisor_customer_group_list.setPacking_percentage(sportarraydata.getString("prepared_designation"));
                        advisor_customer_group_list.setRemark(sportarraydata.getString("remark"));
                        advisor_customer_group_list.setTotal_amount(sportarraydata.getString("total_amount"));
                        advisor_customer_group_list.setPacking_percentage(sportarraydata.getString("packing_percentage"));
                        advisor_customer_group_list.setPacking_rate(sportarraydata.getString("packing_rate"));
                        advisor_customer_group_list.setGst_percentage(sportarraydata.getString("gst_percentage"));
                        advisor_customer_group_list.setGst_rate(sportarraydata.getString("gst_rate"));
                        advisor_customer_group_list.setOther_charges(sportarraydata.getString("other_charges"));
                        advisor_customer_group_list.setNet_amount(sportarraydata.getString("net_amount"));*/
                            listItemsList1.add(advisor_customer_group_list1);
                            search = new search_adapter(Invoice.this, listItemsList1);
                            mRecyclerView1.setAdapter(search);
                        }}
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
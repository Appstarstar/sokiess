package nimbl3.com.sokies.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import nimbl3.com.sokies.R;
import nimbl3.com.sokies.interfaces.API_Result;

public class Viewinvoice extends AppCompatActivity {
    RequestQueue queue;
    String invoice;
    private static String TAG = "Leads Asign";
    Spinner get_name;
    EditText get_address,get_fax_no,get_kind,get_mobile,get_email,get_prepared_name,get_prepared_mobile,get_prepared_designation,get_invoice_no,get_date,get_remark,get_total_amount,get_packing_percentage,get_packing_rate,get_gst,get_gst_rate,get_others,get_net_amount;
    ArrayList<String> worldlist= new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewinvoice);


        Intent intent = getIntent();
        invoice = intent.getStringExtra("invoice_no");
        Log.d("invoice", invoice);

        get_name=(Spinner)findViewById(R.id.name_invoice);
        get_address=(EditText) findViewById(R.id.addres_invoice);
        get_fax_no=(EditText) findViewById(R.id.fax_invoice);
        get_kind=(EditText) findViewById(R.id.kind_invoice);
        get_mobile=(EditText) findViewById(R.id.et_mobile_invoice);
        get_email=(EditText) findViewById(R.id.email_invoice);
        get_prepared_name=(EditText) findViewById(R.id.et_prepared_by_quot);
        get_prepared_mobile=(EditText) findViewById(R.id.et_prepare_mobile_quot);
        get_prepared_designation=(EditText) findViewById(R.id.et_prepared_desi_wquot);
        get_invoice_no=(EditText) findViewById(R.id.et_invoiveno_invoice);
        get_date=(EditText) findViewById(R.id.date_invoice);
        get_remark=(EditText) findViewById(R.id.remark_invoice);
        get_total_amount=(EditText) findViewById(R.id.total_invoice);
        get_packing_percentage=(EditText) findViewById(R.id.packing_invoice);
        get_packing_rate=(EditText) findViewById(R.id.packing_rate_invoice);
        get_gst=(EditText) findViewById(R.id.gst_invoice);
        get_gst_rate=(EditText) findViewById(R.id.gst_rate_invoice);
        get_others=(EditText) findViewById(R.id.et_other_quot);
        get_net_amount=(EditText) findViewById(R.id.net_amount_invoice);
        queue = Volley.newRequestQueue(Viewinvoice.this);
         Load_Advisor_advise();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    public void Load_Advisor_advise() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://gearboxindia.co.in/fetch_invoice_through_invoice_no.php?invoice_no="+invoice, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());

                try {
                    JSONArray sportarraydata2 = response.getJSONArray("data");


                    for (int i = 0; i < sportarraydata2.length(); i++) {
                        JSONObject sportarraydata = sportarraydata2.getJSONObject(i);
                        worldlist.add(sportarraydata.getString("name"));
                     get_name
                                .setAdapter(new ArrayAdapter<String>(Viewinvoice.this,
                                        android.R.layout.simple_spinner_dropdown_item,
                                        worldlist));
                        get_invoice_no.setText(sportarraydata.getString("invoice_no"));
                        get_address.setText(sportarraydata.getString("address"));
                        get_fax_no.setText(sportarraydata.getString("fax_no"));
                        get_kind.setText(sportarraydata.getString("kind_attention"));
                        get_mobile.setText(sportarraydata.getString("mobile_no"));
                        get_email.setText(sportarraydata.getString("email"));
                        get_date.setText(sportarraydata.getString("date"));
                        get_prepared_name.setText(sportarraydata.getString("prepared_name"));
                        get_prepared_mobile.setText(sportarraydata.getString("prepared_mobile_no"));
                        get_prepared_designation.setText(sportarraydata.getString("prepared_designation"));
                        get_remark.setText(sportarraydata.getString("remark"));
                        get_total_amount.setText(sportarraydata.getString("total_amount"));
                        get_packing_percentage.setText(sportarraydata.getString("packing_percentage"));
                        get_packing_rate.setText(sportarraydata.getString("packing_rate"));
                        get_gst.setText(sportarraydata.getString("gst_percentage"));
                        get_gst_rate.setText(sportarraydata.getString("gst_rate"));
                        get_others.setText(sportarraydata.getString("other_charges"));
                        get_net_amount.setText(sportarraydata.getString("net_amount"));





                    }


                    // Spinner on item click listener


                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                    adapter.notifyDataSetChanged();
            }

        }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error Message" + error.getMessage());


            }
        });
        queue.add(jsonObjectRequest);

    }


}

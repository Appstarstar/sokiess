package nimbl3.com.sokies.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class QuotationMaster extends AppCompatActivity {
    EditText email,fax_no,prepared_name,prepared_mobile,prepared_designation,price,gst1,inspection,payment,delivery,validity,get_address,kind_attention;
    Spinner Myspinner;
    TextView quotation_no,date1;
    RequestQueue queue;
    String quotation;
    Button ok_quot;
    private static String TAG = "Leads Asign";
    ArrayList<String> worldlist= new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation_master);

        Intent intent = getIntent();
        quotation = intent.getStringExtra("quotation_no");
        Log.d("quotation", quotation);
        email=(EditText)findViewById(R.id.et_email_quot);
        fax_no=(EditText)findViewById(R.id.et_et_whats_app_quot);
        kind_attention=(EditText)findViewById(R.id.et_kind_attn_quot);
        prepared_name=(EditText)findViewById(R.id.et_prepared_by_quot);
        prepared_mobile=(EditText)findViewById(R.id.et_prepare_mobile_quot);
        prepared_designation=(EditText)findViewById(R.id.et_prepared_desi_wquot);
        price=(EditText)findViewById(R.id.et_price_quot);
        gst1=(EditText)findViewById(R.id.et_gst_quot);
        inspection=(EditText)findViewById(R.id.et_inspection_quot);
        payment=(EditText)findViewById(R.id.et_payment_quot);
        delivery=(EditText)findViewById(R.id.et_delivery_quot);
        validity=(EditText)findViewById(R.id.et_validity_quot);
        Myspinner =(Spinner)findViewById(R.id.quotation_comp);
        quotation_no=(TextView)findViewById(R.id.tv_quotation_number_quot);
        date1=(TextView)findViewById(R.id.tv_current_date_quot);
        queue = Volley.newRequestQueue(QuotationMaster.this);
        get_address=(EditText)findViewById(R.id.et_address_quot);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Load_Advisor_advise();
        ok_quot=(Button)findViewById((R.id.btn_OK_quot));
        ok_quot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuotationMaster.this, Quotation.class);
                startActivity(intent);
            }
        });

    }
    public void Load_Advisor_advise() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://gearboxindia.co.in/fetch_quotation_through_quotation_no.php?quotation_no="+quotation, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());

                try {
                    JSONArray sportarraydata2 = response.getJSONArray("data");


                    for (int i = 0; i < sportarraydata2.length(); i++) {
                        JSONObject sportarraydata = sportarraydata2.getJSONObject(i);
                        quotation_no.setText(sportarraydata.getString("invoice_no"));
                        get_address.setText(sportarraydata.getString("address"));
                        fax_no.setText(sportarraydata.getString("fax_no"));
                        kind_attention.setText(sportarraydata.getString("kind_attention"));

                        email.setText(sportarraydata.getString("email"));
                        date1.setText(sportarraydata.getString("date"));
                        prepared_name.setText(sportarraydata.getString("prepared_name"));
                        prepared_mobile.setText(sportarraydata.getString("prepared_mobile_no"));
                       prepared_designation.setText(sportarraydata.getString("prepared_designation"));
                        gst1.setText(sportarraydata.getString("gst"));
                        price.setText(sportarraydata.getString("price"));
                        inspection.setText(sportarraydata.getString("inspection"));
                        payment.setText(sportarraydata.getString("payment"));
                        delivery.setText(sportarraydata.getString("delivery"));
                        validity.setText(sportarraydata.getString("validity"));
                        worldlist.add(sportarraydata.getString("name"));


                        // Spinner adapter
                        Myspinner
                                .setAdapter(new ArrayAdapter<String>(QuotationMaster.this,
                                        android.R.layout.simple_spinner_dropdown_item,
                                        worldlist));

                    }
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

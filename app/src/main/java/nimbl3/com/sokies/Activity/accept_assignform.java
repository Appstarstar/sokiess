package nimbl3.com.sokies.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import nimbl3.com.sokies.R;

public class accept_assignform extends AppCompatActivity {
    protected TextView Comapany_name, date, order_no, order_date, size, type, drawing, ratio, remarks, quantity, deliver, project, party, HairStraightening, HairColouring, HairCut, HairCute;
    private CardView card;
    String order,data;
    RequestQueue queue;
    private static String TAG = "Leads Asign";
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_assignform);
        Intent intent = getIntent();
        order= intent.getStringExtra("order_no");
        Log.d("order_no", order);

        order_no = (TextView) findViewById(R.id.orde_no1);
           order_date = (TextView) findViewById(R.id.order_date1);
           size = (TextView) findViewById(R.id.size1);
           type = (TextView) findViewById(R.id.type1);
           drawing = (TextView) findViewById(R.id.drawing1);
            ratio = (TextView) findViewById(R.id.ratio1);
           quantity = (TextView) findViewById(R.id.quantity1);
           deliver = (TextView) findViewById(R.id.delivery1);
       project = (TextView) findViewById(R.id.project_manager);
            party = (TextView) findViewById(R.id.party1);
           remarks = (TextView) findViewById(R.id.remarks1);
        queue = Volley.newRequestQueue(accept_assignform.this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Load_Advisor_advise();
        card = (CardView) findViewById(R.id.card_view12);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View d) {

                register(d);
            }
        });
    }
    public void Load_Advisor_advise() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://gearboxindia.co.in/fetch_assignorder_through_orderno.php?order_no="+order, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());

                try {
                    JSONArray sportarraydata2 = response.getJSONArray("data");


                    for (int i = 0; i < sportarraydata2.length(); i++) {
                        JSONObject sportarraydata = sportarraydata2.getJSONObject(i);
                        project.setText(sportarraydata.getString("project_manager"));
                        type.setText(sportarraydata.getString("type_gear_box"));
                        size.setText(sportarraydata.getString("size_gear_box"));
                        drawing.setText(sportarraydata.getString("drawing_no"));;
                        ratio.setText(sportarraydata.getString("ratio"));
                        quantity.setText(sportarraydata.getString("quantity"));
                        party.setText(sportarraydata.getString("party_name"));

                        remarks.setText(sportarraydata.getString("remarks"));
                        order_date.setText(sportarraydata.getString("order_date"));
                        deliver.setText(sportarraydata.getString("deliver_date"));
                        order_no.setText(sportarraydata.getString("order_no"));




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




    public void hidedp() {

        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }


    }
    public void register(View d){

        new accept_assignform.Background().execute(order);


    }




    class Background extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {



            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://gearboxindia.co.in/update_assignedorderform.php?order_no="+order);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);



                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse httpRes = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpRes.getEntity();
                data= EntityUtils.toString(httpEntity);

                Log.d("Data", data);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;

        }
        @Override
        protected void onPostExecute(String s) {
            hidedp();
            Toast.makeText(accept_assignform.this, "Accepted", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(accept_assignform.this,Assignedorder_projectmanager.class);
            startActivity(intent);
            finish();


        }



    }
}




package nimbl3.com.sokies.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nimbl3.com.sokies.R;
import nimbl3.com.sokies.interfaces.API_Result;

import static android.content.ContentValues.TAG;

public class Create_rate_master extends AppCompatActivity {
    Spinner selection;
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

    EditText get_person_name, get_item_name, get_rate, get_remarks, get_date, get_description, get_client_name;
    Spinner get_stock_list, mySpinner;

    ImageView name, email, address, password, iv_stock_list, phone_no, advisor;
    ProgressDialog progressDialog;
    API_Result api_result;
    private DatePickerDialog.OnDateSetListener listener;
    View v;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_rate_master);
        My_spinner = (Spinner) findViewById(R.id.spinner_company);
        get_person_name = (EditText) findViewById(R.id.et_personname1);
        get_item_name= (EditText) findViewById(R.id.et_itemname1);
        get_rate= (EditText) findViewById(R.id.et_rate1);
        get_remarks= (EditText) findViewById(R.id.et_remarks1);
        get_date1 = (TextView) findViewById(R.id.tv_order_date21);

        queue = Volley.newRequestQueue(Create_rate_master.this);
        Load_Advisor_advise();
        submit = (Button) findViewById(R.id.btn_rate_submit1);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register(v);
            }
        });


        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        get_date1.setText(new StringBuilder()
                .append(mYear).append("-")
                .append(mMonth + 1).append("-")
                .append(mDay).append(" "));

    }
    public void Load_Advisor_advise() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://gearboxindia.co.in/companyname.php", null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());

                try {
                    JSONArray sportarraydata2 = response.getJSONArray("data");


                    for (int i = 0; i < sportarraydata2.length(); i++) {
                        JSONObject sportarraydata = sportarraydata2.getJSONObject(i);
                        worldlist.add(sportarraydata.getString("company_name"));


                        // Spinner adapter
                        My_spinner
                                .setAdapter(new ArrayAdapter<String>(Create_rate_master.this,
                                        android.R.layout.simple_spinner_dropdown_item,
                                        worldlist));
                        My_spinner
                                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                    @Override
                                    public void onItemSelected(AdapterView<?> arg0,
                                                               View arg1, int position, long arg3) {
                                        // TODO Auto-generated method stub
                                        // Locate the textviews in activity_main.xml
                                        str_company_name = My_spinner.getSelectedItem().toString();


                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> arg0) {
                                        // TODO Auto-generated method stub
                                        Toast.makeText(getApplicationContext(), "Please Select the Project Manager" + "\t",
                                                Toast.LENGTH_LONG).show();
                                    }
                                });
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
    public void register(View v){
        person_name = get_person_name.getText().toString().trim();

        item_name = get_item_name.getText().toString().trim();
        rate = get_rate.getText().toString().trim();
        remarks =  get_remarks.getText().toString().trim();
        date =get_date1.getText().toString().trim();

        new Create_rate_master.Background().execute(person_name,item_name,rate,remarks,date,str_company_name);


    }




    class Background extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String subject = params[0];
            String branch = params[1];
            String first_name= params[2];
            String last_name=params[3];
            String emailaddress =params[4];
            String password=params[5];

            data=null;
            int tmp;


            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://gearboxindia.co.in/ratemaster.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
                nameValuePairs.add(new BasicNameValuePair("company_name", str_company_name));

                nameValuePairs.add(new BasicNameValuePair("concern_name", person_name));
                nameValuePairs.add(new BasicNameValuePair("item_name", item_name));
                nameValuePairs.add(new BasicNameValuePair("rate",  rate));
                nameValuePairs.add(new BasicNameValuePair("remarks",  remarks));
                nameValuePairs.add(new BasicNameValuePair("date",date));


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
            Toast.makeText(Create_rate_master.this, "Done", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(Create_rate_master.this,Create_rate_master.class);
            startActivity(intent);
            finish();


        }



    }
}
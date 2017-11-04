package nimbl3.com.sokies.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nimbl3.com.sokies.Network_checker.NetworkConnection;
import nimbl3.com.sokies.R;
import nimbl3.com.sokies.api_call.API_Get;
import nimbl3.com.sokies.constant_class.JSON_Names;
import nimbl3.com.sokies.constant_class.URL_Class;
import nimbl3.com.sokies.db_handler.DataBaseHandlerAccount;
import nimbl3.com.sokies.interfaces.API_Result;
import nimbl3.com.sokies.json_machanism.GetJSONData;
import nimbl3.com.sokies.machanism.Methods;
import nimbl3.com.sokies.utils.Response;

public class AddressMaster extends AppCompatActivity implements API_Result {

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
    TextView go_login_page;
    String selected_option, selected_city;
   TextView get_date;
    Button submit,update;
    private static String TAG = "Leads Asign";
    EditText get_company_name, get_address, get_concern_person, get_mobile, get_whatsapp, get_contact_person, get_department, get_email;
    EditText get_phone, get_alternate_mobile, get_state, get_pincode, get_city, get_subject, get_file_number;
    ProgressDialog progressDialog;
    String str_company,data,company_name,address,pin_code,state,city,subject,fax_number,email,file_number,concern_person,mobile_number,alternate_mobile_number,contact_person_name,department,phone_number,date;
    API_Result api_result;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_master);

        api_result = this;
        progressDialog = new ProgressDialog(this);
       /* if (NetworkConnection.connectionChecking(getApplicationContext())) {
            if (NetworkConnection.connectionType(getApplicationContext())) {

                /
            } else {
                Toast.makeText(this, "Check your Network Connection", Toast.LENGTH_SHORT).show();
            }
            //

        } else {
            Toast.makeText(this, "Check your Network Connection", Toast.LENGTH_SHORT).show();
        }*/

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        get_company_name = (EditText) findViewById(R.id.et_add_company_name);
        get_concern_person = (EditText) findViewById(R.id.et_add_concer_name);
        get_email = (EditText) findViewById(R.id.et_add_email);
        get_mobile = (EditText) findViewById(R.id.et_add_mobile);
        get_address = (EditText) findViewById(R.id.et_add_address);
        get_whatsapp = (EditText) findViewById(R.id.et_add_whats_app);
        get_contact_person = (EditText) findViewById(R.id.et_add_contact_person);
        get_department = (EditText) findViewById(R.id.et_add_department);
        get_phone = (EditText) findViewById(R.id.et_add_phone_number);
        get_state = (EditText) findViewById(R.id.et_add_state);
        get_alternate_mobile = (EditText) findViewById(R.id.et_add_altenate_mobile);
        get_pincode = (EditText) findViewById(R.id.et_add_pincode);
        get_city = (EditText) findViewById(R.id.et_add_city);
        get_subject = (EditText) findViewById(R.id.et_add_subject);
        get_file_number = (EditText) findViewById(R.id.et_add_file);
        get_date = (TextView) findViewById(R.id.tv_order_date1);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        get_date.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mYear).append("-")
                        .append(mMonth + 1).append("-")
                        .append(mDay).append(" "));


        submit =(Button) findViewById(R.id.btn_create_add_masters);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpd();
                register(v);
            }
        });
        update = (Button) findViewById(R.id.btn_update_add_master);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressMaster.this, content_updateaddressmaster.class);
                startActivity(intent);
            }
        });

    }





    public void showpd() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Registering Please Wait......");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }

    public void hidedp() {

        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }


    }
    public void register(View v){
        company_name =get_company_name.getText().toString().trim();
        address = get_address.getText().toString().trim();
        pin_code = get_pincode.getText().toString().trim();
       state =  get_state.getText().toString().trim();
        city =get_city.getText().toString().trim();
      subject =  get_subject.getText().toString().trim();
       fax_number = get_whatsapp .getText().toString().trim();
       email= get_email.getText().toString().trim();
        file_number= get_file_number.getText().toString().trim();
       concern_person= get_concern_person.getText().toString().trim();
     mobile_number= get_mobile.getText().toString().trim();
     alternate_mobile_number= get_alternate_mobile.getText().toString().trim();
      contact_person_name= get_contact_person.getText().toString().trim();
       department= get_department.getText().toString().trim();
  phone_number= get_phone.getText().toString().trim();

      date =get_date.getText().toString().trim();
        new AddressMaster.Background().execute(company_name,address,pin_code,state,city,subject,fax_number,email,file_number,concern_person,mobile_number,alternate_mobile_number,contact_person_name,department,phone_number,date);


    }

    @Override
    public void result(String[] data, String source) {

    }

    class Background extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String subject1 = params[0];
            String branch = params[1];
            String first_name= params[2];
            String last_name=params[3];
            String emailaddress =params[4];
            String password=params[5];
            String confirmpassword=params[6];
            String mobile=params[7];
            String city1=params[8];
            data=null;
            int tmp;


            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://gearboxindia.co.in/addressmaster.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
                nameValuePairs.add(new BasicNameValuePair("company_name", company_name));

                nameValuePairs.add(new BasicNameValuePair("address", address));
                nameValuePairs.add(new BasicNameValuePair("pin_code", pin_code));
                nameValuePairs.add(new BasicNameValuePair("state",  state));
                nameValuePairs.add(new BasicNameValuePair("city",city));
                nameValuePairs.add(new BasicNameValuePair("subject",subject));
                nameValuePairs.add(new BasicNameValuePair("fax_number",fax_number ));
                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("file_number", file_number));
                nameValuePairs.add(new BasicNameValuePair("concern_person", concern_person));
                nameValuePairs.add(new BasicNameValuePair("mobile_number", mobile_number));
                nameValuePairs.add(new BasicNameValuePair("alternate_mobile_number", alternate_mobile_number));
                nameValuePairs.add(new BasicNameValuePair("contact_person_name", contact_person_name));
                nameValuePairs.add(new BasicNameValuePair("department", department));
                nameValuePairs.add(new BasicNameValuePair("phone_number", phone_number));
                nameValuePairs.add(new BasicNameValuePair("date", date));




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
            Toast.makeText(AddressMaster.this, "Created", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(AddressMaster.this,Login.class);
            startActivity(intent);
            finish();


        }



    }
}



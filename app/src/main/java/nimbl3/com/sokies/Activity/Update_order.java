package nimbl3.com.sokies.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nimbl3.com.sokies.R;
import nimbl3.com.sokies.interfaces.API_Result;

public class Update_order extends AppCompatActivity implements API_Result ,View.OnClickListener{

    Spinner assigned_to, group, city, team, budget, salable_sqft, carpet_sqft, bedroom,production;
    String assigned, groups, teams, select_city, budgets, salable, carpet, bedrooms, sources, subsources, statuss,productions;
    String query;
    InputStream is = null;
    String categories = "";
    String stock_list, advisor_name;
    JSONObject jsonobject, jArray2;
    JSONArray jsonarray;
    public static String currentState;
    public static String tempSStateCheck;
    public String mEmailValidCheck;
    Button payment;
    public Integer iCountryId;
    ToggleButton get_reminder;
    ImageView image1;
    ImageView iv_document_image;
    private Boolean upflag = false;
    private Uri selectedImage = null;
    Bitmap sitebitmap, planbitmap,pricebitmap;
    int i;
    private ProgressDialog pDialog;
    String imagepath = "";
    String fname,type_gear_box, efficiencey,size_gear_box, drawing_no,  ratio,quantity,party_name,remarks, order_no,data,str_city,balance_quantity,deliver_quantity,order_date,delivery_date,month1,year1,day1;
    private static final int SELECT_PICTURE = 100;
    private Uri filePath;
    File file;
    int Size;
    ArrayList<String> worldlist= new ArrayList<String>();
    Button submit,update, get_price, get_plan, get_site;
    TextView get_date,get_order_number,get_date1;
    EditText get_gears, get_drawing_no, get_ratio, get_quantity, get_partyname, get_remarkorder,get_delivery_date,get_typegears,get_efficiencey,get_balance, get_deliver,get_deliver_quantity;
    private DatePickerDialog.OnDateSetListener listener;
    private int daysOfMonth = 31;
    private static String TAG = "Leads Asign";
    public static TextView assign_message;
    private NumberPicker monthPicker;
    private NumberPicker yearPicker;
    private NumberPicker dayPicker;
    private String[] arraySpinner;
    private Calendar cal = Calendar.getInstance();
    RequestQueue queue;
    private ArrayList<String> students;

    int monthVal = -1, dayVal = -1, yearVal = -1;

    private JSONArray result;
    DatePicker datepicker;
    ProgressDialog progressDialog;
    Spinner My_spinner;
    API_Result api_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order);
        Intent intent = getIntent();
        order_no = intent.getStringExtra("order_no");
        Log.d("order_no", order_no);
        api_result = this;
        progressDialog = new ProgressDialog(this);
        get_typegears= (EditText) findViewById(R.id.et_typegears);

        get_gears = (EditText) findViewById(R.id.et_gears);
        get_drawing_no = (EditText) findViewById(R.id.et_drawing);
        get_ratio = (EditText) findViewById(R.id.et_ratio);
        get_quantity = (EditText) findViewById(R.id.et_quantity);
        get_partyname = (EditText) findViewById(R.id.et_partyname);
        get_balance = (EditText) findViewById(R.id.et_balance_quantity);
        get_deliver_quantity = (EditText) findViewById(R.id.et_deliverquantity);

        get_remarkorder = (EditText) findViewById(R.id.et_remarkorder);
        get_date =(TextView) findViewById(R.id.tv_order_date);
        get_date1 = (TextView) findViewById(R.id.tv_date);
        get_order_number = (TextView) findViewById(R.id.tv_order_number);
        students = new ArrayList<String>();
        My_spinner = (Spinner) findViewById(R.id.soki_company_type);
        queue = Volley.newRequestQueue(Update_order.this);
        Load_Advisor_advise();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);




        monthPicker = (NumberPicker) findViewById(R.id.picker_month);
        yearPicker = (NumberPicker) findViewById(R.id.picker_year);
        dayPicker = (NumberPicker) findViewById(R.id.picker_day);

        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);


        if (monthVal != -1)// && (monthVal > 0 && monthVal < 13))
            monthPicker.setValue(monthVal);
        else
            monthPicker.setValue(cal.get(Calendar.MONTH) + 1);

        monthPicker.setDisplayedValues(new String[]{"Jan", "Feb", "Mar", "Apr", "May", "June", "July",
                "Aug", "Sep", "Oct", "Nov", "Dec"});


        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(daysOfMonth);

        if (dayVal != -1)
            dayPicker.setValue(dayVal);
        else
            dayPicker.setValue(cal.get(Calendar.DAY_OF_MONTH));

        monthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                switch (newVal) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        daysOfMonth = 31;
                        dayPicker.setMaxValue(daysOfMonth);
                        break;
                    case 2:
                        daysOfMonth = 28;
                        dayPicker.setMaxValue(daysOfMonth);
                        break;

                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        daysOfMonth = 30;
                        dayPicker.setMaxValue(daysOfMonth);
                        break;
                }

            }
        });

        int maxYear = cal.get(Calendar.YEAR);//2016
        final int minYear = 1997;
        int arraySize = maxYear - minYear;

        String[] tempArray = new String[arraySize];
        tempArray[0] = "---";
        int tempYear = minYear + 1;

        for (int i = 0; i < arraySize; i++) {
            if (i != 0) {
                tempArray[i] = " " + tempYear + "";
            }
            tempYear++;
        }
        Log.i("", "onCreateDialog: " + tempArray.length);
        yearPicker.setMinValue(minYear + 1);
        yearPicker.setMaxValue(maxYear);
        yearPicker.setDisplayedValues(tempArray);

        yearPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        year1= String.valueOf(yearPicker.getValue());
        month1= String.valueOf(monthPicker.getValue());
        day1 = String.valueOf(dayPicker.getValue());


        submit =(Button) findViewById(R.id.btn_submit_order12);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View d) {
                showpd();
                register(d);
            }
        });


    }




    public void Load_Advisor_advise() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://gearboxindia.co.in/fetch_data_orderno.php?order_no="+order_no, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());

                try {
                    JSONArray sportarraydata2 = response.getJSONArray("data");


                    for (int i = 0; i < sportarraydata2.length(); i++) {
                        JSONObject sportarraydata = sportarraydata2.getJSONObject(i);
                        worldlist.add(sportarraydata.getString("project_manager"));
                        get_typegears.setText(sportarraydata.getString("type_gear_box"));
                        get_gears.setText(sportarraydata.getString("size_gear_box"));
                        get_drawing_no.setText(sportarraydata.getString("drawing_no"));;
                        get_ratio.setText(sportarraydata.getString("ratio"));
                        get_quantity.setText(sportarraydata.getString("quantity"));
                        get_partyname.setText(sportarraydata.getString("party_name"));
                        get_balance.setText(sportarraydata.getString("balance_quantity"));
                        get_deliver_quantity.setText(sportarraydata.getString("deliver_quantity"));
                        get_remarkorder.setText(sportarraydata.getString("remarks"));
                        get_date.setText(sportarraydata.getString("order_date"));
                        get_date1.setText(sportarraydata.getString("deliver_date"));
                        get_order_number.setText(sportarraydata.getString("order_no"));


                        // Spinner adapter
                        My_spinner
                                .setAdapter(new ArrayAdapter<String>(Update_order.this,
                                        android.R.layout.simple_spinner_dropdown_item,
                                        worldlist));
                        My_spinner
                                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                    @Override
                                    public void onItemSelected(AdapterView<?> arg0,
                                                               View arg1, int position, long arg3) {
                                        // TODO Auto-generated method stub
                                        // Locate the textviews in activity_main.xml
                                        str_city = My_spinner.getSelectedItem().toString();


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
    public void register(View d){
        type_gear_box = get_typegears.getText().toString().trim();
        balance_quantity= get_balance.getText().toString().trim();
        deliver_quantity= get_deliver.getText().toString().trim();
        order_no = get_order_number.getText().toString().trim();
        size_gear_box = get_gears.getText().toString().trim();
        drawing_no =  get_drawing_no.getText().toString().trim();
        ratio =get_ratio.getText().toString().trim();
        quantity =  get_quantity.getText().toString().trim();
        party_name = get_partyname .getText().toString().trim();
        remarks= get_remarkorder.getText().toString().trim();
        order_date= get_date.getText().toString().trim();
        delivery_date=get_date.getText().toString().trim();
        new Update_order.Background().execute(type_gear_box,size_gear_box, drawing_no,ratio,quantity,party_name,remarks,order_date,delivery_date,balance_quantity,deliver_quantity);


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void result(String[] data, String source) {

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
            String confirmpassword=params[6];
            String mobile=params[7];
            String city=params[8];
            data=null;
            int tmp;


            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://gearboxindia.co.in/updorder.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
                nameValuePairs.add(new BasicNameValuePair("project_manager", str_city));

                nameValuePairs.add(new BasicNameValuePair("size_gear_box", size_gear_box));
                nameValuePairs.add(new BasicNameValuePair("type_gear_box", type_gear_box));
                nameValuePairs.add(new BasicNameValuePair("drawing_no",  drawing_no));
                nameValuePairs.add(new BasicNameValuePair("ratio",  ratio));
                nameValuePairs.add(new BasicNameValuePair("quantity",quantity));
                nameValuePairs.add(new BasicNameValuePair("balance_quantity",balance_quantity));
                nameValuePairs.add(new BasicNameValuePair("deliver_quantity",deliver_quantity));
                nameValuePairs.add(new BasicNameValuePair("party_name", party_name ));
                nameValuePairs.add(new BasicNameValuePair("remarks", remarks));
                nameValuePairs.add(new BasicNameValuePair("order_no", order_no));
                nameValuePairs.add(new BasicNameValuePair("order_date", order_date));
                nameValuePairs.add(new BasicNameValuePair("delivery_date", delivery_date));


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
            Toast.makeText(Update_order.this, "Done", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(Update_order.this,Login.class);
            startActivity(intent);
            finish();


        }



    }
}









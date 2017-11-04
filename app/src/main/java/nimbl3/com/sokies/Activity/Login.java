package nimbl3.com.sokies.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
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
import nimbl3.com.sokies.shared_preferences.DataStorage;
import nimbl3.com.sokies.utils.AccountDataSet;
import nimbl3.com.sokies.utils.Response;

public class Login extends AppCompatActivity implements API_Result {
    Toolbar toolbar;
    Button login;
    TextView forget_pwd, sign_up;
    EditText get_email_id, get_pwd;
    CheckBox chk_box_show_pwd;
    API_Result api_result;
    ProgressDialog dialog;
    Spinner selection;
    String selected_option;
    SharedPreferences.Editor editor;
    ArrayList<AccountDataSet> profileList = new ArrayList<>();
    AccountDataSet accountDataSet;
    SharedPreferences myPrefs;
    public static final String MY_PREFS_NAME2 = "MyPrefsFile2";
    public static final String MY_PREFS_NAME = "MyPrefsfile";
    RequestQueue requestqueue;
    ProgressDialog progressDialog ;
    private StringRequest stringRequest;
    SharedPreferences pref;
    SharedPreferences.Editor edt;
    String as;
    private String[] arraySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.arraySpinner = new String[] {
                "Admin", "Project Manager", "Supervisor","sales" ,"Marketing","Store"
        };
        api_result = this;
        dialog = new ProgressDialog(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        myPrefs = getSharedPreferences(MY_PREFS_NAME2, Context.MODE_PRIVATE);
        editor = myPrefs.edit();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        get_email_id = (EditText) findViewById(R.id.et_login_enter_email_id);
        get_pwd = (EditText) findViewById(R.id.et_login_enter_password);

        login = (Button) findViewById(R.id.btn_login_pg_login);
      //  forget_pwd = (TextView) findViewById(R.id.tv_login_pg_forget_pwd);
        // sign_up = (TextView) findViewById(R.id.tv_login_pg_sign_up);
        chk_box_show_pwd = (CheckBox) findViewById(R.id.c_box_tick_login);
        selection = (Spinner) findViewById(R.id.spiner_selection);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        selection.setAdapter(adapter);
        selection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                as =selection. getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        pref = getPreferences(Context.MODE_PRIVATE);
        edt = pref.edit();


        chk_box_show_pwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    get_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    get_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });


        // sign_up.setPaintFlags(sign_up.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        requestqueue= Volley.newRequestQueue(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpd();
                stringRequest = new StringRequest(Request.Method.POST, "http://gearboxindia.co.in/login.php", new com.android.volley.Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            hidedp();
                            if (json.names().get(0).equals("success")) {
                                Toast.makeText(getApplicationContext(), "Success" + json.getString("success"), Toast.LENGTH_SHORT).show();
                                if (accountDataSet != null) {
                                    DataBaseHandlerAccount.getInstance(getApplicationContext()).insert_account_detail_new(accountDataSet);
                                    Intent intent=new Intent(getApplicationContext(),Home.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "email and password is not correct", Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "username and password is not correct", Toast.LENGTH_LONG).show();
                            }
                            String person_id=json.getString("id");
                            edt.putString("person_id",person_id);
                            edt.commit();

                        } catch (Exception e) {
                        }


                    }

                }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("category",as);
                        hashMap.put("user",  get_email_id.getText().toString().trim());
                        hashMap.put("pass", get_pwd.getText().toString().trim());
                        return hashMap;
                    }
                };
                requestqueue.add(stringRequest);
            }

            public void showpd() {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setMessage("Logging In Please Wait........");
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
        });}

    @Override
    public void result(String[] data, String source) {

    }
}

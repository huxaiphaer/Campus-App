package campus.app.com.newline.campusmobapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class Login extends AppCompatActivity {

    private EditText edt_Reg;
    private EditText ed_Pass;
    private Button bt_login;
     String username= "";
    String password ="";
    public static String KEY_USERNAME="Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
        edt_Reg  =(EditText) findViewById(R.id.edt_Reg);
        ed_Pass =(EditText) findViewById(R.id.ed_Pass);
        bt_login =(Button) findViewById(R.id.bt_login);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //computer ip address 192.168.43.104:82
                username = edt_Reg.getText().toString();
                password = ed_Pass.getText().toString();


                final ProgressDialog pd;
                pd = new ProgressDialog(Login.this);
                pd.setMessage("Logging in...");
                pd.setCancelable(false);
                pd.show();
                final String url = "http://10.0.2.2:82/COOPERP/Mobile/Loginaspx.aspx?Username="+username+"&Password="+password;
                Ion.with(getBaseContext())
                        .load(url)
                        .progressDialog(pd)
                        .asString()
                        .setCallback(new FutureCallback<String>() {
                            @Override
                            public void onCompleted(Exception e, String result) {
                                try {
                                    if (result.toString().equals("Accepted") || result.toString().equals("user")) {
                                       Intent myIntent = new Intent(getBaseContext(), StudentsInfo.class);
                                      //  myIntent.putExtra("username", username.getText().toString().toLowerCase());
                                        myIntent.putExtra(KEY_USERNAME, edt_Reg.getText().toString());
                                        startActivity(myIntent);
                                        Toast.makeText(Login.this,"Logged in",Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(getBaseContext(), "Invalid User Name or Password"+ result, Toast.LENGTH_LONG).show();
                                    }
                                } catch (Exception ex) {
                                    Toast.makeText(getBaseContext(), "No Connectivity. Check and try again", Toast.LENGTH_LONG).show();

                                }
                                pd.dismiss();
                            }
                        });

            }
        });
            }






    }





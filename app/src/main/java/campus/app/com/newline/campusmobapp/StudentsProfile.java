package campus.app.com.newline.campusmobapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import java.util.List;

import adapters.StudentsProfileAdapter;
import model.StudentsProfileModel;

/**
 * Created by HUZY_KAMZ on 10/12/2016.
 */
public class StudentsProfile extends AppCompatActivity {


    String username = "username";
    RecyclerView rv;
    TextView reg_no;
  private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_main_profile);


      reg_no =(TextView) findViewById(R.id.registration_no);
        rv = (RecyclerView) findViewById(R.id.student_profile_recycler);

        // Initialize recycler view

      //  rv.setLayoutManager(new LinearLayoutManager(this));

        Intent inn = getIntent();
        if (null != inn) {
            username = inn.getStringExtra(Login.KEY_USERNAME);
            //District = inn.getStringExtra(BuyApartmentsOne.KEY_DISTRICT_NAME);
            }
        // Getting Student profile here
         getStudentProfile();



    }



    void getStudentProfile(){

        String url =  "http://10.0.2.2:82/COOPERP/Mobile/Default.aspx?DataFormat=StudentBioData&regno="+username;
        //final static  Context cn;


        final ProgressDialog pd;
        pd = new ProgressDialog(StudentsProfile.this);
        pd.setMessage("Loading....");
        pd.setCancelable(false);
        pd.show();

        Ion.with(getBaseContext())
                .load(url)
                .progressDialog(pd)
                .as(new TypeToken<List<StudentsProfileModel>>() {
                })
                .setCallback(new FutureCallback<List<StudentsProfileModel>>() {

                    @Override
                    public void onCompleted(Exception e, List<StudentsProfileModel> itemList) {
                        try {
                            //Context c;
                            final StudentsProfileAdapter adapter = new StudentsProfileAdapter(itemList,context);
                            if (itemList != null) {
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));

                                Toast.makeText(getApplicationContext(), itemList.size() + " Your Profile", Toast.LENGTH_SHORT).show();
                            } else {
                                rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                                Toast.makeText(getApplicationContext(), "Your Student Profile is Not Available!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception ex) {
                            Toast.makeText(getApplicationContext(), "Internet Error Connectivity  " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        pd.dismiss();
                    }

                });
    }


    }


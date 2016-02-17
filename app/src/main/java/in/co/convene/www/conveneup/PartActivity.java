package in.co.convene.www.conveneup;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class PartActivity extends AppCompatActivity {

    private static final String TAG_ID = "ID";
    private static final String TAG_PART_NAME = "Part_name";
    private static final String TAG_PART_NAME2 = "Part_name2";
    private static final String TAG_PART_NAME3 = "Part_name3";
    private static final String TAG_PART_NAME4 = "Part_name4";

    private static final String TAG_PART_PHONE = "Part_phone";
    private static final String TAG_PART_EMAIL = "Part_email";
    private static final String TAG_PART_ADDRESS = "Part_address";
    private static final String TAG_PART_BRANCH = "Part_branch";
    private static final String TAG_TEAM_MEMBERS = "Team_members";
    private static final String TAG_PROJECT_NAME = "Project_name";
    private static final String TAG_PROJECT_DOMAIN = "Project_domain";
    private static final String TAG_PROJECT_TYPE = "Project_type";
    private static final String TAG_GUIDE_NAME = "Guide_name";
    private static final String TAG_COLLEGE_NAME = "College_name";
    private static final String TAG_TOTAL_AMOUNT_PAID = "Total_amount_paid";
    private static final String TAG_USERNAME = "Username";

    TextView ptvID_details,ptvID;
    EditText petId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }


    public class GetpData extends AsyncTask<Void, Void, String> {
        TextView ptvID_details;
        String petId;

        Context context;
        public GetpData(Context context, TextView ptvID_details, String petId){
            this.ptvID_details = ptvID_details;
            this.context = context;
            this.petId = petId;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Void... arg) {
            StringBuilder sb = new StringBuilder();
            JSONArray data1 = null;
            String data = null;
            String result = null;
            try {

                String link1 = "http://convene.co.in/appprocess_part.php";
                URL url = new URL(link1);


                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
              data = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(petId, "UTF-8");
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));


                String line = null;

                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

            String json = sb.toString();
            // print the json response in the log
            Log.d("rishi", "json file> " + json);
            if (json != null) {
                try {
                    Log.d("rishi", "in the try");
                    // Getting JSON Array node
                    data1 = new JSONArray(json);
                    Log.d("rishi", "user point array");
                    int len = data1.length();
                    Log.d("rishi", "get array length");
                    for (int i = 0; i < data1.length(); i++) {
                        JSONObject c = data1.getJSONObject(i);
                        String Id = c.getString(TAG_ID);
                        Log.d("rishi", Id);
                        String Part_name = c.getString(TAG_PART_NAME);
                        Log.d("rishi", Part_name);
                        String Part_name2 = c.getString(TAG_PART_NAME2);
                        String Part_name3 = c.getString(TAG_PART_NAME3);
                        String Part_name4 = c.getString(TAG_PART_NAME4);
                        String Part_phone = c.getString(TAG_PART_PHONE);
                        Log.d("rishi", Part_phone);
                        String Part_email = c.getString(TAG_PART_EMAIL);
                        String Part_address = c.getString(TAG_PART_ADDRESS);
                        String Part_branch = c.getString(TAG_PART_BRANCH);
                        String Team_members = c.getString(TAG_TEAM_MEMBERS);
                        String Project_name = c.getString(TAG_PROJECT_NAME);
                        String Project_domain = c.getString(TAG_PROJECT_DOMAIN);
                        String Project_type = c.getString(TAG_PROJECT_TYPE);
                        String Guide_name = c.getString(TAG_GUIDE_NAME);
                        String College_name = c.getString(TAG_COLLEGE_NAME);
                        String Total_amount_paid = c.getString(TAG_TOTAL_AMOUNT_PAID);
                        String Username = c.getString(TAG_USERNAME);

                        result ="\n1. Name : "+Part_name+"\n2. Name : "+Part_name2+"\n3. Name : "+Part_name3+"\n4. Name : "+Part_name4+
                                "\nPhone no.: "+Part_phone+"\nE-mail ID : "+Part_email+"\nAddress: "+Part_address+"\nBranch : "+Part_branch+"\nNo of team members : "+Team_members+"\nProject name : "+Project_name+"\nProject domain : "+Project_domain+"\nProject type : "+Project_type+"\nGuide name : "+Guide_name+"\nCollege name : "+College_name+"\nTotal amount paid : "+Total_amount_paid+"\nRegistrant : "+Username;
                    }
                }
                catch (JSONException e) {
                    Log.d("rishi", "in the catch");
                    e.printStackTrace();
                }
            } else {
                Log.d("rishi", "Didn't receive any data from server!");
            }
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("rishi", "in the postexecute");
            this.ptvID_details.setText(result);

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onPart(View view){
        Log.d("rishi", "in onPart");
        ptvID_details = (TextView) findViewById(R.id.ptvID_details);
        ptvID = (TextView) findViewById(R.id.ptvId);
        petId = (EditText) findViewById(R.id.petId);
        Toast.makeText(getApplicationContext(), "Submitting", Toast.LENGTH_SHORT).show();
        this.ptvID.setText("ID Details:");
        String petId1 = petId.getText().toString();
        new GetpData(this,ptvID_details,petId1).execute();
    }

}

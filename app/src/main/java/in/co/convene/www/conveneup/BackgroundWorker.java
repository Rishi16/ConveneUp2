package in.co.convene.www.conveneup;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
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
import java.util.HashMap;


import javax.crypto.Cipher;

/**
 * Created by Rishikesh on 22-Jan-16.
 */
public class BackgroundWorker extends AsyncTask<String,String,String> {
    private static final String TAG = "Convene";
    Context loginContext, regSuccessContext, viewContext;

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

    TextView tvIncorrect;
    String Choice;
    SessionManager session;
    String username;
    LoginActivity loginActivity;
    View view;
    EditText etPart_name; EditText etPart_phone; EditText etPart_email; EditText etPart_address; EditText etPart_branch; EditText etTeam_members; EditText etProject_name; EditText etProject_domain; EditText etProject_type; EditText etGuide_name; EditText etCollege_name; EditText etTotal_amount_paid; EditText etPart_name2; EditText etPart_name3; EditText etPart_name4;

    BackgroundWorker(LoginActivity loginActivity, Context context, SessionManager session, TextView tvIncorrect, String Choice, String username) {
        this.loginContext = context;
        this.Choice = Choice;
        Log.d("rishi", this.Choice);
        this.tvIncorrect = tvIncorrect;
        this.session = session;
        this.loginActivity = loginActivity;
        this.username=username;

    }

    BackgroundWorker(Context context, String Choice,SessionManager session) {
        this.Choice = Choice;
        this.regSuccessContext = context;
        this.viewContext = context;
        this.session = session;
        Log.d("rishi", this.Choice);
    }
    BackgroundWorker(Context context, String Choice,SessionManager session,EditText etPart_name, EditText etPart_phone, EditText etPart_email, EditText etPart_address, EditText etPart_branch, EditText etTeam_members, EditText etProject_name, EditText etProject_domain, EditText etProject_type, EditText etGuide_name, EditText etCollege_name, EditText etTotal_amount_paid, EditText etPart_name2, EditText etPart_name3, EditText etPart_name4,View view) {
       Log.d("rishi","in backgroundw constructer");
        this.Choice = Choice;
        this.regSuccessContext = context;
        this.viewContext = context;
        this.session = session;
        Log.d("rishi", this.Choice);
        this.etPart_name = etPart_name;
        this.etPart_name2 = etPart_name2;
        this.etPart_name3 = etPart_name3;
        this.etPart_name4 = etPart_name4;
        this.etPart_phone = etPart_phone;
        this.etPart_email = etPart_email;
        this.etPart_address = etPart_address;
        this.etPart_branch = etPart_branch;
        this.etTeam_members = etTeam_members;
        this.etProject_name = etProject_name;
        this.etProject_domain = etProject_domain;
        this.etProject_type = etProject_type;
        this.etGuide_name = etGuide_name;
        this.etCollege_name = etCollege_name;
        this.etTotal_amount_paid = etTotal_amount_paid;
        this.view=view;
    }


    @Override
    protected String doInBackground(String... params) {
        try {
            Log.d("rishi", "Doinbg");
            StringBuilder sb = new StringBuilder();

            if (this.Choice.equals("Login")) {
                String username = (String) params[0];
                String password = (String) params[1];

                String link = "http://convene.co.in/phonelogin.php";
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
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
            } else if (this.Choice.equals("Submit")) {
                Log.d("rishi", "submit");
                HashMap<String, String> user = session.getUserDetails();
                String Username = user.get(SessionManager.KEY_NAME);
                Log.d("rishi","Session Username: "+Username);
                String Part_name = (String) params[0];
                String Part_phone = (String) params[1];
                String Part_email = (String) params[2];
                String Part_address = (String) params[3];
                String Part_branch = (String) params[4];
                String Team_members = (String) params[5];
                String Project_name = (String) params[6];
                String Project_domain = (String) params[7];
                String Project_type = (String) params[8];
                String Guide_name = (String) params[9];
                String College_name = (String) params[10];
                String Total_amount_paid = (String) params[11];
                String Part_name2 = (String) params[12];
                String Part_name3 = (String) params[13];
                String Part_name4 = (String) params[14];
                String link = "http://convene.co.in/appprocess_add.php";

                String data = URLEncoder.encode("Part_name", "UTF-8") + "=" + URLEncoder.encode(Part_name, "UTF-8");
                data += "&" + URLEncoder.encode("Part_name2", "UTF-8") + "=" + URLEncoder.encode(Part_name2, "UTF-8");
                data += "&" + URLEncoder.encode("Part_name3", "UTF-8") + "=" + URLEncoder.encode(Part_name3, "UTF-8");
                data += "&" + URLEncoder.encode("Part_name4", "UTF-8") + "=" + URLEncoder.encode(Part_name4, "UTF-8");
                data += "&" + URLEncoder.encode("Part_phone", "UTF-8") + "=" + URLEncoder.encode(Part_phone, "UTF-8");
                data += "&" + URLEncoder.encode("Part_email", "UTF-8") + "=" + URLEncoder.encode(Part_email, "UTF-8");
                data += "&" + URLEncoder.encode("Part_address", "UTF-8") + "=" + URLEncoder.encode(Part_address, "UTF-8");
                data += "&" + URLEncoder.encode("Part_branch", "UTF-8") + "=" + URLEncoder.encode(Part_branch, "UTF-8");
                data += "&" + URLEncoder.encode("Team_members", "UTF-8") + "=" + URLEncoder.encode(Team_members, "UTF-8");
                data += "&" + URLEncoder.encode("Project_name", "UTF-8") + "=" + URLEncoder.encode(Project_name, "UTF-8");
                data += "&" + URLEncoder.encode("Project_domain", "UTF-8") + "=" + URLEncoder.encode(Project_domain, "UTF-8");
                data += "&" + URLEncoder.encode("Project_type", "UTF-8") + "=" + URLEncoder.encode(Project_type, "UTF-8");
                data += "&" + URLEncoder.encode("Guide_name", "UTF-8") + "=" + URLEncoder.encode(Guide_name, "UTF-8");
                data += "&" + URLEncoder.encode("College_name", "UTF-8") + "=" + URLEncoder.encode(College_name, "UTF-8");
                data += "&" + URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(Username, "UTF-8");
                data += "&" + URLEncoder.encode("Total_amount_paid", "UTF-8") + "=" + URLEncoder.encode(Total_amount_paid, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                Log.d("rishi", "submitend");
            }else if (this.Choice.equals("View")) {

                String link = "http://convene.co.in/appprocess_view.php";
                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;

                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
            }else if (this.Choice.equals("Update")) {
                Log.d("rishi", "update");
                HashMap<String, String> user = session.getUserDetails();
                String Username = user.get(SessionManager.KEY_NAME);
                String Part_name = (String) params[0];
                String Part_phone = (String) params[1];
                String Part_email = (String) params[2];
                String Part_address = (String) params[3];
                String Part_branch = (String) params[4];
                String Team_members = (String) params[5];
                String Project_name = (String) params[6];
                String Project_domain = (String) params[7];
                String Project_type = (String) params[8];
                String Guide_name = (String) params[9];
                String College_name = (String) params[10];
                String Total_amount_paid = (String) params[11];
                String Check_Id = (String) params[12];
                String Part_name2 = (String) params[13];
                String Part_name3 = (String) params[14];
                String Part_name4 = (String) params[15];

                String link = "http://convene.co.in/appprocess_update.php";

                String data = URLEncoder.encode("Part_name", "UTF-8") + "=" + URLEncoder.encode(Part_name, "UTF-8");
                data += "&" + URLEncoder.encode("Part_name2", "UTF-8") + "=" + URLEncoder.encode(Part_name2, "UTF-8");
                data += "&" + URLEncoder.encode("Part_name3", "UTF-8") + "=" + URLEncoder.encode(Part_name3, "UTF-8");
                data += "&" + URLEncoder.encode("Part_name4", "UTF-8") + "=" + URLEncoder.encode(Part_name4, "UTF-8");
                data += "&" + URLEncoder.encode("Part_phone", "UTF-8") + "=" + URLEncoder.encode(Part_phone, "UTF-8");
                data += "&" + URLEncoder.encode("Part_email", "UTF-8") + "=" + URLEncoder.encode(Part_email, "UTF-8");
                data += "&" + URLEncoder.encode("Part_address", "UTF-8") + "=" + URLEncoder.encode(Part_address, "UTF-8");
                data += "&" + URLEncoder.encode("Part_branch", "UTF-8") + "=" + URLEncoder.encode(Part_branch, "UTF-8");
                data += "&" + URLEncoder.encode("Team_members", "UTF-8") + "=" + URLEncoder.encode(Team_members, "UTF-8");
                data += "&" + URLEncoder.encode("Project_name", "UTF-8") + "=" + URLEncoder.encode(Project_name, "UTF-8");
                data += "&" + URLEncoder.encode("Project_domain", "UTF-8") + "=" + URLEncoder.encode(Project_domain, "UTF-8");
                data += "&" + URLEncoder.encode("Project_type", "UTF-8") + "=" + URLEncoder.encode(Project_type, "UTF-8");
                data += "&" + URLEncoder.encode("Guide_name", "UTF-8") + "=" + URLEncoder.encode(Guide_name, "UTF-8");
                data += "&" + URLEncoder.encode("College_name", "UTF-8") + "=" + URLEncoder.encode(College_name, "UTF-8");
                data += "&" + URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(Username, "UTF-8");
                data += "&" + URLEncoder.encode("Total_amount_paid", "UTF-8") + "=" + URLEncoder.encode(Total_amount_paid, "UTF-8");
                data += "&" + URLEncoder.encode("Check_Id", "UTF-8") + "=" + URLEncoder.encode(Check_Id, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
            }else if (Choice.equals("Check")){
                String CId = (String) params[0];
                Log.d("rishi","in Cid : "+ CId);

                 sb = new StringBuilder();
                try {
                    Log.d("rishi","in onprogressuodate try  Cid: "+ CId);
                    String link = "http://convene.co.in/appprocess_check.php";
                    HashMap<String, String> user = session.getUserDetails();
                    String Username = user.get(SessionManager.KEY_NAME);
                    String data = URLEncoder.encode("CId", "UTF-8") + "=" + URLEncoder.encode(CId, "UTF-8");
                    data += "&" + URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(Username, "UTF-8");
                    Log.d("rishi","in onprogressuodate data : "+ data);

                    URL url =  new URL(link);
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);

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
                    String exception = new String("Exception: " + e.getMessage());
                    Log.d("rishi", "On progressupdate Exception : "+exception);
                }
            }

            return sb.toString();
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        String Check = this.Choice;
        Log.d("rishi", "in post execute");
        if (Check.equals("Login")) {
            if (result.equals("212")) {
                Log.d("rishi", "in login" );
                session.createLoginSession(username);
                Intent intent = new Intent(loginContext, MainActivity.class);
                loginContext.startActivity(intent);
                loginActivity.finish();
            } else {
                this.tvIncorrect.setText("Incorrect Username/Password");
            }
        } else if (Check.equals("Submit")) {
            Log.d("rishi", "in form check");
            Log.d("rishi", "result " + result);
            Intent intent = new Intent(regSuccessContext, RegSuccessActivity.class);
            intent.putExtra("result", result);
            regSuccessContext.startActivity(intent);
        }
        else if (Check.equals("Update"))
        {
            Log.d("rishi", "in form check");
            Log.d("rishi", "result " + result);
            Intent intent = new Intent(regSuccessContext, RegSuccessActivity.class);
            intent.putExtra("result", result);
            regSuccessContext.startActivity(intent);
        }
        else if(Check.equals("Check")){
            JSONArray dataArray;
            String json = result;
            Log.d("rishi", "json file> " + json);
            if (json != null) {
                try {
                    Log.d("rishi", "in the try");
                    // Getting JSON Array node
                    dataArray = new JSONArray(json);
                    Log.d("rishi", "user point array");
                    int len = dataArray.length();
                    Log.d("rishi", "get array length");
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject c = dataArray.getJSONObject(i);
                        String Id = c.getString(TAG_ID);
                        Log.d("rishi", Id);
                        final String Part_name = c.getString(TAG_PART_NAME);
                        final String Part_name2 = c.getString(TAG_PART_NAME2);
                        final String Part_name3 = c.getString(TAG_PART_NAME3);
                        final String Part_name4 = c.getString(TAG_PART_NAME4);
                        Log.d("rishi", Part_name);
                        final String Part_phone = c.getString(TAG_PART_PHONE);
                        Log.d("rishi", Part_phone);
                        final String Part_email = c.getString(TAG_PART_EMAIL);
                        final String Part_address = c.getString(TAG_PART_ADDRESS);
                        final String Part_branch = c.getString(TAG_PART_BRANCH);
                        final String Team_members = c.getString(TAG_TEAM_MEMBERS);
                        final String Project_name = c.getString(TAG_PROJECT_NAME);
                        final String Project_domain = c.getString(TAG_PROJECT_DOMAIN);
                        final String Project_type = c.getString(TAG_PROJECT_TYPE);
                        final String Guide_name = c.getString(TAG_GUIDE_NAME);
                        final String College_name = c.getString(TAG_COLLEGE_NAME);
                        final String Total_amount_paid = c.getString(TAG_TOTAL_AMOUNT_PAID);
                        final String cUsername = c.getString(TAG_USERNAME);
                        HashMap<String, String> user = session.getUserDetails();
                        String Username = user.get(SessionManager.KEY_NAME);
                        if(Username.equals(cUsername)) {
                            Log.d("rishi","in if of username check: "+Username+cUsername);

                            Log.d("rishi","Setting Edittext");
                            etPart_name.setText(Part_name);
                            etPart_name2.setText(Part_name2);
                            etPart_name3.setText(Part_name3);
                            etPart_name4.setText(Part_name4);
                            etPart_phone.setText(Part_phone);
                            etPart_email.setText(Part_email);
                            etPart_address.setText(Part_address);
                            etPart_branch.setText(Part_branch);
                            etTeam_members.setText(Team_members);
                            etProject_name.setText(Project_name);
                            etProject_domain.setText(Project_domain);
                            etProject_type.setText(Project_type);
                            etGuide_name.setText(Guide_name);
                            etCollege_name.setText(College_name);
                            etTotal_amount_paid.setText(Total_amount_paid);
                        }
                        else{
                            Toast.makeText(viewContext, "You don't have the rights to edit this entry.", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                catch (JSONException e) {
                    Log.d("rishi", "in the catch");
                    e.printStackTrace();
                }
            } else {
                Log.d("rishi", "Didn't receive any data from server!");
            }
        }
        }


        @Override
        protected void onProgressUpdate (String...params){
            super.onProgressUpdate(params);


    }
}
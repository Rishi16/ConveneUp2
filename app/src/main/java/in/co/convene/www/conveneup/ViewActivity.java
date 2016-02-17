package in.co.convene.www.conveneup;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewActivity extends ListActivity {

    private static final String TAG_ID = "ID";
    private static final String TAG_PART_NAME = "Part_name";
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
    String Choice;


    JSONArray data = null;
    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("rishi", "in oncreate");
        setContentView(R.layout.activity_view);
        Bundle bundle = getIntent().getExtras();
        String Choice = bundle.getString("Choice");

        Log.d("rishi", Choice);
        new GetData(Choice,this).execute();

}
    public class GetData extends AsyncTask<Void, Void, String> {

        String Choice;
        Context context;
        public GetData(String Choice, Context context){
            this.Choice=Choice;
            this.context = context;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Void... arg) {
            StringBuilder sb = new StringBuilder();
            try {

                String link1 = "http://www.YOURLINK.com/view.php";
                String link2 = "http://www.YOURLINK.com/registrant.php";
                URL url = null;
                if(Choice.equals("All")) {
                    url = new URL(link1);
                }
                else if(Choice.equals("Registrant")) {
                    url = new URL(link2);
                }

                SessionManager session = new SessionManager(context);
                HashMap<String, String> user = session.getUserDetails();
                String Username = user.get(SessionManager.KEY_NAME);
                Log.d("rishi", "Username f: " +Username);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                String data = URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(Username, "UTF-8");
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
                    data = new JSONArray(json);
                    Log.d("rishi", "user point array");
                    int len = data.length();
                    Log.d("rishi", "get array length");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject c = data.getJSONObject(i);
                        String Id = c.getString(TAG_ID);
                        Log.d("rishi", Id);
                        String Part_name = c.getString(TAG_PART_NAME);
                        Log.d("rishi", Part_name);
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

                        //  hashmap for single match
                        HashMap<String, String> rows = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        if(!Id.equals("0")) {
                            rows.put(TAG_ID, Id);
                            rows.put(TAG_PART_NAME, Part_name);
                            rows.put(TAG_PART_PHONE, Part_phone);
                            rows.put(TAG_PART_EMAIL, Part_email);
                            rows.put(TAG_PART_ADDRESS, Part_address);
                            rows.put(TAG_PART_BRANCH, Part_branch);
                            rows.put(TAG_TEAM_MEMBERS, Team_members);
                            rows.put(TAG_PROJECT_NAME, Project_name);
                            rows.put(TAG_PROJECT_DOMAIN, Project_domain);
                            rows.put(TAG_PROJECT_TYPE, Project_type);
                            rows.put(TAG_GUIDE_NAME, Guide_name);
                            rows.put(TAG_COLLEGE_NAME, College_name);
                            rows.put(TAG_TOTAL_AMOUNT_PAID, Total_amount_paid);
                            rows.put(TAG_USERNAME, Username);
                            dataList.add(rows);
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
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("rishi", "in the postexecute");
            ListAdapter adapter = new SimpleAdapter(
                    ViewActivity.this, dataList,
                    R.layout.list_item, new String[] {
                    TAG_ID, TAG_PART_NAME, TAG_PART_PHONE,TAG_USERNAME,TAG_TOTAL_AMOUNT_PAID, TAG_PART_EMAIL,TAG_PART_ADDRESS,TAG_PART_BRANCH,TAG_TEAM_MEMBERS,TAG_PROJECT_NAME,TAG_PROJECT_DOMAIN,TAG_PROJECT_TYPE,TAG_GUIDE_NAME,TAG_COLLEGE_NAME
            }
                    , new int[] {
                    R.id.tvId,R.id.tvName,
                    R.id.tvPart_phone, R.id.tvUsername, R.id.tvTotal_amount_paid,
            }
            );
            Log.d("rishi", "Setting list");
            setListAdapter(adapter);
            ListView list = getListView();
            setListViewHeightBasedOnChildren(list);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private  void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
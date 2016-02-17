package in.co.convene.www.conveneup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

public class RegSuccessActivity extends AppCompatActivity {
    TextView tvRegCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_success);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvRegCheck = (TextView) findViewById(R.id.tvRegCheck);
        Log.d("rishi", "in regcheck");
        Bundle bundle = getIntent().getExtras();
        String result = bundle.getString("result");
        Log.d("rishi",result);

        if(result.equals("212")){
            Log.d("rishi","in succes");
            tvRegCheck.setText("Registered successfully.");
            Log.d("rishi", " after in succes");
        }
        else if(result.equals("213"))
        {
            Log.d("rishi","in update");
            tvRegCheck.setText("Updated successfully.");
            Log.d("rishi", " after in Update");
        }
        else if(result.equals("312"))
        {
            Log.d("rishi","in update");
            tvRegCheck.setText("You do not have the rights to edit this entry.");
            Log.d("rishi", " after in Update");
        }
        else
        {
            this.tvRegCheck.setText(result);
        }


    }

}

package in.co.convene.www.conveneup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment implements  View.OnClickListener  {

    EditText etcId =null;
    EditText etPart_name = null;
    EditText etPart_name2 = null;
    EditText etPart_name3 = null;
    EditText etPart_name4 = null;
    EditText etPart_phone = null;
    EditText etPart_email = null;
    EditText etPart_address = null;
    EditText etPart_branch = null;
    EditText etTeam_members = null;
    EditText etProject_name = null;
    EditText etProject_domain = null;
    EditText etProject_type = null;
    EditText etGuide_name = null;
    EditText etCollege_name = null;
    EditText etTotal_amount_paid = null;
    Button bSubmit,bClear,bCheck;
    AutoCompleteTextView autoCompleteTextView;
    String[] Types;
    SessionManager session;

    public UpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("rishi","in on ACreated");
        super.onActivityCreated(savedInstanceState);
        Log.d("rishi", "end in on ACreated");
        etcId = (EditText) getActivity().findViewById(R.id.uetcId);

        etPart_name = (EditText) getActivity().findViewById(R.id.uetPart_name);
        etPart_name2 = (EditText) getActivity().findViewById(R.id.uetPart_name2);
        etPart_name3 = (EditText) getActivity().findViewById(R.id.uetPart_name3);
        etPart_name4 = (EditText) getActivity().findViewById(R.id.uetPart_name4);
        etPart_phone = (EditText) getActivity().findViewById(R.id.uetPart_phone);
        etPart_email = (EditText) getActivity().findViewById(R.id.uetPart_email);
        etPart_address = (EditText) getActivity().findViewById(R.id.uetPart_address);
        etPart_branch = (EditText) getActivity().findViewById(R.id.uetPart_branch);
        etTeam_members = (EditText) getActivity().findViewById(R.id.uetTeam_members);
        etProject_name = (EditText) getActivity().findViewById(R.id.uetProject_name);
        etProject_domain = (EditText) getActivity().findViewById(R.id.uetProject_domain);
        etProject_type = (EditText) getActivity().findViewById(R.id.uetProject_type);
        etGuide_name = (EditText) getActivity().findViewById(R.id.uetGuide_name);
        etCollege_name = (EditText) getActivity().findViewById(R.id.uetCollege_name);
        etTotal_amount_paid = (EditText) getActivity().findViewById(R.id.uetTotal_amount_paid);
        bSubmit = (Button) getActivity().findViewById(R.id.ubSubmit);
        bClear = (Button) getActivity().findViewById(R.id.ubClear);
        bCheck = (Button) getActivity().findViewById(R.id.ubCheck);
        bSubmit.setOnClickListener(this);
        bClear.setOnClickListener(this);
        bCheck.setOnClickListener(this);
        session =  new SessionManager(this.getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("rishi","in onCreate");
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        autoCompleteTextView =(AutoCompleteTextView) view.findViewById(R.id.uetProject_type);
        Types = getResources().getStringArray(R.array.project_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,Types);
        autoCompleteTextView.setAdapter(adapter);
        Log.d("rishi", "endin onCreate");
        return view;
    }

    @Override
    public void onClick(final View v) {
        BackgroundWorker backgroundWorker;
        String CId;
        String Choice;
        switch (v.getId()) {

            case R.id.ubSubmit:
                CId = etcId.getText().toString();
                String Part_name = etPart_name.getText().toString();
                String Part_name2 = etPart_name2.getText().toString();
                String Part_name3 = etPart_name3.getText().toString();
                String Part_name4 = etPart_name4.getText().toString();
                String Part_phone = etPart_phone.getText().toString();
                String Part_email = etPart_email.getText().toString();
                String Part_address = etPart_address.getText().toString();
                String Part_branch = etPart_branch.getText().toString();
                String Team_members = etTeam_members.getText().toString();
                String Project_name = etProject_name.getText().toString();
                String Project_domain = etProject_domain.getText().toString();
                String Project_type = etProject_type.getText().toString();
                String Guide_name = etGuide_name.getText().toString();
                String College_name = etCollege_name.getText().toString();
                String Total_amount_paid = etTotal_amount_paid.getText().toString();
                Log.d("rishi", "click");
                bSubmit.setEnabled(false);

                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        v.post(new Runnable() {

                            @Override
                            public void run() {
                                bSubmit.setEnabled(true);
                            }
                        });
                    }
                }, 10000);
                Choice = "Update";
                Toast.makeText(getActivity().getApplicationContext(), "Updating", Toast.LENGTH_SHORT).show();
                backgroundWorker = new BackgroundWorker(this.getContext(), Choice,session);
                backgroundWorker.execute(Part_name, Part_phone, Part_email, Part_address, Part_branch, Team_members, Project_name, Project_domain, Project_type, Guide_name, College_name, Total_amount_paid, CId,Part_name2,Part_name3,Part_name4);
                break;

            case R.id.ubCheck:
                Choice = "Check";
                CId = etcId.getText().toString();
                Toast.makeText(getActivity().getApplicationContext(), "Checking", Toast.LENGTH_SHORT).show();
                backgroundWorker = new BackgroundWorker(this.getContext(), Choice,session,etPart_name, etPart_phone, etPart_email, etPart_address, etPart_branch, etTeam_members, etProject_name, etProject_domain, etProject_type, etGuide_name, etCollege_name, etTotal_amount_paid,etPart_name2,etPart_name3,etPart_name4,v);
                backgroundWorker.execute(CId);
                break;

            case R.id.ubClear:ViewGroup group = (ViewGroup)getActivity().findViewById(R.id.ullForm);
                for (int i = 0, count = group.getChildCount(); i < count; ++i) {
                    View view = group.getChildAt(i);
                    if (view instanceof EditText) {
                        ((EditText)view).setText("");
                    }
                }
                break;

        }
    }
}

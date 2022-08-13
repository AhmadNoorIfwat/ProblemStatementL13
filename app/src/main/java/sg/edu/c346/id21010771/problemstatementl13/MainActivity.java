package sg.edu.c346.id21010771.problemstatementl13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView lvData;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvData = findViewById(R.id.lvData);
        client = new AsyncHttpClient();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<StudentEnrolment> alStudentEnrolment = new ArrayList<StudentEnrolment>();

        client.get("https://data.gov.sg/api/action/datastore_search?resource_id=4ad866a7-c43a-4645-87fd-fc961c9de78a&limit=10", new JsonHttpResponseHandler() {
            String enrolment;
            String year;

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    JSONObject firstObj = response.getJSONObject("result");
                    JSONArray jsonArrRecords = firstObj.getJSONArray("records");
                    for(int i = 0; i < jsonArrRecords.length(); i++){
                        JSONObject jsonObjRecords = jsonArrRecords.getJSONObject(i);
                        enrolment = jsonObjRecords.getString("enrolment");
                        year = jsonObjRecords.getString("year");
                        StudentEnrolment students = new StudentEnrolment(enrolment, year);
                        alStudentEnrolment.add(students);
                    }
                }
                catch(JSONException e){
                    Log.d("exception", e.toString());
                }

                //display listview
                ArrayAdapter<StudentEnrolment> aaStudentEnrolment = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, alStudentEnrolment);

                lvData.setAdapter(aaStudentEnrolment);
            } //end onSuccess
        });

    } //end onResume
}
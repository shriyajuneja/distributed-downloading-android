package project.com.distributivedownload;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;

import project.com.distributivedownload.models.FirstRequest;

public class MainActivity extends AppCompatActivity {

    private EditText url,numOfRequests;
    private Button download;
    Context context;
    ProgressDialog progressDialog;

    private String requestUrl = "localhost:3000/download";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        url = (EditText) findViewById(R.id.link);
        numOfRequests = (EditText) findViewById(R.id.num_of_requests);
        download = (Button) findViewById(R.id.download);
        progressDialog = new ProgressDialog(context);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!url.getText().toString().contains(" ")&&!numOfRequests.getText().toString().contains(" ")&&!url.getText().toString().equals("")&&!numOfRequests.getText().toString().equals(""))
                {
                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    makeRequest(url.getText().toString(),Integer.parseInt(numOfRequests.getText().toString()));
                    progressDialog.dismiss();
                }
                else
                {
                    if(url.getText().toString().contains(" ")||url.getText().toString().equals(""))
                        url.setError("url cannot contain blank spaces");
                    if(numOfRequests.getText().toString().contains(" ")||numOfRequests.getText().toString().equals(""))
                        numOfRequests.setError("key cannot contain blank spaces");
                }
            }
        });
    }

    private void makeRequest(String url, int n) {
        FirstRequest firstRequest = new FirstRequest(url,n);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, requestUrl,firstRequest, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}

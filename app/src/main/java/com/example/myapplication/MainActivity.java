package com.example.myapplication;

import android.nfc.Tag;
import android.os.Bundle;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    static final ObjectMapper mapper =new ObjectMapper();
    private CardArrayAdapter cardArrayAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("===","on create");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
       final EditText simpleEditText= findViewById(R.id.simpleEditText);
        Button checkButton = findViewById(R.id.displayText);//get the id for button
          listView=(ListView) findViewById(R.id.card_listView);
          listView.addFooterView(new View(this));
          listView.addHeaderView(new View(this));


        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (simpleEditText.getText().toString() != null)//check whether the entered text is not null
                {
                    //Toast.makeText(getApplicationContext(), simpleEditText.getText().toString(), Toast.LENGTH_LONG).show();//display the text that you entered in edit text

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            getResult(simpleEditText.getText().toString());

                        }
                    }).start();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    private void getResult(final String text) {
        System.out.println("username--->"+ text);
        Log.e("Volley","success==>======" );
        // First, we insert the username into the repo url.
        // The repo url is defined in GitHubs API docs (https://developer.github.com/v3/repos/).
        final TextView erroWordView = (TextView) findViewById(R.id.wrong);
        final TextView originalWordView = (TextView) findViewById(R.id.right);
        final CardArrayAdapter cardArrayAdapter = new CardArrayAdapter(getApplicationContext(),R.layout.fragment_first);

        //final TextView explainationView = (TextView) findViewById(R.id.explaination);

        String url ="http://192.168.0.152:8090/grammar/check?text=" +text;
        url =url.replaceAll(" ","%20");

       // http://localhost:8090/grammar/check?text=Lets integrate with scribens.

        // Next, we create a new JsonArrayRequest. This will use Volley to make a HTTP request
        // that expects a JSON Array Response.
        // To fully understand this, I'd recommend readng the office docs: https://developer.android.co     m/training/volley/index.html

        // Request a string response from the provided URL.
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        StringBuilder builder  = new StringBuilder();
                        Map<String, Explain> offsetMap=null;
                        if(response!=null){

                                try {
                                    String[] words = text.split(" ");

                                    // JSONObject offsetMap = (JSONObject) response.get("offsetMap");
                                    CorrectedSentence correctedSentence = mapper.readValue(response, CorrectedSentence.class);
                                    offsetMap = correctedSentence.getExplainationMap();
                                    builder.append(offsetMap);

                                } catch (JsonParseException e) {
                                    e.printStackTrace();
                                } catch (JsonMappingException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                for(Explain explain:offsetMap.values()){
                                   cardArrayAdapter.add(explain);

                                }

                            Log.e("Volley","success==>" +response);
                        }
                        listView.setAdapter(cardArrayAdapter);

                        //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // textView.setText("That didn't work!");
                Log.e("Volley","on create" +error);
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add the request we just defined to our request queue.
        // The request queue will automatically handle the request as soon as it can.
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}

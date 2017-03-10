package com.example.imagine.javadevelopers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<User> arrayList;
    ListView lv;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listView);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);

                TextView userId=(TextView) view.findViewById(R.id.txtName);
                TextView profileUrl=(TextView) view.findViewById(R.id.profileUrl);
                TextView imageUrl=(TextView) view.findViewById(R.id.imageUrl);

                String img_url=imageUrl.getText().toString();
                String username=userId.getText().toString();
                String user_url=profileUrl.getText().toString();


                //Create the bundle
                Bundle bundle = new Bundle();

                //Add your data to bundle
                bundle.putString("img_url", img_url);
                bundle.putString("user_name", username);
                bundle.putString("user_url", user_url);

                //Add the bundle to the intent
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("https://api.github.com/search/users?q=java+location:lagos");
            }
        });
    }
    class ReadJSON extends AsyncTask<String,Integer,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray = jsonObject.getJSONArray("items");

                for(int i =0 ; i<jsonArray.length(); i++)   {
                    JSONObject userObject = jsonArray.getJSONObject(i);
                    arrayList.add(new User(
                            userObject.getString("avatar_url"),
                            userObject.getString("login"),
                            userObject.getString("id"),
                            userObject.getString("html_url")

                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CustomListAdapter adapter = new CustomListAdapter(
                    getApplicationContext(), R.layout.custom_list_layout,arrayList
            );
            lv.setAdapter(adapter);
        }
    }
    private static String readURL (String theUrl)  {
        StringBuilder content = new StringBuilder();
        try {
            //create a url object
            URL url= new URL(theUrl);
            //create a url connection
            URLConnection urlConnection=url.openConnection();
            //wrap the url in a buffered reader
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            //read from the urlconnection via the buffered reader
            while ((line=bufferedReader.readLine())!=null){
                content.append(line+'\n');
            }
            bufferedReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();

        }
        return content.toString();
    }
}

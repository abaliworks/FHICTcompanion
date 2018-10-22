package com.example.ab.fhictcompanion;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ab.fhictcompanion.Model.People;
import com.example.ab.fhictcompanion.Model.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PeopleActivity extends AppCompatActivity {
    private JSONTask mJsonTask;
    private Button mBtnSearch;
    private EditText mEtQuery;
    private People mPeople;
    private ImageView mImageView;
    private TextView mDisplayName, mId, mOffice, mEmail, mTelephone;
    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        // linking
        mDialog = new ProgressDialog(PeopleActivity.this);

        mBtnSearch = findViewById(R.id.btnSearch);
        mEtQuery = findViewById(R.id.etsearch);

        mImageView = findViewById(R.id.peepImageView);
        mDisplayName = findViewById(R.id.peepDisplayName);
        mId = findViewById(R.id.peepFhictId);
        mOffice = findViewById(R.id.peepOffice);
        mTelephone = findViewById(R.id.peepTelephoneNumber);
        mEmail = findViewById(R.id.peepEmail);

        // Event handler
        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mJsonTask = new JSONTask();
                mJsonTask.execute();
            }
        });

    }

    public class JSONTask extends AsyncTask<Void, Void, Void> {
        public String stringUrlPhoto,displayName,fhictId,office,email,telephone;
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String searchQuery = mEtQuery.getText().toString();
                URL url = new URL("https://api.fhict.nl/people/search/" + searchQuery);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("Authorization", "Bearer " + StartActivity.token);
                connection.connect();

                InputStream is = new BufferedInputStream(connection.getInputStream());
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                String response = sb.toString();
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonobject = jsonArray.getJSONObject(i);
                    stringUrlPhoto = jsonobject.getString("photo");
                    displayName = jsonobject.getString("displayName");
                    fhictId = jsonobject.getString("id");
                    office = jsonobject.getString("office");
                    email = jsonobject.getString("mail");
                    telephone = jsonobject.getString("telephoneNumber");
                }

                URL urlPhoto = new URL(stringUrlPhoto);
                HttpURLConnection connectionPhoto = (HttpURLConnection) urlPhoto.openConnection();
                connectionPhoto.setRequestProperty("Accept", "application/json");
                connectionPhoto.setRequestProperty("Authorization", "Bearer " + StartActivity.token);
                connectionPhoto.connect();

                InputStream isPhoto = connectionPhoto.getInputStream();
                Bitmap b = BitmapFactory.decodeStream(isPhoto);

                mPeople = new People(b, fhictId, displayName, email, office, telephone);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialog.setTitle("Loading");
            mDialog.setMessage("Please wait...");
            mDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mDialog.dismiss();

            Bitmap photo = mPeople.getPhoto();

            mImageView.setImageBitmap(photo);
            mDisplayName.setText(mPeople.getDisplayName());
            mId.setText(mPeople.getId());
            mEmail.setText(mPeople.getMail());
            mOffice.setText(mPeople.getOffice());
            mTelephone.setText(mPeople.getTelephoneNumber());
        }

    }
}

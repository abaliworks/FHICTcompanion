package com.example.ab.fhictcompanion;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ab.fhictcompanion.Model.Profile;

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


public class AboutMeActivity extends AppCompatActivity {
    private Profile mProfile;
    private ImageView mImageView;
    private TextView mDisplayName, mFhictId, mStudentId, mClassTitle, mDepartment, mEmail;
    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);

        mDialog = new ProgressDialog(AboutMeActivity.this);

        mImageView = (ImageView) findViewById(R.id.image);
        mDisplayName = (TextView) findViewById(R.id.displayName);
        mFhictId = (TextView) findViewById(R.id.fhictId);
        mStudentId = (TextView) findViewById(R.id.studentId);
        mClassTitle = (TextView) findViewById(R.id.classTitle);
        mDepartment = (TextView) findViewById(R.id.department);
        mEmail = (TextView) findViewById(R.id.email);

        new JSONTask().execute();
    }
    public class JSONTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("https://api.fhict.nl/people/me");
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

                JSONObject json = new JSONObject(response);

                String stringUrlPhoto = json.getString("photo");
                String displayName = json.getString("displayName");
                String fhictId = json.getString("id");
                String studentId = json.getString("employeeId");
                String classTitle = json.getString("personalTitle");
                String department = json.getString("department");
                String email = json.getString("mail");

                URL urlPhoto = new URL(stringUrlPhoto);
                HttpURLConnection connectionPhoto = (HttpURLConnection) urlPhoto.openConnection();
                connectionPhoto.setRequestProperty("Accept", "application/json");
                connectionPhoto.setRequestProperty("Authorization", "Bearer " + StartActivity.token);
                connectionPhoto.connect();

                InputStream isPhoto = connectionPhoto.getInputStream();
                Bitmap b = BitmapFactory.decodeStream(isPhoto);

                mProfile = new Profile(b, displayName, fhictId, studentId, classTitle, department, email);

            }
            catch (MalformedURLException e) {
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
            Bitmap photo = mProfile.getPhoto();
            Bitmap outputPhoto = Bitmap.createBitmap(photo.getWidth(),
                    photo.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(outputPhoto);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, photo.getWidth(), photo.getHeight());

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            canvas.drawCircle(photo.getWidth() / 2, photo.getHeight() / 2,
                    photo.getWidth() / 2, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(photo, rect, rect, paint);
            //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);


            mImageView.setImageBitmap(outputPhoto);
            mDisplayName.setText(mProfile.getDisplayName());
            mFhictId.setText(mProfile.getFhictID());
            mStudentId.setText(mProfile.getStudentID());
            mClassTitle.setText(mProfile.getClassTitle());
            mDepartment.setText(mProfile.getDepartment());
            mEmail.setText(mProfile.getMail());
        }
    }
}

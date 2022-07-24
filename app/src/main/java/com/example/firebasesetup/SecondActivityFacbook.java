package com.example.firebasesetup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

public class SecondActivityFacbook extends AppCompatActivity {
    TextView name, email;
    Button signOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_facbook);
        name = (TextView)findViewById(R.id.name);
        email = (TextView)findViewById(R.id.email);
        // to access user details
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try{
                            String fullname = object.getString("name");
                            name.setText(fullname);
                            String id = object.getString("id");
                            email.setText(id);
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void signout(View view){
        LoginManager.getInstance().logOut();
        startActivity(new Intent(SecondActivityFacbook.this, MainActivity.class));
        finish();
    }
}
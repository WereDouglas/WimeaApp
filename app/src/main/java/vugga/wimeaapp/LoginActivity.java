package vugga.wimeaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends ActionBarActivity {

    Button Login;
    TextView Email, Password;
    boolean canEnter=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = (TextView) findViewById(R.id.email);
        Password = (TextView) findViewById(R.id.password);

        SharedPreferences myPrefs = getSharedPreferences(util.PREFS_NAME, 0);
        String name =   myPrefs.getString("name", "").toString();
        String station =   myPrefs.getString("station", "").toString();
        if(!(name.toString().equals("") && station.toString().equals("")) ) {

            Intent startLocation = new Intent(LoginActivity.this, StartActivity.class);
            startActivity(startLocation);
            finish();

        }

        Login = (Button) findViewById(R.id.login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (haveNetworkConnection()) {
                      Login();
                    }
                else{
                    Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
                }
                //finish();
            }
        });


    }

    private boolean Login() {

        Login.setVisibility(View.GONE);
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        SharedPreferences settings = getSharedPreferences(util.PREFS_NAME, 0);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("password", password);
        //   Toast.makeText(getApplicationContext(), params.toString(), Toast.LENGTH_LONG).show();
        client.post(util.Url + "user/login", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int in, Header[] headers, byte[] bytes) {
                System.out.println(headers.toString());
                String ret = new String(bytes);
                try {
                    JSONObject j = new JSONObject(ret);
                    Toast.makeText(getApplicationContext(), j.get("status").toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), j.get("info").toString(), Toast.LENGTH_LONG).show();

                    if (j.get("status").toString().equals("true")) {
                        String  name = j.get("name").toString();
                        String  station = j.get("station").toString();
                        SharedPreferences myPrefs = getApplicationContext().getSharedPreferences(util.PREFS_NAME, 0);

                        SharedPreferences.Editor editor = myPrefs.edit();
                        editor.putBoolean("hasLoggedIn", true);
                        editor.putString("name", name);
                        editor.putString("station", station);
                        editor.apply();
                        editor.commit();

                        try {

                            Intent startLocation = new Intent(LoginActivity.this, LoginActivity.class);
                            startActivity(startLocation);
                            finish();

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_LONG).show();
                            Login.setVisibility(View.VISIBLE);
                        }

                    } else {

                        Toast.makeText(getApplicationContext(), "invalid user: " + "", Toast.LENGTH_LONG).show();

                        Login.setVisibility(View.VISIBLE);
                    }
                    //  Toast.makeText(getApplicationContext(), "registration successful", Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    // TODO Auto-generated catch block

                    System.out.print("data sync Error" + e);

                    System.out.print(ret);
                    e.printStackTrace();
                    Login.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {
// TODO Auto-generated method stub

                if (statusCode == 404) {
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                    Login.setVisibility(View.VISIBLE);
                } else if (statusCode == 500) {
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                    Login.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getApplicationContext(), "Error:" + statusCode + throwable.getMessage(), Toast.LENGTH_LONG).show();
                    Login.setVisibility(View.VISIBLE);
                }
            }
        });

        return canEnter;
    }




    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

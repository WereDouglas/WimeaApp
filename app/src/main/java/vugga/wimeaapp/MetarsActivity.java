package vugga.wimeaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import vugga.wimeaapp.helper.DatabaseHelper;
import vugga.wimeaapp.model.Metar;


public class MetarsActivity extends ActionBarActivity {

    TableLayout  tableLayout;
    DatabaseHelper db;

    String name ;
    String station ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metars);
        SharedPreferences myPrefs = getSharedPreferences(util.PREFS_NAME, 0);
        name =   myPrefs.getString("name", "").toString();
         station =   myPrefs.getString("station", "").toString();

        db = new DatabaseHelper(getApplicationContext());
          init();

        if (haveNetworkConnection()) {
            Toast.makeText(getApplicationContext(),"uploading data to server", Toast.LENGTH_LONG).show();
            Online();
        }
        else {
            Toast.makeText(getApplicationContext(),"no connection", Toast.LENGTH_LONG).show();

        }
    }

        public void init() {

            TableLayout stk = (TableLayout) findViewById(R.id.tableLayout);
            TableRow tbrow0 = new TableRow(this);
            TextView tv0 = new TextView(this);
            tv0.setText("METAR/SPECI ");
            tv0.setTextColor(Color.WHITE);
            tbrow0.addView(tv0);


            TextView tv1 = new TextView(this);
            tv1.setText("CCCC ");
            tv1.setTextColor(Color.WHITE);
            tbrow0.addView(tv1);

            TextView tv2 = new TextView(this);
            tv2.setText("YYGGggz ");
            tv2.setTextColor(Color.WHITE);
            tbrow0.addView(tv2);

            TextView tv3 = new TextView(this);
            tv3.setText("Dddff/fm/fm");
            tv3.setTextColor(Color.WHITE);
            tbrow0.addView(tv3);

            TextView tv4 = new TextView(this);
            tv4.setText("WW or CAVOCK");
            tv4.setTextColor(Color.WHITE);
            tbrow0.addView(tv4);


            TextView tv5 = new TextView(this);
            tv5.setText("W1W1");
            tv5.setTextColor(Color.WHITE);
            tbrow0.addView(tv5);

            TextView tv6 = new TextView(this);
            tv6.setText("N1CCh1h1NnCChhhNhCChhh");
            tv6.setTextColor(Color.WHITE);
            tbrow0.addView(tv6);

            TextView tv7 = new TextView(this);
            tv7.setText("air temperature");
            tv7.setTextColor(Color.WHITE);
            tbrow0.addView(tv7);

            TextView tv8 = new TextView(this);
            tv8.setText("Dew point");
            tv8.setTextColor(Color.WHITE);
            tbrow0.addView(tv8);

            TextView tv9 = new TextView(this);
            tv9.setText("Wet bulb");
            tv9.setTextColor(Color.WHITE);
            tbrow0.addView(tv9);

            TextView tv10 = new TextView(this);
            tv10.setText("TT/TdTd");
            tv10.setTextColor(Color.WHITE);
            tbrow0.addView(tv10);

            TextView tv11 = new TextView(this);
            tv11.setText("QNH(hpa)");
            tv11.setTextColor(Color.WHITE);
            tbrow0.addView(tv11);

            TextView tv12 = new TextView(this);
            tv12.setText("QNH(in)");
            tv12.setTextColor(Color.WHITE);
            tbrow0.addView(tv12);

            TextView tv13 = new TextView(this);
            tv13.setText("QFE(hpa)");
            tv13.setTextColor(Color.WHITE);
            tbrow0.addView(tv13);

            TextView tv14 = new TextView(this);
            tv14.setText("QFE(in)");
            tv14.setTextColor(Color.WHITE);
            tbrow0.addView(tv14);

            TextView tv15 = new TextView(this);
            tv15.setText("RE W1W1");
            tv15.setTextColor(Color.WHITE);
            tbrow0.addView(tv15);


            TextView tv16 = new TextView(this);
            tv16.setText("SYNC");
            tv16.setTextColor(Color.WHITE);
            tbrow0.addView(tv16);

            stk.addView(tbrow0);


            db = new DatabaseHelper(getApplicationContext());

            List<Metar> allMetars = db.getAllMetar();
            for (int i = 0; i <  allMetars.size(); i++) {


                TableRow tbrow = new TableRow(this);
                TextView t1v = new TextView(this);
                t1v.setText( i+ " "+allMetars.get(i).getType().toString() );
                t1v.setTextColor(Color.WHITE);

                tbrow.addView(t1v);

                TextView t2v = new TextView(this);
                t2v.setText("HK63");
                t2v.setTextColor(Color.WHITE);

                tbrow.addView(t2v);

                TextView t3v = new TextView(this);
                t3v.setText(allMetars.get(i).getDatetime().toString());
                t3v.setTextColor(Color.WHITE);

                tbrow.addView(t3v);

                TextView t4v = new TextView(this);
                t4v.setText(allMetars.get(i).getWind_direction().toString()+" "+allMetars.get(i).getWind_speed().toString()+" KT");
                t4v.setTextColor(Color.WHITE);

                tbrow.addView(t4v);

                TextView t5v = new TextView(this);
                t5v.setText(allMetars.get(i).getPresent_weather().toString());
                t5v.setTextColor(Color.WHITE);
                tbrow.addView(t5v);


                TextView t6v = new TextView(this);
                t6v.setText(allMetars.get(i).getRecent_weather().toString());
                t6v.setTextColor(Color.WHITE);
                tbrow.addView(t6v);

                TextView t7v = new TextView(this);
                t7v.setText(allMetars.get(i).getCloud().toString());
                t7v.setTextColor(Color.WHITE);
                tbrow.addView(t7v);

                TextView t8v = new TextView(this);
                t8v.setText(allMetars.get(i).getAir_temperature().toString());
                t8v.setTextColor(Color.WHITE);
                tbrow.addView(t8v);

                TextView t9v = new TextView(this);
                t9v.setText(allMetars.get(i).getDew_temperature().toString());
                t9v.setTextColor(Color.WHITE);
                tbrow.addView(t9v);

                TextView t10v = new TextView(this);
                t10v.setText(allMetars.get(i).getWet_bulb().toString());
                t10v.setTextColor(Color.WHITE);
                tbrow.addView(t10v);

                TextView t11v = new TextView(this);
                t11v.setText(allMetars.get(i).getAir_temperature().toString()+"/"+allMetars.get(i).getDew_temperature().toString());
                t11v.setTextColor(Color.WHITE);
                tbrow.addView(t11v);
                String pressure = "0";
                pressure = "0"+allMetars.get(i).getStation_pressure_hpa().toString();
                TextView t12v = new TextView(this);
                t12v.setText(pressure+"");
                t12v.setTextColor(Color.WHITE);
                tbrow.addView(t12v);

                double result = ((Double.parseDouble(allMetars.get(i).getStation_pressure_hpa().toString())* 0.02952));
                String stringdouble=  String.valueOf(result);
                TextView t13v = new TextView(this);
               t13v.setText(stringdouble.toString()+"");
                t13v.setTextColor(Color.WHITE);
                tbrow.addView(t13v);

                TextView t14v = new TextView(this);
                t14v.setText(allMetars.get(i).getSea_pressure_hpa().toString()+"");
                t14v.setTextColor(Color.WHITE);
                tbrow.addView(t14v);


               double results = (Double.parseDouble(allMetars.get(i).getSea_pressure_hpa().toString())* 0.02952);
                String stringdoubles= String.valueOf(results);

                TextView t15v = new TextView(this);
                t15v.setText(stringdoubles.toString());
                t15v.setTextColor(Color.WHITE);
                tbrow.addView(t15v);

                TextView t16v = new TextView(this);
                t16v.setText(allMetars.get(i).getRecent_weather().toString()+"" );
                t16v.setTextColor(Color.WHITE);
                tbrow.addView(t16v);

                TextView t17v = new TextView(this);
                t17v.setText(allMetars.get(i).getSync().toString()+"" );
                t17v.setTextColor(Color.WHITE);
                tbrow.addView(t17v);

                stk.addView(tbrow);
                }


        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.metars, menu);
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

    private void Online() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        List<Metar> allMetars = db.getAllMetar();
        for ( int i = 0; i < allMetars.size(); i++) {

          if( allMetars.get(i).getSync().toString().equals("F")) {
              Toast.makeText(getApplicationContext(),station, Toast.LENGTH_LONG).show();
              params.put("station", station);
              params.put("type", allMetars.get(i).getType().toString());
              params.put("datetime", allMetars.get(i).getDatetime().toString());
              params.put("wind", allMetars.get(i).getWind_direction().toString()+" "+allMetars.get(i).getWind_speed().toString()+"KT");
              params.put("visibility", allMetars.get(i).getVisibility().toString());
              params.put("present", allMetars.get(i).getPresent_weather().toString());
              params.put("cloud", allMetars.get(i).getCloud().toString());
              params.put("stationhpa", allMetars.get(i).getStation_pressure_hpa().toString());
              params.put("seahpa", allMetars.get(i).getSea_pressure_hpa().toString());
              params.put("recent", allMetars.get(i).getRecent_weather().toString());
              params.put("temperature", allMetars.get(i).getAir_temperature().toString());
              params.put("humidity", allMetars.get(i).getHumidity().toString());
              params.put("dew", allMetars.get(i).getDew_temperature().toString());
              params.put("wet", allMetars.get(i).getWet_bulb().toString());
              params.put("datenow", allMetars.get(i).getDay().toString());
              params.put("user", name);

            db.updateMetar(allMetars.get(i).getId(),"T");
        //   Toast.makeText(getApplicationContext(), params.toString(), Toast.LENGTH_LONG).show();
        client.post(util.Url + "metar/saveOnline", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int in, Header[] headers, byte[] bytes) {
                System.out.println(headers.toString());
                String ret = new String(bytes);
                Toast.makeText(getApplicationContext(),ret.toString(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {
            // TODO Auto-generated method stub
                if (statusCode == 404) {
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();

                } else if (statusCode == 500) {
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Error:" + statusCode + throwable.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        });
        }
      }

    }

}

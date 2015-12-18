package vugga.wimeaapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.List;

import vugga.wimeaapp.helper.DatabaseHelper;
import vugga.wimeaapp.model.Daily;
import vugga.wimeaapp.model.Metar;


public class DailysActivity extends ActionBarActivity {
    TableLayout tableLayout;
    DatabaseHelper db;

    String name;
    String station;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailys);

        SharedPreferences myPrefs = getSharedPreferences(util.PREFS_NAME, 0);
        name = myPrefs.getString("name", "").toString();
        station = myPrefs.getString("station", "").toString();

        db = new DatabaseHelper(getApplicationContext());
        init();

        if (haveNetworkConnection()) {
            Toast.makeText(getApplicationContext(), "uploading data to server", Toast.LENGTH_LONG).show();
           Online();
        } else {
            Toast.makeText(getApplicationContext(), "no connection", Toast.LENGTH_LONG).show();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dailys, menu);
        return true;


}
    public void init() {

        TableLayout stk = (TableLayout) findViewById(R.id.tableLayout);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText("Date");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);


        TextView tv1 = new TextView(this);
        tv1.setText("MAX ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);

        TextView tv2 = new TextView(this);
        tv2.setText("MIN");
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2);

        TextView tv3 = new TextView(this);
        tv3.setText("R/F(mm)");
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3);

        TextView tv4 = new TextView(this);
        tv4.setText("Duration(Hrs)");
        tv4.setTextColor(Color.WHITE);
        tbrow0.addView(tv4);


        TextView tv5 = new TextView(this);
        tv5.setText("Anemometer");
        tv5.setTextColor(Color.WHITE);
        tbrow0.addView(tv5);

        TextView tv6 = new TextView(this);
        tv6.setText("Height");
        tv6.setTextColor(Color.WHITE);
        tbrow0.addView(tv6);

        TextView tv7 = new TextView(this);
        tv7.setText("Wind");
        tv7.setTextColor(Color.WHITE);
        tbrow0.addView(tv7);

        TextView tv8 = new TextView(this);
        tv8.setText("Rain");
        tv8.setTextColor(Color.WHITE);
        tbrow0.addView(tv8);

        TextView tv9 = new TextView(this);
        tv9.setText("Thunder");
        tv9.setTextColor(Color.WHITE);
        tbrow0.addView(tv9);

        TextView tv10 = new TextView(this);
        tv10.setText("Fog");
        tv10.setTextColor(Color.WHITE);
        tbrow0.addView(tv10);

        TextView tv11 = new TextView(this);
        tv11.setText("Haze");
        tv11.setTextColor(Color.WHITE);
        tbrow0.addView(tv11);

        TextView tv12 = new TextView(this);
        tv12.setText("Storm");
        tv12.setTextColor(Color.WHITE);
        tbrow0.addView(tv12);

        TextView tv13 = new TextView(this);
        tv13.setText("Quake");
        tv13.setTextColor(Color.WHITE);
        tbrow0.addView(tv13);

        TextView tv14 = new TextView(this);
        tv14.setText("QFE(in)");
        tv14.setTextColor(Color.WHITE);
        tbrow0.addView(tv14);

        TextView tv15 = new TextView(this);
        tv15.setText("Sunshine");
        tv15.setTextColor(Color.WHITE);
        tbrow0.addView(tv15);

        TextView tv16 = new TextView(this);
        tv16.setText("Rad.type");
        tv16.setTextColor(Color.WHITE);
        tbrow0.addView(tv16);
        TextView tv17 = new TextView(this);
        tv17.setText("Radiation");
        tv17.setTextColor(Color.WHITE);
        tbrow0.addView(tv17);

        TextView tv18 = new TextView(this);
        tv18.setText("Evap.type");
        tv18.setTextColor(Color.WHITE);
        tbrow0.addView(tv18);
        TextView tv19 = new TextView(this);
        tv19.setText("Evap");
        tv19.setTextColor(Color.WHITE);
        tbrow0.addView(tv19);
        TextView tv20 = new TextView(this);
        tv20.setText("Evap.type");
        tv20.setTextColor(Color.WHITE);
        tbrow0.addView(tv20);
        TextView tv21 = new TextView(this);
        tv21.setText("Evap");
        tv21.setTextColor(Color.WHITE);
        tbrow0.addView(tv21);


        TextView tv22 = new TextView(this);
        tv22.setText("SYNC");
        tv22.setTextColor(Color.WHITE);
        tbrow0.addView(tv22);

        stk.addView(tbrow0);


        db = new DatabaseHelper(getApplicationContext());

        List<Daily> allDaily = db.getAllDaily();
        for (int i = 0; i <  allDaily.size(); i++) {


            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText( " "+allDaily.get(i).getDate().toString() );
            t1v.setTextColor(Color.WHITE);

            tbrow.addView(t1v);

            TextView t2v = new TextView(this);
           t2v.setText(" "+allDaily.get(i).getMax().toString() );
            t2v.setTextColor(Color.WHITE);

            tbrow.addView(t2v);

            TextView t3v = new TextView(this);
            t3v.setText(allDaily.get(i).getMin().toString());
            t3v.setTextColor(Color.WHITE);

            tbrow.addView(t3v);

            TextView t4v = new TextView(this);
            t4v.setText(allDaily.get(i).getMax().toString()+" ");
            t4v.setTextColor(Color.WHITE);

            tbrow.addView(t4v);

            TextView t5v = new TextView(this);
            t5v.setText(allDaily.get(i).getActual().toString());
            t5v.setTextColor(Color.WHITE);
            tbrow.addView(t5v);


            TextView t6v = new TextView(this);
            t6v.setText(allDaily.get(i).getDuration().toString());
            t6v.setTextColor(Color.WHITE);
            tbrow.addView(t6v);

            TextView t7v = new TextView(this);
             t7v.setText(allDaily.get(i).getAnemometer().toString());
            t7v.setTextColor(Color.WHITE);
            tbrow.addView(t7v);

            TextView t8v = new TextView(this);
            t8v.setText(allDaily.get(i).getHeight().toString());
            t8v.setTextColor(Color.WHITE);
            tbrow.addView(t8v);

            TextView t9v = new TextView(this);
          t9v.setText(allDaily.get(i).getWind().toString());
            t9v.setTextColor(Color.WHITE);
            tbrow.addView(t9v);

            TextView t10v = new TextView(this);
            t10v.setText(allDaily.get(i).getRain().toString());
            t10v.setTextColor(Color.WHITE);
            tbrow.addView(t10v);

            TextView t11v = new TextView(this);
            t11v.setText(allDaily.get(i).getThunder().toString());
            t11v.setTextColor(Color.WHITE);
            tbrow.addView(t11v);

            String pressure = "0";
            pressure = "0"+allDaily.get(i).getFog().toString();
            TextView t12v = new TextView(this);
            t12v.setText(pressure+"");
            t12v.setTextColor(Color.WHITE);
            tbrow.addView(t12v);


            TextView t13v = new TextView(this);
            t13v.setText(allDaily.get(i).getHaze().toString());
            t13v.setTextColor(Color.WHITE);
            tbrow.addView(t13v);

            TextView t14v = new TextView(this);
            t14v.setText(allDaily.get(i).getStorm().toString()+"");
            t14v.setTextColor(Color.WHITE);
            tbrow.addView(t14v);


            TextView t15v = new TextView(this);
            t15v.setText(allDaily.get(i).getQuake().toString()+"");
            t15v.setTextColor(Color.WHITE);
            tbrow.addView(t15v);

            TextView t16v = new TextView(this);
            t16v.setText(allDaily.get(i).getSunshine().toString()+"" );
            t16v.setTextColor(Color.WHITE);
            tbrow.addView(t16v);

            TextView t17v = new TextView(this);
            t17v.setText(allDaily.get(i).getRadiation().toString()+"" );
            t17v.setTextColor(Color.WHITE);
            tbrow.addView(t17v);

            TextView t18v = new TextView(this);
            t18v.setText(allDaily.get(i).getRadiationtype().toString()+"" );
            t18v.setTextColor(Color.WHITE);
            tbrow.addView(t18v);


            TextView t19v = new TextView(this);
            t19v.setText(allDaily.get(i).getEvaptype1().toString()+"" );
            t19v.setTextColor(Color.WHITE);
            tbrow.addView(t19v);
            TextView t20v = new TextView(this);
            t20v.setText(allDaily.get(i).getEvap1().toString()+"" );
            t20v.setTextColor(Color.WHITE);
            tbrow.addView(t20v);


            TextView t21v = new TextView(this);
            t21v.setText(allDaily.get(i).getEvaptype2().toString()+"" );
            t21v.setTextColor(Color.WHITE);
            tbrow.addView(t21v);
            TextView t22v = new TextView(this);
            t22v.setText(allDaily.get(i).getEvap2().toString()+"" );
            t22v.setTextColor(Color.WHITE);
            tbrow.addView(t22v);



            TextView t23v = new TextView(this);
            t23v.setText(allDaily.get(i).getSync().toString()+"" );
            t23v.setTextColor(Color.WHITE);
            tbrow.addView(t23v);

            stk.addView(tbrow);
        }


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
        List<Daily> allDailys = db.getAllDaily();
        for ( int i = 0; i < allDailys.size(); i++) {

            if( allDailys.get(i).getSync().toString().equals("F")) {

                Toast.makeText(getApplicationContext(),station, Toast.LENGTH_LONG).show();
                params.put("station", station);
                params.put("date", allDailys.get(i).getDate().toString());
                params.put("max", allDailys.get(i).getMax().toString());
                params.put("min", allDailys.get(i).getMin().toString());
                params.put("actual", allDailys.get(i).getActual().toString());
                params.put("anemometer", allDailys.get(i).getAnemometer().toString());
                params.put("wind", allDailys.get(i).getWind().toString());
                params.put("rain", allDailys.get(i).getRain().toString());
                params.put("thunder", allDailys.get(i).getThunder().toString());
                params.put("fog", allDailys.get(i).getFog().toString());
                params.put("haze", allDailys.get(i).getHaze().toString());
                params.put("storm", allDailys.get(i).getStorm().toString());
                params.put("quake", allDailys.get(i).getQuake().toString());
                params.put("height", allDailys.get(i).getHeight().toString());
                params.put("duration", allDailys.get(i).getDuration().toString());
                params.put("sunshine", allDailys.get(i).getSunshine().toString());
                params.put("type", allDailys.get(i).getRadiationtype().toString());
                params.put("radiation", allDailys.get(i).getRadiation().toString());
                params.put("evaptype1", allDailys.get(i).getEvaptype1().toString());
                params.put("evap1", allDailys.get(i).getEvap1().toString());
                params.put("evaptype2", allDailys.get(i).getEvaptype2().toString());
                params.put("evap2", allDailys.get(i).getEvap2().toString());



                params.put("user", name);

                db.updateDaily(allDailys.get(i).getId(),"T");
                //   Toast.makeText(getApplicationContext(), params.toString(), Toast.LENGTH_LONG).show();
                client.post(util.Url + "metar/dailyOnline", params, new AsyncHttpResponseHandler() {

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

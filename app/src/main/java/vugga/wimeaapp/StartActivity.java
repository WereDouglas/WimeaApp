package vugga.wimeaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vugga.wimeaapp.helper.DatabaseHelper;
import vugga.wimeaapp.model.Daily;
import vugga.wimeaapp.model.Metar;


public class StartActivity extends ActionBarActivity {


    TextView status,metarinfo,dailyinfo;
    DatabaseHelper db;
    Button metar,daily,dailys,metars,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        db = new DatabaseHelper(getApplicationContext());

        List<Metar> allMetars = db.getAllMetar();
        List<Daily> allDailys = db.getAllDaily();

        metarinfo = (TextView) findViewById(R.id.metarinfo);
        dailyinfo = (TextView) findViewById(R.id.dailyinfo);

        dailyinfo.setText("Daily count: "+allDailys.size());
        metarinfo.setText("Metar count: "+allMetars.size());


        SharedPreferences myPrefs = getSharedPreferences(util.PREFS_NAME, 0);
        String name =   myPrefs.getString("name", "").toString();
        String station =   myPrefs.getString("station", "").toString();
        if(!(name.toString().equals("") && station.toString().equals("")) ) {
            status = (TextView) findViewById(R.id.status);
            status.setText(""+name + "    "+station);
        }

        metar = (Button) findViewById(R.id.metar);
        metar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent startLocation = new Intent(StartActivity.this, MetarActivity.class);
                startActivity(startLocation);

            }
        });

        metars = (Button) findViewById(R.id.metars);
        metars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startLocation = new Intent(StartActivity.this, MetarsActivity.class);
                startActivity(startLocation);

            }
        });

        daily = (Button) findViewById(R.id.daily);
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent startLocation = new Intent(StartActivity.this, DailyActivity.class);
                startActivity(startLocation);


            }
        });

        dailys = (Button) findViewById(R.id.dailys);
        dailys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent startLocation = new Intent(StartActivity.this, DailysActivity.class);
                startActivity(startLocation);

            }
        });
        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.removeAll();
                Toast.makeText(getApplicationContext(),"all deleted", Toast.LENGTH_LONG).show();

            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
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

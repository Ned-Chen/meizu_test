package us.allinvest.meizutest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        findViewById(R.id.btn_alert_1).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                sendTestAlarm(Constants.ACTION_TEST_1, "Alert 1", System.currentTimeMillis() + 5000,
                        1);
                sendTestAlarm(Constants.ACTION_TEST_1, "Alert 2",
                        System.currentTimeMillis() + 10000, 2);
            }
        });
    }

    private void sendTestAlarm(String action, String title,
            long triggerAtMillis, int id) {
        Intent intent = new Intent(this, TestReceiver.class);
        intent.setAction(action);
        intent.putExtra(Constants.Extra.TITLE, title);
        intent.putExtra(Constants.Extra.NOTIFICATION_ID, id);
        Uri uri = Uri.parse("content://us.allinvest.meizutest").buildUpon()
                .appendEncodedPath("items").build();
        intent.setData(ContentUris.withAppendedId(uri, id));
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                triggerAtMillis,
                PendingIntent.getBroadcast(MainActivity.this, id, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

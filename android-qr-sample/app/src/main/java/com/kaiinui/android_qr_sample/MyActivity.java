package com.kaiinui.android_qr_sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import info.vividcode.android.zxing.CaptureActivity;
import info.vividcode.android.zxing.CaptureActivityIntents;
import info.vividcode.android.zxing.CaptureResult;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.button)
    public void onButtonClick() {
        Intent captureIntent = new Intent(this, CaptureActivity.class);
        // Using `CaptureActivityIntents`, set parameters to an intent.
        // (There is no requisite parameter to set to an intent.)
        // For instance, `setPromptMessage` method set prompt message displayed on `CaptureActivity`.
        CaptureActivityIntents.setPromptMessage(captureIntent, "Barcode scanning...");
        // Start activity.
        startActivityForResult(captureIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                CaptureResult res = CaptureResult.parseResultIntent(data);
                Toast.makeText(this, res.getContents() + " (" + res.getFormatName() + ")", Toast.LENGTH_LONG).show();
            } else {
                // Process comes here when “back” button was clicked for instance.
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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

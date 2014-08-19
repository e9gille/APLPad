package com.athoraya.aplpad;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.athoraya.MESSAGE";
    ClipboardManager mClipboard;

    ClipboardManager.OnPrimaryClipChangedListener mPrimaryChangeListener
            = new ClipboardManager.OnPrimaryClipChangedListener() {
        public void onPrimaryClipChanged() {
            updateClipData();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FontOverride.setDefaultFont(this,"MONOSPACE","fonts/apl385.ttf");
        setContentView(R.layout.activity_main);

        mClipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        mClipboard.addPrimaryClipChangedListener(mPrimaryChangeListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;

            case R.id.action_settings:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_message);
        ClipData clip = mClipboard.getPrimaryClip().newPlainText("APLPad",editText.getText());
        mClipboard.setPrimaryClip(clip);
    }

    @Override
    public void onDestroy(){
        mClipboard.removePrimaryClipChangedListener(mPrimaryChangeListener);
    }

    public void updateClipData() {
        ClipData clip = mClipboard.getPrimaryClip();
        if (clip.getDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
            ClipData.Item item = clip.getItemAt(0);

            TextView clipText = (TextView) findViewById(R.id.clipboard_text);
            clipText.setText(item.getText());

        }
    }

}

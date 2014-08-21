package com.athoraya.aplpad;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.athoraya.utils.TypefaceUtil;

public class MainActivity extends Activity {

//    private ClipboardManager mClipboard;
    private EditText mEditText;
/*
    private TextView mClipText;

    ClipboardManager.OnPrimaryClipChangedListener mPrimaryChangeListener
            = new ClipboardManager.OnPrimaryClipChangedListener() {
        public void onPrimaryClipChanged() {
            updateClipData(false);
        }
    };
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        mClipboard = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
//        mClipboard.addPrimaryClipChangedListener(mPrimaryChangeListener);

        mEditText = (EditText) this.findViewById(R.id.edit_message);
    }

    @Override
    protected void onStart(){
        super.onStart();
        loadPref();
//        updateClipData(true);
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
/*
            case R.id.action_copy:
                copyText();
                return true;
*/
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivityForResult(intent, 0);
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        loadPref();
    }

    private void loadPref(){
        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String font_name = mySharedPreferences.getString("pref_font_name", "");
        if (font_name != "") {
//            TypefaceUtil.overrideFont(this, "DEFAULT", font_name);
            mEditText.setTypeface(Typeface.createFromAsset(getAssets(), font_name));
        }
    }
/*
    public void copyText() {
        if (mEditText.getText().length() > 0) {
            ClipData clip = mClipboard.getPrimaryClip().newPlainText("APLPad", mEditText.getText());
            mClipboard.setPrimaryClip(clip);
        }
    }
*/
    @Override
    public void onDestroy(){
        super.onDestroy();
//        mClipboard.removePrimaryClipChangedListener(mPrimaryChangeListener);
    }
/*
    public void updateClipData(Boolean pasteToEdit) {
        ClipData clip = mClipboard.getPrimaryClip();
        if (clip != null &&
                clip.getDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
            ClipData.Item item = clip.getItemAt(0);

            CharSequence clipText = item.getText();
            mClipText.setText(clipText);
            if (pasteToEdit && mEditText.getText().length() == 0) {
                mEditText.setText(clipText);
            }

        }
    }
*/
}

package com.example.johnpconsidine.healthebeacon;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*

        Make sure the user can enter out of the keyboard any time they wish

         */
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainScreenWrapper);
        if (mainLayout != null)
            mainLayout.setOnClickListener(closeKeyBoardClickListener);
        setExplainSnackBar();


        /* Set button click listener to open dialog*/
        Button button = (Button) findViewById(R.id.startTeamButton);
        if (button != null)
            button.setOnClickListener(pickTeamClickListener);

    }

    /*
    THis should be called AFTER we've checked this user exists
     */
    private void setExplainSnackBar () {
//        Snackbar.make(findViewById(android.R.id.content), "Had a snack at Snackbar", Snackbar.LENGTH_LONG)
//                .setActionTextColor(Color.RED)
//                .setText(getString(R.string.text_id_explan))
//                .show();
        Toast.makeText(getApplicationContext(), getString(R.string.text_id_explan), Toast.LENGTH_LONG).show();
    }

    /*
    Sets a click listener on the screen to close the keyboard
     */
    private View.OnClickListener closeKeyBoardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeKeyBoard();
        }
    };

    private void closeKeyBoard () {
        View view = this.getCurrentFocus();
        if (view == null)
            return;
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    private View.OnClickListener pickTeamClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
            builder.setTitle(getString(R.string.pick_team));
            String[] items = getResources().getStringArray(R.array.mock_team_names);
            final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(SplashActivity.this, android.R.layout.select_dialog_singlechoice, items);
            builder.setAdapter(listAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }
    };
}

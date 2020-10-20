package com.example.android5778_7822_2864.control;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android5778_7822_2864.R;
import com.example.android5778_7822_2864.entities.Driver;
import com.example.android5778_7822_2864.model.backend.*;

import java.util.ArrayList;
import java.util.List;

/**
 * class LoginActivity
 * 
 */
public class LoginActivity extends AppCompatActivity {//implements LoaderCallbacks<Cursor> {
    public CheckBox checkBox;
    private EditText editTextId;
    private EditText editTextPassword;
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String TEXT_ID="textId";
    public static final String TEXT_PASSWORD="textPassword";

    public static final String CHECK_BOX="checkBox";
    public String textId;
    public String textPassword;
    public boolean boolCheckBox;
    public static final String ID_EXTRA="com.example.android5778_7822_2864.control.ID_EXTRA";
    /**
     * onCreate
     *Sign in to the app
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Backend fb=BackendFactory.getInstance(this);
        checkBox=(CheckBox) findViewById(R.id.checkBox);
        editTextId=(EditText)findViewById(R.id.userId);
        editTextPassword=(EditText)findViewById(R.id.password);
        loadDataAndUpdate();

        Button SignInButton = (Button) findViewById(R.id.sign_in_button);
        SignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Driver> driverList=new ArrayList<Driver>();
                driverList=fb.getDriverList();
               if(checkUser(driverList)) {

                   //check if checkBox marked
                   if (checkBox.isChecked()) {
                       saveData();
                   } else {
                       deleteData();
                   }
                   Long id = Long.parseLong(editTextId.getText().toString());
                   Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                   intent.putExtra(ID_EXTRA, id);
                   startActivity(intent);
               }


            }

        });
        TextView SignRegister = (TextView) findViewById(R.id.TextviewRegister);
        SignRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LoginActivity.this, DriverActivity.class);
                startActivity(intent);
            }


        });


    }

    /**
     *
     * @param list
     * @return true if the driver in the system else false
     */
    public boolean checkUser(List<Driver> list)
    {
        boolean flag=false;
        Long id= Long.parseLong(editTextId.getText().toString());
        String password=editTextPassword.getText().toString();
        for (Driver driver:list )//go over the list
        {
            Long idOfDriver=driver.getId();
            String passwordOfDriver=driver.getPassword();

            if(idOfDriver.equals(id)&&passwordOfDriver.equals(password))//check if id and password in the list
            {
                return true;
            }
            else if(idOfDriver.equals(id)&&!passwordOfDriver.equals(password))//if the id int the list but password not
            {
                editTextPassword.setError("Password incorrect! try again");
                return false;
            }

        }
       /* if(!flag)
        {
            editTextId.setError("The user does not exist in the system");
            return  false;
        }*/
        return true;
    }

    /**
     * saveData
     * save a user name and password
     */
    public void saveData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT_ID, editTextId.getText().toString());
        editor.putString(TEXT_PASSWORD, editTextPassword.getText().toString());
        editor.putBoolean(CHECK_BOX,checkBox.isChecked());

        editor.commit();

    }

    /**
     * loadDataAndUpdate
     * shardedprefernces, save a user name and password on the screen
     */
    public void loadDataAndUpdate()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        textId = sharedPreferences.getString(TEXT_ID, "");
        textPassword = sharedPreferences.getString(TEXT_PASSWORD, "");
        boolCheckBox = sharedPreferences.getBoolean(CHECK_BOX,false);
        editTextId.setText(textId);
        editTextPassword.setText(textPassword);
        checkBox.setChecked(boolCheckBox);
    }

    /**
     * deleteData
     * delete a user name and password
     */
    public void deleteData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT_ID, "");
        editor.putString(TEXT_PASSWORD,"");
        editor.putBoolean(CHECK_BOX,false);

        editor.commit();

    }


}


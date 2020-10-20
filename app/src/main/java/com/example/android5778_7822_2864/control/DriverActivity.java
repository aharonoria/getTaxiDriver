package com.example.android5778_7822_2864.control;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android5778_7822_2864.R;
import com.example.android5778_7822_2864.entities.Driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.example.android5778_7822_2864.model.backend.*;
public class DriverActivity extends Activity implements View.OnClickListener {

    private  Button addDriver;
    /**
     * ocCreate
     * insert new driver to database
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_driver);
            addDriver = (Button)findViewById(R.id.ButtonAdd);
            addDriver.setOnClickListener(this);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * onClick
     * event to insert details of new driver
     * @param v
     */
     public void onClick(View v) {

          try {

              Driver driver = new Driver();
              //find the id from view
              EditText userFirstNameField = (EditText) findViewById(R.id.editTextName);
              EditText userLastNameField = (EditText) findViewById(R.id.editTextLastName);
              EditText userPhoneField = (EditText) findViewById(R.id.editTextPhone);
              EditText userEmailField = (EditText) findViewById(R.id.editTextEmail);
              EditText userCreditCardField = (EditText) findViewById(R.id.editTextCreditCard);
              EditText userIdField = (EditText) findViewById(R.id.editId);
              EditText userPasswordField = (EditText) findViewById(R.id.edifPassword);


              //If input is correct enter the details
              if (Validate(userFirstNameField,userLastNameField, userPhoneField, userEmailField,userCreditCardField ,userIdField,userPasswordField )) {
                  final String firstName = (userFirstNameField.getText().toString());
                  final String lastName = (userLastNameField.getText().toString());
                  final String phone = userPhoneField.getText().toString();
                  final String email = userEmailField.getText().toString();
                  final String password = userPasswordField.getText().toString();
                  final String id = userIdField.getText().toString();
                  final String creditCard = userCreditCardField.getText().toString();

                          Driver driver1 = new Driver();
                           //update the driver
                          driver1.setFirstName(firstName);
                          driver1.setLastName(lastName);
                          driver1.setPhone(phone);
                          driver1.setEmail(email);
                          driver1.setPassword(password);
                          driver1.setId(Long.parseLong(id));
                          driver1.setCreditCard(Long.parseLong(creditCard));
                          try {
                                 Backend backend1 = BackendFactory.getInstance(this);
                                 //call the function  to add in data base
                                  backend1.addDriver(driver1,new Backend.Action() {
                                  @Override
                                  public void onSuccess(Long id) {
                                      Toast.makeText(getBaseContext(), "registration succeeded!", Toast.LENGTH_LONG).show();
                                      addDriver.setEnabled(true);
                                      goToMainActivity ();
                                  }

                                  @Override
                                  public void onFailure(Exception exception) {
                                      Toast.makeText(getBaseContext(), "registration failed!", Toast.LENGTH_LONG).show();

                                  }

                                  @Override
                                  public void onProgress(String status, double percent) {
                                      if( percent != 100)
                                          addDriver.setEnabled(false);
                                  }
                              });


                          } catch (Exception e) {
                              e.printStackTrace();
                          }
                          //update the files
                  userFirstNameField.setText("");
                  userLastNameField.setText("");
                  userPhoneField.setText("");
                  userEmailField.setText("");
                  userPasswordField.setText("");
                  userCreditCardField.setText("");
                  userIdField.setText("");
                  goToMainActivity ();
              }


          } catch (Exception e) {
              System.out.println(e.getMessage());
          }
      }

    /**
     *jump to activity of the driver
     */
    public  void goToMainActivity ()
 {
     Intent intent = new Intent(this, MainActivity.class);//when the user click the button login the intent call to new activity
     startActivity(intent);//start the activity
 }
    /**
     * isValidMail
     * Mail integrity check
     * @param email
     * @param userEmailField
     * @return
     */
    private boolean isValidMail(String email,EditText userEmailField) {
        boolean check;
        Pattern p;
        Matcher m;

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        p = Pattern.compile(EMAIL_STRING);

        m = p.matcher(email);
        check = m.matches();
        if (!check) {
            userEmailField.setError("Not Valid Email");
        }

        return check;
    }

    /**
     * isValidMobile
     * Phone integrity check
     * @param phone
     * @param userPhoneField
     * @return
     */
    private boolean isValidMobile(String phone,  EditText userPhoneField) {
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            if (phone.length() < 6 || phone.length() > 13) {
                // if(phone.length() != 10) {
                check = false;
                userPhoneField.setError("Not Valid Number");
            }
            else {
                check = true;
            }
        } else {
            check = false;
            userPhoneField.setError("Not Valid Number");
        }
        return check;
    }

    /**
     * isValidCreditCard
     * credit card integrity check
     * @param userCreditCardField
     * @return
     */
    public boolean isValidCreditCard(EditText userCreditCardField)
    {
        if(userCreditCardField.getText().toString().length()<16) {
            userCreditCardField.setError("Missing numbers");
            return false;
        }
        return true;
    }

    /**
     * Validate
     * Check the integrity of the input
     * @param userFirstNameField
     * @param userLastNameField
     * @param userPhoneField
     * @param userEmailField
     * @param userCreditCardField
     * @param userIdField
     * @param userPasswordField
     * @return
     */
    public boolean Validate(EditText userFirstNameField , EditText userLastNameField,EditText userPhoneField ,
                            EditText userEmailField ,EditText userCreditCardField,EditText userIdField ,EditText userPasswordField ){
        //If one of the fields is empty
        if(userFirstNameField.getText().toString().length()==0||userPhoneField.getText().toString().length()==0|| userEmailField.getText().toString().length()==0|| userLastNameField.getText().toString().length()==0
                ||userCreditCardField.getText().toString().length()==0
                ||userIdField.getText().toString().length()==0||userPasswordField.getText().toString().length()==0)
        {
            Toast.makeText(DriverActivity.this, "You have empty fields!", Toast.LENGTH_LONG).show();
            return false;

        }
        boolean flag=true;
        //Mail integrity check
        if(!isValidMail(userEmailField.getText().toString(),userEmailField)) flag=false;
        //Phone integrity check
        if(!isValidMobile(userPhoneField.getText().toString(),userPhoneField)) flag=false;
        //credit card integrity check
        if(!isValidCreditCard(userCreditCardField)) flag=false;
        return flag;


    }
}








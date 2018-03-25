package com.weteam.weteam.dziennikprzedszkolaka.preschool;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.weteam.weteam.dziennikprzedszkolaka.R;
import com.weteam.weteam.dziennikprzedszkolaka.SignInActivity;

import architecture.Preschool;
import architecture.User;

public class EditPreschoolActivity extends SignInActivity {

    Preschool user;
    int userId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_preschool);

        Bundle myIntent = getIntent().getExtras();
        if(myIntent!=null) {
            userId = myIntent.getInt("id");
            user = findPreschoolById(userId);
        }

        EditText editText = (EditText)findViewById(R.id.eLogin);
        editText.setText(user.login);
        editText = (EditText)findViewById(R.id.eName);
        editText.setText(user.name);
        editText = (EditText)findViewById(R.id.eAddress);
        editText.setText(user.location);
        editText = (EditText)findViewById(R.id.eOpeningHours);
        editText.setText(user.openingHours);
    }

    public boolean confirmPassword(){

        EditText editText;
        String password, password2;

        editText = (EditText) findViewById(R.id.ePassword1);
        password = editText.getText().toString();

        if(password.length()>= 2) {

            editText = (EditText) findViewById(R.id.ePassword2);
            password2 = editText.getText().toString();


            if (!password.equals(password2)) {
                editText.setError(getString(R.string.error_not_the_same_password));
                return false;
            }
            return true;
        }
        editText.setError(getString(R.string.error_password_too_short));

        return false;
    }


    public void changeLogin(View view) {

        if(checkLogin()) {
            Toast toast;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            try {
                EditText editText = (EditText) findViewById(R.id.eLogin);
                user.login = editText.getText().toString();
                toast = Toast.makeText(context, R.string.success, duration);
                saveAll();
            }
            catch (Exception e){
                toast = Toast.makeText(context, R.string.failure, duration);
            }
            toast.show();
        }
    }

    public void changePassword(View view) {

        if(confirmPassword()) {
            Toast toast;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            try {
                EditText editText = (EditText) findViewById(R.id.ePassword1);
                user.password = editText.getText().toString().hashCode();
                toast = Toast.makeText(context, R.string.success, duration);
                saveAll();
            }catch (Exception e){
                toast = Toast.makeText(context, R.string.failure, duration);
            }
            toast.show();
        }
    }

    public void changeName(View view) {
        EditText editText = (EditText) findViewById(R.id.eName);
        String name = editText.getText().toString();
        if (checkEmptyField(name, editText)){

            Toast toast;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            try {
                user.name = name;
                toast = Toast.makeText(context, R.string.success, duration);
                saveAll();
            } catch (Exception e) {
                toast = Toast.makeText(context, R.string.failure, duration);
            }
            toast.show();
        }
    }

    public void changeAddress(View view) {
        EditText editText = (EditText) findViewById(R.id.eAddress);

        Toast toast;
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        try {
            user.location = editText.getText().toString();
            toast = Toast.makeText(context, R.string.success, duration);
            saveAll();
        }catch (Exception e){
            toast = Toast.makeText(context, R.string.failure, duration);
        }
        toast.show();
    }

    public void changeOpeningHours(View view) {
        EditText editText = (EditText)findViewById(R.id.eOpeningHours);

        Toast toast;
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        try {
            user.openingHours = editText.getText().toString();
            toast = Toast.makeText(context, R.string.success, duration);
            saveAll();
        }catch (Exception e){
            toast = Toast.makeText(context, R.string.failure, duration);
        }
        toast.show();
    }


    boolean checkLogin(){

        EditText editText = (EditText) findViewById(R.id.eLogin);
        String login = editText.getText().toString();

        if(login.equals("")){
            editText.setError(getString(R.string.error_null_login));
            return false;
        }

        for(User i: users)
            if(login.equals(i.login)) {
                editText.setError(getString(R.string.error_the_same_login));
                return false;
            }
        return true;
    }


    private boolean checkEmptyField(String string, EditText editText) {
        if(string.length()<=0) {
            editText.setError(getString(R.string.error_string_is_null));
            return false;
        }
        return true;
    }



}

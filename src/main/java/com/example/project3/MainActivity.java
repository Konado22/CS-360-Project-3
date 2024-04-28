package com.example.project3;

import static com.example.project3.Database.CreateUserTable;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Database helper = new Database(this) {
    };
    Button login_button, register_new_user_button, register_button, return_to_login;
    Button add_button, update_button, remove_button, sms_notify_button;
    SQLiteDatabase userdb,itemdb;
    //declare global variables for accessing withing files

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
    }
    //change to register_user layout
    public void goRegister(View view) {
        register_button = findViewById(R.id.register_button);
        register_button.setOnClickListener(view1 -> {
            setContentView(R.layout.register_page);
        });
    }
    //change to goLogin layout
    public void goLogin (View view) {
       return_to_login = findViewById(R.id.return_to_login);
       return_to_login.setOnClickListener(view2 -> {
           setContentView(R.layout.login_page);
       });
    }

    //register new user in application database
    public void registerUser () {
        register_new_user_button = findViewById(R.id.register_new_user_button);
        register_new_user_button.setOnClickListener(view -> {
            String username = findViewById(R.id.register_username).toString().trim();
            String password = findViewById(R.id.register_password).toString().trim();
            User newUser = new User(username, password);
            helper.addUser(newUser);
            boolean success = true;
            if (success) {
                setContentView(R.layout.inventory_page);
            }
        });
        //add new user to the user database
    }

    //login user in application database
    public void loginUser () {
        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener( view -> {
            String username = findViewById(R.id.username_text).toString();
            String password = findViewById(R.id.password_text).toString();
            User newUser = new User(username, password);
            User compare = helper.getUser(username);
            if (compare != null) {
                setContentView(R.layout.inventory_page);
            }
        });
    }
    public void setAddButton () {
        add_button = findViewById(R.id.update_button);
        add_button.setOnClickListener( v -> {
            Item item = null;
            helper.addItem(item);
        });
    }
    public void setRemoveButton () {
        remove_button = findViewById(R.id.remove_button);
        remove_button.setOnClickListener(v -> {
            Item item = null;
            helper.deleteItem(item);
        });
    }
    public void setUpdateButton () {
        Item item = null;
        update_button = findViewById(R.id.update_button);
        update_button.setOnClickListener(v -> {
            helper.updateItem(item);
        });
    }
    public void setSMSNotifications () {
        sms_notify_button = findViewById(R.id.SMSNotifications_button);
        sms_notify_button.setOnClickListener(v -> {
            SMSalert alert = new SMSalert();
            alert.SMSNotifications();
        });
    }
}
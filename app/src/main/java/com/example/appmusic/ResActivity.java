package com.example.appmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appmusic.Database.User;
import com.example.appmusic.Database.UserData;

public class ResActivity extends AppCompatActivity {
    private Button btnRes2;
    private EditText Username;
    private EditText PassWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        getSupportActionBar().hide();
        anhXa();

        btnRes2OnClickListener();

    }

    private void btnRes2OnClickListener() {
        final UserData dbUser = new UserData(this);

        btnRes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Username.getText().length() <= 0)
                {
                    Toast.makeText(getApplicationContext(),"Username không được bỏ trống" , Toast.LENGTH_SHORT).show();
                    return;
                }
                if (PassWord.getText().length() <= 0)
                {
                    Toast.makeText(getApplicationContext(),"Password không được bỏ trống" , Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = createUser();
                if (user != null) {
                    dbUser.addUser(user);
                    Toast.makeText(getApplicationContext(), "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    dbUser.close();
                }
            }
        });
    }

    private void anhXa() {
        Username = (EditText) findViewById(R.id.txtUserName2);
        PassWord = (EditText) findViewById(R.id.txtPassword2);
        btnRes2  = (Button)  findViewById(R.id.btnRes2);
    }

    private User createUser()
    {
        String UserName = Username.getText().toString();
        String Password = PassWord.getText().toString();
        User user = new User(UserName,Password);
        return user;
    }

}

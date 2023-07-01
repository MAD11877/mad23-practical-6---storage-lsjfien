package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    MyDBHandler myDBHandler = new MyDBHandler(this, null, null, 1);
    ArrayList<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Random rand = new Random();
        if (myDBHandler.getUsers() == null){
            for (int i = 0;i<20;i++){
                User user = new User("Name " + rand.nextInt(100000), "Description " + rand.nextInt(1000000), i, rand.nextBoolean());
                myDBHandler.addUser(user);
            }
        }
        userList = myDBHandler.getUsers();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        UserAdapter mAdapter = new UserAdapter(userList, this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }
}
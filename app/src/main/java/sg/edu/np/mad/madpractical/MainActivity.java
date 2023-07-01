package sg.edu.np.mad.madpractical;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final String TITLE= "Main Activity";
    User myUser;
    MyDBHandler myDBHandler = new MyDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TITLE, "On Create!");


        Intent receivingEnd = getIntent();
        myUser = receivingEnd.getParcelableExtra("User");

        TextView greeting = findViewById(R.id.textView);
        greeting.setText(myUser.name);

        TextView description = findViewById(R.id.textView2);
        description.setText(myUser.description);
        Button myButton = findViewById(R.id.button);
        if (myUser.isFollowed() == true){
            myButton.setText("Unfollow");
        }
        else myButton.setText("Follow");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TITLE, "On Start!");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.v(TITLE, "On Pause!");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.v(TITLE, "On Resume!");

        Button myButton = findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myUser.followed == false)
                {
                    myUser.setFollowed(true);
                    myDBHandler.updateUser(myUser);
                    Log.v(TITLE, "Followed");
                    myButton.setText("Unfollow");
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    myUser.setFollowed(false);
                    myDBHandler.updateUser(myUser);
                    Log.v(TITLE, "Unfollowed");
                    myButton.setText("Follow");
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button myButton2 = findViewById(R.id.button2);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Message = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(Message);
            }
        });
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.v(TITLE, "On Stop!");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.v(TITLE, "On Destroy!");
    }
}
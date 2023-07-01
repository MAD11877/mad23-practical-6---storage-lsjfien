package sg.edu.np.mad.madpractical;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    ArrayList<User> userList;
    Context context;
    String tag = "Adapter";
    public UserAdapter(ArrayList<User> userList, Context context){
        this.userList=userList;
        this.context = context;
    }
    @Override
    public int getItemViewType(int position)
    {
        User user = userList.get(position);
        if (user.name.endsWith("7")) { return 1; }
        else { return 0;}
    }
    @Override
    public UserViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType) {
        if (viewType == 1){
            Log.v(tag, "ViewType: "+viewType);
            return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.alternative_user_list,
                    parent, false));
        }
        else {
            return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list,
                    parent, false));
        }
    }
    @Override
    public void onBindViewHolder(
            UserViewHolder holder,
            int position) {
        User user = userList.get(position);
        holder.nameview.setText(user.name);
        holder.descview.setText(user.description);
        holder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Profile");
                builder.setMessage(user.name);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Intent MainActivity = new Intent(v.getContext(), MainActivity.class);
                        MainActivity.putExtra("User", user);
                        v.getContext().startActivity(MainActivity);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return userList.size();
    }
}


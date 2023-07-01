package sg.edu.np.mad.madpractical;

import android.app.Activity;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder{
    TextView nameview,descview;
    ImageView pic;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        pic = itemView.findViewById(R.id.imageView);
        nameview = itemView.findViewById(R.id.textView3);
        descview = itemView.findViewById(R.id.textView4);
    }
}

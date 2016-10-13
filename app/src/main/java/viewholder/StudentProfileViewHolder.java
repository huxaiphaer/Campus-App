package viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import campus.app.com.newline.campusmobapp.R;

/**
 * Created by HUZY_KAMZ on 10/12/2016.
 */
public class StudentProfileViewHolder  extends RecyclerView.ViewHolder {


    public View root;
    public TextView regno;



    public StudentProfileViewHolder(final View itemView, final Context c) {
        super(itemView);
        root = itemView;
        regno = (TextView) itemView.findViewById(R.id.registration_no);

    }
}

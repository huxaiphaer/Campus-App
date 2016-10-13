package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import campus.app.com.newline.campusmobapp.R;
import model.StudentsProfileModel;
import viewholder.StudentProfileViewHolder;

/**
 * Created by HUZY_KAMZ on 10/12/2016.
 */
public class StudentsProfileAdapter extends RecyclerView.Adapter<StudentProfileViewHolder>{


private List<StudentsProfileModel> itemList ;
private Context context;
    private String regno;
  //  public String branchname ;


        public StudentsProfileAdapter(List<StudentsProfileModel> itemList, Context context) {
            this.itemList = itemList;
            this.context = context;
        }

    public void GetStudentsProfile(int position)
    {
        regno=this.itemList.get(position).getRegno();

    }
        @Override
    public StudentProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.students_profile,parent,false);
            StudentProfileViewHolder rcv = new StudentProfileViewHolder(layoutView,context);
            return rcv;



    }

    @Override
    public void onBindViewHolder(StudentProfileViewHolder holder, int position) {

        StudentsProfileModel ci = itemList.get(position);
        holder.regno.setText(ci.getRegno());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

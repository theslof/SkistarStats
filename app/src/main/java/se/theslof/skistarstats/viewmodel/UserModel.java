package se.theslof.skistarstats.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import se.theslof.skistarstats.BR;
import se.theslof.skistarstats.activity.MainActivity;

public class UserModel extends BaseObservable {
    private String skierId = "";
    private Context context;

    public UserModel(Context context){
        this.context = context;

    }

    @Bindable
    public String getSkierId() {
        return skierId;
    }

    public void setSkierId(String skierId) {
        this.skierId = skierId;
        notifyPropertyChanged(BR.skierId);
    }

    @Bindable
    public String getUserError(){
        try {
            int id = Integer.parseInt(skierId);
        } catch (NumberFormatException e){
            return "Not a number!";
        }

        return null;
    }

    public void onClick(View view){
        if(getUserError() == null) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("skierId", skierId);
            context.startActivity(intent);
        }
    }
}

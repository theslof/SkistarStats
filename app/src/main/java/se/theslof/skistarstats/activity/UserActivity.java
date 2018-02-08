package se.theslof.skistarstats.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import se.theslof.skistarstats.R;
import se.theslof.skistarstats.viewmodel.UserModel;
import se.theslof.skistarstats.databinding.ActivityUserBinding;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUserBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_user);
        UserModel userModel = new UserModel(this);
        binding.setViewModel(userModel);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}

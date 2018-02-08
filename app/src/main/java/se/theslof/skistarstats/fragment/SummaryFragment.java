package se.theslof.skistarstats.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.theslof.skistarstats.R;
import se.theslof.skistarstats.databinding.FragmentSummaryBinding;
import se.theslof.skistarstats.viewmodel.MainModel;

public class SummaryFragment extends Fragment {
    private MainModel viewModel;

    public SummaryFragment() {
        // Required empty public constructor
    }

    public static SummaryFragment newInstance(MainModel viewModel) {
        SummaryFragment fragment = new SummaryFragment();
        fragment.viewModel = viewModel;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentSummaryBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_summary,
                container, false);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }
}

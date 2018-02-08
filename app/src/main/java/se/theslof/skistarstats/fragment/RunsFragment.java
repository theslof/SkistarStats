package se.theslof.skistarstats.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.theslof.skistarstats.R;
import se.theslof.skistarstats.adapter.LiftRidesListAdapter;
import se.theslof.skistarstats.databinding.FragmentRunsBinding;
import se.theslof.skistarstats.viewmodel.MainModel;

public class RunsFragment extends Fragment {
    private MainModel viewModel;

    public RunsFragment() {
        // Required empty public constructor
    }

    public static RunsFragment newInstance(MainModel viewModel) {
        RunsFragment fragment = new RunsFragment();
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
        FragmentRunsBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_runs,
                container, false);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recycler = getActivity().findViewById(R.id.recycler_runs);
        recycler.setAdapter(viewModel.adapter);

    }
}

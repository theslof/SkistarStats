package se.theslof.skistarstats.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.theslof.skistarstats.R;
import se.theslof.skistarstats.databinding.FragmentFriendsBinding;
import se.theslof.skistarstats.viewmodel.MainModel;

public class FriendsFragment extends Fragment {
    private MainModel viewModel;

    public FriendsFragment() {
        // Required empty public constructor
    }

    public static FriendsFragment newInstance(MainModel viewModel) {
        FriendsFragment fragment = new FriendsFragment();
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
        FragmentFriendsBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_friends,
                container, false);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recycler = getActivity().findViewById(R.id.recycler_friends);
        recycler.setAdapter(viewModel.friendsAdapter);

    }
}

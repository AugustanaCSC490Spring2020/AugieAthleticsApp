package edu.augustanacsc490spring2020.augieathletics.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import edu.augustanacsc490spring2020.augieathletics.GameActivity;
import edu.augustanacsc490spring2020.augieathletics.R;

public class HomeFragment extends Fragment {
Button Events;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Events=root.findViewById(R.id.btnUpComingEvents);

        Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), currentFixtures.class);
                startActivity(intent);
            }
        });
        return root;
    }

    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), GameActivity.class);
        startActivity(intent);
    }

}

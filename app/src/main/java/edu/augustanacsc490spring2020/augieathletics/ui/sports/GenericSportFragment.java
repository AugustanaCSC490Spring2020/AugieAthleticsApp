package edu.augustanacsc490spring2020.augieathletics.ui.sports;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.augustanacsc490spring2020.augieathletics.R;

public class GenericSportFragment extends Fragment {

    private GenericSportViewModel genericSportViewModel;
    private Button rosterButton;
    private TextView text_mBasket;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_generic_sport, container, false);
        rosterButton = root.findViewById(R.id.rosterBtn);
        goToRoster(root);
        return root;
    }

    private void goToRoster(final View view) {
        rosterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), RosterActivity.class);
                intent.putExtras(getArguments());
                startActivity(intent);
            }
        });
    }


}

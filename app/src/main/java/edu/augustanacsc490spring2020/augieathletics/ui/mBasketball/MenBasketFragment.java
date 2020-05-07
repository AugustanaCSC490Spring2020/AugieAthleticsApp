package edu.augustanacsc490spring2020.augieathletics.ui.mBasketball;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import edu.augustanacsc490spring2020.augieathletics.R;

public class MenBasketFragment extends Fragment {

    private MenBasketViewModel menBasketViewModel;
    private Button rosterButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        menBasketViewModel =
                ViewModelProviders.of(this).get(MenBasketViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mbasket, container, false);
        final TextView textView = root.findViewById(R.id.text_mbasket);
        menBasketViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        rosterButton = root.findViewById(R.id.rosterBtn);
        goToRoster(root);

        return root;
    }

    private void goToRoster(final View view) {
        rosterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), RosterActivity.class);
                startActivity(intent);
            }
        });
    }


}

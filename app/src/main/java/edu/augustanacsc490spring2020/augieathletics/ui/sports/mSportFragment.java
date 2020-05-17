package edu.augustanacsc490spring2020.augieathletics.ui.sports;

import android.os.Bundle;

import androidx.fragment.app.ListFragment;
import androidx.navigation.Navigation;

import edu.augustanacsc490spring2020.augieathletics.R;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class mSportFragment extends ListFragment implements AdapterView.OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msport, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.mSports, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        String[] mSportsArray = getResources().getStringArray(R.array.mSports);
        List<String> mSportsModified = new ArrayList<>();
        List<String> multiGenderSports = Arrays.asList("basketball", "cross country","golf","lacrosse",
                "soccer","swimming and diving", "tennis", "track and field", "volleyball");
        for (String str: mSportsArray) {
            if (multiGenderSports.contains(str.toLowerCase())) {
                mSportsModified.add("mens-" + str.toLowerCase().replace(' ', '-'));
            } else {
                mSportsModified.add(str.toLowerCase().replace(' ', '-'));
            }
        }
//        for (String str: mSportsArray) {
//            mSportsModified.add("mens-" + str.toLowerCase().replace(' ', '-'));
//        }
        Bundle sportName = new Bundle();
        sportName.putString("sportName", mSportsModified.get(position));
        Navigation.findNavController(view).navigate(R.id.action_nav_msport_to_nav_generic_sport,
                sportName);
    }
}

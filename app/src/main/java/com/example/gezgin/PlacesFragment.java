package com.example.gezgin;



import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gezgin.databinding.PlacesFragmentBinding;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class PlacesFragment extends Fragment {
    private ArrayList<Places> placesArrayList;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PlacesFragmentBinding design;
        design = DataBindingUtil.inflate(inflater, R.layout.places_fragment, container, false);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireActivity(), R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        design.spinner.setAdapter(adapter);
        design.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        getLocation("hospital");
                        break;
                    case 1:
                        getLocation("cafe");
                        break;
                    case 2:
                        getLocation("shopping_mall");
                        break;
                    case 3:
                        getLocation("local_government_office");
                        break;
                    case 4:
                        getLocation("restaurant");
                        break;
                    case 5:
                        getLocation("pharmacy");
                        break;
                    case 6:
                        getLocation("gym");
                        break;
                    case 7:
                        getLocation("bank");
                        break;
                    case 8:
                        getLocation("atm");
                        break;
                    case 9:
                        getLocation("gas_station");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        design.getplacesbutton.setOnClickListener(view -> {
            RvAdapter rvAdapter = new RvAdapter(placesArrayList);
            design.rv.setHasFixedSize(true);
            design.rv.setLayoutManager(new LinearLayoutManager(requireActivity().getApplicationContext()));
            design.rv.setAdapter(rvAdapter);
            Bundle bundle = new Bundle();
            bundle.putString("apply","confirm");
            getParentFragmentManager().setFragmentResult("requestKey", bundle);
            sharedViewModel.setArrayListMutableLiveData(placesArrayList);
        });

        return design.getRoot();
    }

    public void getLocation(String location) {
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.0007655,28.8503406&type="+location+"&radius=3000&key=AIzaSyAWQf93dsF1xtomC2OaDpj1cMXj9dookOg";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                placesArrayList = new ArrayList<>();
                JSONObject jsonObject = new JSONObject(response);
                JSONArray places = jsonObject.getJSONArray("results");
                for (int i=0 ; i<places.length();i++){
                    JSONObject place = places.getJSONObject(i);
                    String place_name = place.getString("name");
                    String place_rating = place.getString("rating");
                    String place_icon = place.getString("icon");

                    JSONObject geometry = place.getJSONObject("geometry");
                    JSONObject location1 = geometry.getJSONObject("location");
                    String lat = location1.getString("lat");
                    String lng = location1.getString("lng");

                    Places places1 = new Places(place_name,place_icon,Double.parseDouble(lat),Double.parseDouble(lng),Double.parseDouble(place_rating));
                    placesArrayList.add(places1);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        });
        Volley.newRequestQueue(requireActivity()).add(stringRequest);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }
}

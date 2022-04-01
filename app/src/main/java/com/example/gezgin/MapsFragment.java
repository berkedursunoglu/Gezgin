package com.example.gezgin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.Manifest;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;


import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Logger;


public class MapsFragment extends Fragment {
    private PlacesFragment placesFragment;
    private ArrayList<Places> placesArrayList = new ArrayList<>();
    private SharedViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);


        maps();
        return view;
    }

    public void maps() {
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity().getApplicationContext());
        ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    double currentLat = location.getLatitude();
                    double currentLong = location.getLongitude();
                    SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLat, currentLong), 15));
                            googleMap.addMarker(new MarkerOptions().position(new LatLng(currentLat, currentLong)).title("Buradasınız"));
                        }
                    });
                }
            }
        });
    }

    public void updateMaps() {
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity().getApplicationContext());
        ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    double currentLat = location.getLatitude();
                    double currentLong = location.getLongitude();
                    SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            googleMap.clear();
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLat, currentLong), 15));
                            googleMap.addMarker(new MarkerOptions().position(new LatLng(currentLat, currentLong)).title("Buradasınız"));
                            viewModel.getArrayListMutableLiveData().observe(requireActivity(), new Observer<ArrayList<Places>>() {
                                @Override
                                public void onChanged(ArrayList<Places> places) {
                                    for (int i = 0; i < places.size(); i++) {
                                        googleMap.addMarker(new MarkerOptions().position(new LatLng(places.get(i).getPlace_lat(),places.get(i).getPlace_lng())).title(places.get(i).getPlace_name()));
                                    }
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("requestKey", requireActivity(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String response = result.getString("apply");
                if (response.equals("confirm")) {
                    updateMaps();
                }
            }
        });
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

}
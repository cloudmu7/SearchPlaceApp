package com.cloud7mu7.searchplaceapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SearchMapFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                MainActivity ma = (MainActivity) getActivity();

                //구글지도 위도/경도 객체
                LatLng me = new LatLng(ma.mylocation.getLatitude(), ma.mylocation.getLongitude());
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(me, 16));

                //구글맵에 내위치 표시 설정
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                //몇가지 지도 설정
                googleMap.getUiSettings().setZoomControlsEnabled(true);
                googleMap.getUiSettings().setZoomGesturesEnabled(true);

                for (Place place : ma.searchLocalApiResponse.documents){
                    double latitode = Double.parseDouble(place.y);
                    double longitude = Double.parseDouble(place.x);
                    LatLng position = new LatLng(latitode, longitude);

                    //마커 옵션 객체를 통해 마커의 설정
                    MarkerOptions options = new MarkerOptions().position(position).title(place.place_name).snippet(place.distance + "m");
                    googleMap.addMarker(options);
                }

            }
        });
    }
}

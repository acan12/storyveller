package core.fragment;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.xzone.app.storyveller.R;

/**
 * Created by arysuryawan on 10/26/15.
 */
public class MapBoxFragment extends Fragment {

    MapView mapView;
    private GoogleMap map;
    private Double latitude, longitude;
    public LocationManager locationManager;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        // inflat and return the layout
        mapView = (MapView) rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();//needed to get the map to display immediately

        try {
            MapsInitializer.initialize(this.getContext());
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

        map = mapView.getMap();

        LatLng sydney = new LatLng(-33.867, 151.206);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));



        return rootView;
    }


    private void setMapView() {
        try {

            LatLng sydney = new LatLng(-33.867, 151.206);

            map.setMyLocationEnabled(true);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

            map.addMarker(new MarkerOptions()
                    .title("Sydney")
                    .snippet("The most populous city in Australia.")
                    .position(sydney));

            map = mapView.getMap();

        } catch (Exception e) {

        }

    }
}

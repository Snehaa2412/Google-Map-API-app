 package com.example.map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.map.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolygonOptions;

import java.io.IOException;
import java.util.ArrayList;

 public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

     private GoogleMap mMap;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);


         setContentView(R.layout.activity_maps);

         SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
         mapFragment.getMapAsync(this);


     }

     @Override
     public void onMapReady(GoogleMap googleMap) {
       mMap = googleMap;
       LatLng jdplatLng=new LatLng(26.2389,73.0243);
       MarkerOptions markerOptions=new MarkerOptions().position(jdplatLng).title("Jodhpur");
       mMap.addMarker(markerOptions);
       mMap.moveCamera(CameraUpdateFactory.newLatLng(jdplatLng));

       mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(jdplatLng,16f));

       //circle
         mMap.addCircle(new CircleOptions().center(jdplatLng).radius(1000)
                 .fillColor(Color.GREEN)
                 .strokeColor(Color.DKGRAY));
       //polygon
         mMap.addPolygon(new PolygonOptions().add(new LatLng(26.2389,73.0243),
                 new LatLng(26.2389,74.0243),
                 new LatLng(27.2389,74.0243),
                 new LatLng(27.2389,73.0243),
                 new LatLng(26.2389,73.0243)).fillColor(Color.GREEN).strokeColor(Color.DKGRAY));

         mMap.addGroundOverlay(new GroundOverlayOptions().position(jdplatLng,1000f,1000f)
                          .image(BitmapDescriptorFactory.fromResource(R.drawable.android))
         .clickable(true));
       //  mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

         mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
             @Override
             public void onMapClick(LatLng latLng) {
                 mMap.addMarker(new MarkerOptions().position(latLng).title("Clicked here"));
                 Geocoder geocoder=new Geocoder(MapsActivity.this);
                 try {
                     ArrayList<Address> arrAdr =(ArrayList<Address>) geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
                     Log.d("Addr",arrAdr.get(0).getAddressLine(0));

                 } catch (IOException e) {
                     e.printStackTrace();
                 }

             }
         });



     }
 }
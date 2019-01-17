package com.smarthomesystem.ju.smarthomesystemapplication.fragments;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.Manifest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.smarthomesystem.ju.smarthomesystemapplication.R;
import com.smarthomesystem.ju.smarthomesystemapplication.mapmodels.DirectionsJSONParser;
import com.smarthomesystem.ju.smarthomesystemapplication.mapmodels.LocationData;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {

    ArrayList<LocationData> markersArray;
    LocationData locationData;
    private View rootView;
    private GoogleMap mGoogleMap;

    private MapView mMapView;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private float zoom = 17;
    private Marker userMarker = null;


    private android.app.Fragment currentFragment;
    private FragmentTransaction transaction;
    private FragmentManager manager;

    private double currentLat;
    private double currentLng;
    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_map, container, false);

        if (googleServicesAvailable()) {
            Toast.makeText(getActivity(), "Perfect", Toast.LENGTH_SHORT).show();
            mMapView = (MapView) rootView.findViewById(R.id.mapView);
            mMapView.onCreate(savedInstanceState);
            mMapView.onResume();

            mMapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap mMap) {
                    mGoogleMap = mMap;

                    // For showing a move to my location button
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    mGoogleMap.setMyLocationEnabled(true);

                    // For dropping a marker at a point on the Map
                    if (locationServicesEnabled(getActivity())) {
                        Toast.makeText(getActivity(), "locationServicesEnabled", Toast.LENGTH_SHORT).show();
                        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                                .addApi(LocationServices.API)
                                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                                    @Override
                                    public void onConnected(@Nullable Bundle bundle) {
                                        mLocationRequest = LocationRequest.create();
                                        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                                        //mLocationRequest.setInterval(20000);

                                        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                            // TODO: Consider calling
                                            //    ActivityCompat#requestPermissions
                                            // here to request the missing permissions, and then overriding
                                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                            //                                          int[] grantResults)
                                            // to handle the case where the user grants the permission. See the documentation
                                            // for ActivityCompat#requestPermissions for more details.
                                            return;
                                        }
                                        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, new LocationListener() {
                                            @Override
                                            public void onLocationChanged(Location location) {
                                                if (location == null) {
                                                    Toast.makeText(getActivity(), "Cant get Current location", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    double lat = location.getLatitude();
                                                    double lng = location.getLongitude();

                                                    currentLat = lat;
                                                    currentLng = lng;

                                                    LatLng latLng = new LatLng(lat, lng);
                                                    CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
                                                    mGoogleMap.animateCamera(update);
                                                    SetCarLocation(lat, lng);
                                                    try {
                                                        removeMarker("user");
                                                        mapMaker(lat, lng, "user");

                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }

                                                  //  DrawRouteMap(new LatLng(currentLat,currentLng),new LatLng(currentLat+0.000182, currentLng+0.003094));

                                                }
                                            }
                                        });
                                    }

                                    @Override
                                    public void onConnectionSuspended(int i) {

                                    }
                                })
                                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                                    @Override
                                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                                    }
                                })
                                .build();

                        mGoogleApiClient.connect();
                    } else {
                        Toast.makeText(getActivity(),"Please turn on location service",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            Toast.makeText(getActivity(), "No google Map service", Toast.LENGTH_SHORT).show();
        }
        return rootView;
    }

    public void SetCarLocation(double lat, double lng)
    {

        markersArray = new ArrayList<LocationData>();

        markersArray.clear();
        mGoogleMap.clear();

        locationData = new LocationData();
        locationData.setLatitude(lat+ 0.000182);
        locationData.setLongitude(lng+ 0.003094);
        locationData.setTitle("Hasan");
        locationData.setSnippet("hasan@gmail.com");

        markersArray.add(locationData);


        locationData = new LocationData();
        locationData.setLatitude(lat+ 0.000182);
        locationData.setLongitude(lng+ 0.006014);
        locationData.setTitle("Arif");
        locationData.setSnippet("arif@gmail.com");

        markersArray.add(locationData);

        locationData = new LocationData();
        locationData.setLatitude(lat+ 0.005382);
        locationData.setLongitude(lng+ 0.001044);
        locationData.setTitle("Sultan");
        locationData.setSnippet("sultan@gmail.com");

        markersArray.add(locationData);

        for(int i = 0 ; i < markersArray.size() ; i++ ) {

            createMarker(markersArray.get(i).getLatitude(), markersArray.get(i).getLongitude(), markersArray.get(i).getTitle(), markersArray.get(i).getSnippet());


        }

    }

    protected Marker createMarker(double latitude, double longitude, String title, String snippet) {

        return mGoogleMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.human_walking))
        );
    }

    public boolean googleServicesAvailable() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int isAvailable = apiAvailability.isGooglePlayServicesAvailable(getActivity());
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (apiAvailability.isUserResolvableError(isAvailable)) {
            Dialog dialog = apiAvailability.getErrorDialog(getActivity(), isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(getActivity(), "Can't connect to play services", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private boolean locationServicesEnabled(Activity mapActivity) {
        LocationManager lm = (LocationManager) mapActivity.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean net_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            Log.e("Exception gps_enabled", "Exception gps_enabled");
        }

        try {
            net_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
            Log.e("Exception net_enabled", "Exception network_enabled");
        }
        return gps_enabled || net_enabled;
    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public void mapMaker(double lat, double lng , String status) throws IOException {
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
        String cityName = addresses.get(0).getAddressLine(0);
        String stateName = addresses.get(0).getAddressLine(1);
        String countryName = addresses.get(0).getAddressLine(2);

        Log.d("cityName",cityName);
        //    Log.d("stateName",stateName);
        //   Log.d("countryName",countryName);

        if(status.equals("user")){
            MarkerOptions options = new MarkerOptions()
                    .title(cityName)
                    .position(new LatLng(lat,lng))
                    .snippet(status)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.human_current_position));

            userMarker = mGoogleMap.addMarker(options);

        }
    }

    public void removeMarker(String type){
        if(type.equals("user")){
            if(userMarker!=null){
                userMarker.remove();
            }
        }
    }

    private void DrawRouteMap(LatLng origin, LatLng dest)
    {
        String url = getDirectionsUrl(origin, dest);

        DownloadTask downloadTask = new DownloadTask();

        // Start downloading json data from Google Directions API
        downloadTask.execute(url);
    }

//    private void drawRouteOnMap(GoogleMap map, List<LatLng> positions) {
//
//        PolylineOptions options = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
//        options.addAll(positions);
//        Polyline polyline = map.addPolyline(options);
//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(new LatLng(positions.get(1).latitude, positions.get(1).longitude))
//                .zoom(17)
//                .build();
//        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Ex to download url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }


    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";
            String duration = "";

            if (result.size() < 1) {
                Toast.makeText(getActivity(), "No Points", Toast.LENGTH_SHORT).show();
                return;
            }

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    if (j == 0) {    // Get distance from the list
                        distance = (String) point.get("distance");
                        continue;
                    } else if (j == 1) { // Get duration from the list
                        duration = (String) point.get("duration");
                        continue;
                    }

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(2);
                lineOptions.color(Color.RED);
            }

            String txt = ("Distance:" + distance + ", Duration:" + duration);

            Toast.makeText(getActivity(), txt, Toast.LENGTH_SHORT).show();

            // Drawing polyline in the Google Map for the i-th route
            mGoogleMap.addPolyline(lineOptions);
        }
    }

}


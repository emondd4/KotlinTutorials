package com.charityright.locationpicker

import android.Manifest.permission
import android.app.ProgressDialog
import android.content.IntentSender.SendIntentException
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.widget.TextView.OnEditorActionListener
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import java.lang.Exception
import java.lang.NullPointerException
import java.util.ArrayList

class MainActivity : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback,LocationListener {

    val TAG = "LocationActivity"


    lateinit var mapView: MapView
    lateinit var googleMap: GoogleMap
    lateinit var apply:TextView
    lateinit var myLocation: ImageView


    val REQUEST_CHECK_SETTINGS_GPS = 0x1
    val REQUEST_ID_MULTIPLE_PERMISSIONS = 0x2
    var lat = 0.0
    var lon = 0.0
    var googleApiClient: GoogleApiClient? = null
    var center: LatLng? = null
    var mylocation: Location? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)

        myLocation = findViewById(R.id.my_location)
        apply = findViewById(R.id.apply)

        val permissionLocation = ContextCompat.checkSelfPermission(
            this@MainActivity,
            permission.ACCESS_FINE_LOCATION
        )

        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(permission.ACCESS_FINE_LOCATION)
            if (listPermissionsNeeded.isNotEmpty()) {
                ActivityCompat.requestPermissions(
                    this,
                    listPermissionsNeeded.toTypedArray(),
                    REQUEST_ID_MULTIPLE_PERMISSIONS
                )
            }
        } else {
            loadData()
        }


        // Gets to GoogleMap from the MapView and does initialization stuff
        mapView.getMapAsync(this)

        myLocation.setOnClickListener {
            getMyLocation()
        }

        apply.setOnClickListener {
            Toast.makeText(this,"Latitude: $lat && Longitude: $lon",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onConnected(p0: Bundle?) {
        checkPermissions()
    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun checkPermissions() {
        val permissionLocation = ContextCompat.checkSelfPermission(
            this@MainActivity,
            permission.ACCESS_FINE_LOCATION
        )
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(permission.ACCESS_FINE_LOCATION)
            if (listPermissionsNeeded.isNotEmpty()) {
                ActivityCompat.requestPermissions(
                    this,
                    listPermissionsNeeded.toTypedArray(),
                    REQUEST_ID_MULTIPLE_PERMISSIONS
                )
            }
        } else {
            getMyLocation()
        }
    }

    /**
     * Function for get the lat, lon from gps
     */
    private fun loadData() {
        if (lat == 0.0 && lon == 0.0) {
            if (googleApiClient == null) {
                setUpGClient()
            } else if (mylocation == null) {
                getMyLocation()
            } else {
                lat = mylocation!!.latitude
                lon = mylocation!!.longitude
                Log.v("lat&lon", "lat = $lat&lon=$lon")
            }
        } else {
        }
    }

    @Synchronized
    private fun setUpGClient() {
        googleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this, 0, this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()
        googleApiClient!!.connect()
    }

    override fun onMapReady(map: GoogleMap) {
        Log.v(TAG, "map=$map")
        googleMap = map
        if (map != null) {
            map.uiSettings.isMyLocationButtonEnabled = false
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            } else {
                map.isMyLocationEnabled = true
            }

            if (lat == 0.0 && lon == 0.0) {
                when {
                    googleApiClient == null -> {
                        setUpGClient()
                    }
                    mylocation == null -> {
                        getMyLocation()
                    }
                    else -> {
                        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                            LatLng(
                                mylocation!!.latitude,
                                mylocation!!.longitude
                            ), 15f
                        )
                        map.animateCamera(cameraUpdate)
                    }
                }
            } else {
                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng(lat, lon), 15f)
                map.animateCamera(cameraUpdate)
            }


            map.setOnCameraChangeListener(OnCameraChangeListener { // TODO Auto-generated method stub
                center = map.getCameraPosition().target
                Log.v(
                    TAG,
                    "center-latitude=" + center!!.latitude + " &center-longitude=" + center!!.longitude
                )
                lat = center!!.latitude
                lon = center!!.longitude
                map.clear()
            })
        }
    }

    private fun getMyLocation() {
        if (googleApiClient != null) {
            if (googleApiClient!!.isConnected) {
                val permissionLocation = ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    permission.ACCESS_FINE_LOCATION
                )
                if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                    mylocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient)
                    val locationRequest = LocationRequest()
                    locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                    val builder = LocationSettingsRequest.Builder()
                        .addLocationRequest(locationRequest)
                    builder.setAlwaysShow(true)
                    LocationServices.FusedLocationApi.requestLocationUpdates(
                        googleApiClient,
                        locationRequest,
                        this@MainActivity
                    )
                    val result: PendingResult<*> = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build())

                    result.setResultCallback {
                        ResultCallback<LocationSettingsResult> { result ->
                            val status = result.status
                            when (status.statusCode) {
                                LocationSettingsStatusCodes.SUCCESS -> {
                                    // All location settings are satisfied.
                                    // You can initialize location requests here.
                                    val permissionLocation = ContextCompat
                                        .checkSelfPermission(
                                            this@MainActivity,
                                            permission.ACCESS_FINE_LOCATION
                                        )
                                    if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                                        mylocation = LocationServices.FusedLocationApi
                                            .getLastLocation(googleApiClient)
                                        if (mylocation != null) {
                                            lat = mylocation!!.latitude
                                            lon = mylocation!!.longitude
                                        }
                                        Log.v("mylocation", "mylocation=$mylocation")
                                    }
                                }
                                LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->                                     // Location settings are not satisfied.
                                    // But could be fixed by showing the user a dialog.
                                    try {
                                        // Show the dialog by calling startResolutionForResult(),
                                        // and check the result in onActivityResult().
                                        // Ask to turn on GPS automatically
                                        status.startResolutionForResult(
                                            this@MainActivity,
                                            REQUEST_CHECK_SETTINGS_GPS
                                        )
                                    } catch (e: SendIntentException) {
                                        // Ignore the error.
                                    }
                                LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onLocationChanged(location: Location?) {
        mylocation = location
        googleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    mylocation!!.latitude,
                    mylocation!!.longitude
                ), 15f
            )
        )
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this)
    }

}
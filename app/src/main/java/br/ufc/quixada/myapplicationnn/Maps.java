package br.ufc.quixada.myapplicationnn;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import br.ufc.quixada.myapplicationnn.Entidades.Usuario;
import br.ufc.quixada.myapplicationnn.databinding.ActivityMapsBinding;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    GoogleMap googleMap2;
    Usuario user = new Usuario();
    String latitude,longitude;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if(intent != null){
            latitude = intent.getStringExtra("latitude");
            longitude = intent.getStringExtra("longitude");
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void novaPosicao() {

        mMap = googleMap2;

        googleMap2.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                googleMap2.clear();
                String latitude = String.valueOf(latLng.latitude);
                String longitude = String.valueOf(latLng.longitude);

                // Add a marker in Sydney and move the camera

                LatLng sydney = new LatLng(latLng.latitude, latLng.longitude);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Fortaleza"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                db.collection("usuarios").document(id).update("latitude",latitude,"longitude",longitude).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Maps.this,"Posição atualizado",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        this.googleMap2 = googleMap;

        if(latitude == "0" && longitude == "0"){
            LatLng sydney = new LatLng(-3.7931403, -38.5896562);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Localização atual"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            novaPosicao();
        }else{

            Double dLatitude = Double.valueOf(latitude);
            Double dLongitude = Double.valueOf(longitude);

            LatLng sydney = new LatLng(dLatitude, dLongitude);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Localização atual"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            novaPosicao();
        }


    }

}
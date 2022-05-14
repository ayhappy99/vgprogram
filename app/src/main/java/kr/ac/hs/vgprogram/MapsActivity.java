package kr.ac.hs.vgprogram;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import kr.ac.hs.vgprogram.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // 구글 맵 객체를 불러온다.
        mMap = googleMap;

        // 1. 마커 옵션 설정 (만드는 과정)
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                .position(new LatLng(37.557829, 126.942371))
                .title("베지베어"); // 타이틀.
                markerOptions.snippet("비건");
        // 마커를 생성한다.
        mMap.addMarker(markerOptions);

        markerOptions = new MarkerOptions();
        markerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                .position(new LatLng(37.557580, 126.905588))
                .title("어라운드 그린"); // 타이틀.
        markerOptions.snippet("폴로 베지테리언");


        // 마커를 생성한다.
        mMap.addMarker(markerOptions);

        markerOptions = new MarkerOptions();
        markerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                .position(new LatLng(37.554116, 126.929955))
                .title("드렁큰 비건"); // 타이틀.
        markerOptions.snippet("페스코 베지테리언");

        // 마커를 생성한다.
        mMap.addMarker(markerOptions);

        markerOptions = new MarkerOptions();
        markerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                .position(new LatLng(37.523633, 127.023053))
                .title("마히나 비건 테이블"); // 타이틀.
        markerOptions.snippet("플렉시 테리언");

        // 마커를 생성한다.
        mMap.addMarker(markerOptions);

        markerOptions = new MarkerOptions();
        markerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                .position(new LatLng(37.546024, 126.985174))
                .title("바이두부 (by TOFU)"); // 타이틀.
        markerOptions.snippet("오보 베지테리언");

        // 마커를 생성한다.
        mMap.addMarker(markerOptions);

        markerOptions = new MarkerOptions();
        markerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                .position(new LatLng(37.476933, 127.047345))
                .title("베지 그린"); // 타이틀.
        markerOptions.snippet("페스코 베지테리언");

        // 마커를 생성한다.
        mMap.addMarker(markerOptions);

        markerOptions = new MarkerOptions();
        markerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                .position(new LatLng(37.533087, 126.994238))
                .title("플랜트"); // 타이틀.
        markerOptions.snippet("비건");

        // 마커를 생성한다.
        mMap.addMarker(markerOptions);

        markerOptions = new MarkerOptions();
        markerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                .position(new LatLng(37.543674, 126.987254))
                .title("베제투스"); // 타이틀.
        markerOptions.snippet("폴로 베지테리언");

        // 마커를 생성한다.
        mMap.addMarker(markerOptions);


        markerOptions = new MarkerOptions();
        markerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                .position(new LatLng(37.496211, 126.997112))
                .title("푸드더즈매터"); // 타이틀.
        markerOptions.snippet("플렉시테리언");

        // 마커를 생성한다.
        mMap.addMarker(markerOptions);

        markerOptions = new MarkerOptions();
        markerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                .position(new LatLng(37.545833, 126.920821))
                .title("슬런치 팩토리"); // 타이틀.
        markerOptions.snippet("오보 베지테리언");

        // 마커를 생성한다.
        mMap.addMarker(markerOptions);

            // 카메라를 위치로 옮긴다.
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(37.526459, 126.980118)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.526459,126.980118),12));//지도를 14배율로 확대해서 보여줌

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                if(marker.getTitle().equals("드렁큰 비건")){
                    String Url = "https://vgreservation.modoo.at/?link=yhzs49wu";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Url));
                    startActivity(intent);
                }
                return false;
            }
        });
    }
}
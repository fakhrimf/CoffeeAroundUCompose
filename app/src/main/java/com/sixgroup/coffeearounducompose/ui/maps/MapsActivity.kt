package com.sixgroup.coffeearounducompose.ui.maps

import android.os.Bundle
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.MapsInitializer.Renderer
import com.google.android.gms.maps.OnMapsSdkInitializedCallback
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.sixgroup.coffeearounducompose.model.TokoModel
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.ui.theme.DarkBrown
import com.sixgroup.coffeearounducompose.ui.theme.MontSerrat
import com.sixgroup.coffeearounducompose.utils.Constants.TOKO_DETAIL

class MapsActivity : ComponentActivity(), OnMapsSdkInitializedCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapsInitializer.initialize(applicationContext, Renderer.LATEST, this)
        setContent {
            CoffeeAroundUComposeTheme {
                val toko = intent.getParcelableExtra<TokoModel>(TOKO_DETAIL)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MapsView(activity = this, tokoModel = toko!!)
                }
            }
        }
    }

    override fun onMapsSdkInitialized(p0: Renderer) {
        when (p0) {
            Renderer.LATEST -> Log.d("MapsDemo", "The latest version of the renderer is used.")
            Renderer.LEGACY -> Log.d("MapsDemo", "The legacy version of the renderer is used.")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsView(tokoModel: TokoModel, activity: ComponentActivity) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lokasi Toko",
                        color = DarkBrown,
                        fontFamily = MontSerrat,
                        fontWeight = FontWeight.Medium
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { activity.onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Filled.ChevronLeft,
                            contentDescription = "back",
                            tint = DarkBrown.copy(0.5f)
                        )
                    }
                }
            )
        }
    ) {
        val loc = LatLng(tokoModel.lat.toDouble(), tokoModel.lon.toDouble())
        Log.d("LATLON", "MapsView: ${tokoModel.lat.toDouble()}, ${tokoModel.lon.toDouble()}")
        val cam = rememberCameraPositionState { CameraPosition.fromLatLngZoom(loc, 10f) }
        GoogleMap(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            cameraPositionState = cam,

        ) {
            Marker(
                state = MarkerState(position = loc),
                title = tokoModel.nama,
                snippet = tokoModel.address
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview3() {
//    CoffeeAroundUComposeTheme {
//        MapsView()
//    }
//}
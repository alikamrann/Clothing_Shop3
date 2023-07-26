package com.alikamran.clothingshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alikamran.clothingshop.ui.NiaApp
import com.alikamran.clothingshop.ui.theme.ClothingShopTheme
import com.alikamran.data.NetworkMonitor

class MainActivity : ComponentActivity() {


    lateinit var networkMonitor: NetworkMonitor
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClothingShopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NiaApp(
                        networkMonitor = networkMonitor,
                        windowSizeClass = calculateWindowSizeClass(this),

                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClothingShopTheme {
        Greeting("Android")
    }


}
val bottomNavigationItems = listOf(
    BottomNavigationItem(
        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
        label = { Text("Home") },
        selected = currentTab == Tab.Home,
        onClick = { currentTab = Tab.Home }
    ),
    BottomNavigationItem(
        icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
        label = { Text("Favorites") },
        selected = currentTab == Tab.Favorites,
        onClick = { currentTab = Tab.Favorites }
    ),
    // Add more items as needed
)
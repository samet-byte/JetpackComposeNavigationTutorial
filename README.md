# Jetpack Compose Navigation

## Step 1: Dependencies

Make sure to include the necessary dependencies in your app's `build.gradle` file:

#### .gradle
```gradle
implementation "androidx.navigation:navigation-compose:2.7.6"
```
#### .kts
```kts
implementation("androidx.navigation:navigation-compose:2.7.6")
```

## Step 2: Define Screens and Keys
```kotlin
object Screen {
    const val MainScreen = "main_screen"
    const val DetailsScreen = "details_screen"
}

object Keys {
    const val USER = "user"
    // Add more keys if needed
}
```

Here, we define the screen names and keys that will be used for navigation.

## Step 3: Navigation Composable
```kotlin
@Composable
fun Navigation() {
    val navigationController = rememberNavController()

    NavHost(
        navController = navigationController,
        startDestination = Screen.MainScreen
    ) {
        composable(route = Screen.MainScreen) {
            MainScreen(navController = navigationController)
        }
        composable(
            route = "${Screen.DetailsScreen}/{${Keys.USER}}",
            arguments = listOf(
                navArgument(Keys.USER) {
                    type = NavType.StringType
                    defaultValue = Default.DEFAULT_USER
                    nullable = true
                }
            )
        ) { entry ->
            DetailsScreen(name = entry.arguments?.getString(Keys.USER))
        }
    }
}
```

This sets up the NavHost with two composables for the main screen and details screen. It also defines the dynamic argument for the details screen.

## Step 4: MainScreen Composable
```kotlin
@Composable
fun MainScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                navController.navigate(
                    "${Screen.DetailsScreen}/${text.takeIf { it.isNotEmpty() } ?: Default.DEFAULT_USER}"
                )
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("To Details Screen")
        }
    }
}
```

In the MainScreen Composable, we capture user input and navigate to the details screen using the navController.navigate method.

## Step 5: DetailsScreen Composable
```kotlin
@Composable
fun DetailsScreen(name: String?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "$name, hi there!")
    }
}
```
Finally, the DetailsScreen Composable displays a greeting using the received name argument.

## Step 6: Integration
In your main activity or entry point, call the Navigation Composable:
```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}
```

This is a basic example, and you can extend it based on your app's requirements. Adjust the screens, keys, and navigation logic as needed.

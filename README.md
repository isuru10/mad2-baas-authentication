This is the starter code for BaaS Authentication demo.

## Step 1 - Clone repo and checkout branch

Clone this repo and checkout `starter` branch. The `finished` branch contains the finished version.
Please go through the steps by yourself before checking out the finished version.

## Step 2 - Setup Firebase console

 - Go to `Tools -> Firebase -> Authentication` and select "Authenticate using a custom authentication system"
 - Click "Connect to Firebase"
 - Your browser will open Firebase console
 - Continue until the screen "Your Android Studio project is connected to your Firebase Android app"
 - Come back to Android Studio
 - Click "Add the Firebase Authentication SDK to your app"
 - Click "Accept changes" and let it sync
 - After syncing close the Firebase Assistant panel in Android Studio

## Step 3 - Enable authentication

 - Go to [Firebase Console](https://console.firebase.google.com/)
 - Select the project you created on Step 2
 - Select `Build -> Authentication` from side navigation menu
 - Select "Get Started"
 - Select "Email/Password" under "Native Providers"
 - Enable "Email/Password" and hit Save

## Step 4 - Create AuthViewModel

 - Create a new Kotlin class named `AuthViewModel`
 - Extend it with `ViewModel` class as follows
```kotlin
class AuthViewModel : ViewModel() {
    
}
```
 - Get the Firebase Auth instance as follows
```kotlin
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
```

 - Create `AuthState` sealed class to keep track of the authentication state as follows
```kotlin
sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message : String) : AuthState()
}
```

 - Refer the AuthState in the ViewModel as follows
```kotlin
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState
```

 - Implement `checkAuthStatus` and call it in `init` as follows
```kotlin
    init {
        checkAuthStatus()
    }

    fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }
```

 - Implement the `login` function as follows
```kotlin
fun login(email: String, password: String) {

    if (email.isEmpty() || password.isEmpty()) {
        _authState.value = AuthState.Error("Email or password cannot be empty")
        return
    }

    _authState.value = AuthState.Loading
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                _authState.value = AuthState.Authenticated
            } else {
                _authState.value = AuthState.Error(task.exception?.message?:"Something went wrong")
            }
        }
}
```

 - Challenge: Implement the `signup` and `signOut` functions with your knowledge so far

## Step 5 - Implement and test signup function

 - Update `AppNavigation` to require `authViewModel` as follows
```kotlin
fun AppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    // Same as before
}
```
 - Update `SignupScreen` to require `authViewModel` as follows
```kotlin
fun SignupScreen(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {
    // Same as before
}
```
 - Update `MainActivity` to create `authViewModel` as follows
```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Retrieve AuthViewModel
        val authViewModel : AuthViewModel by viewModels()
        setContent {
            AuthExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Call AppNavigation with authViewModel
                    AppNavigation(modifier = Modifier.padding(innerPadding), authViewModel)
                }
            }
        }
    }
}
```
 - Update the Signup button in the `SignupScreen` as follows
```kotlin
Button(onClick = {
    authViewModel.signup(email, password)
}) {
    Text(text = "Signup")
}
```
 - Run the application
 - Navigate to signup screen
 - Enter an email and a password with at least 6 characters and press signup
 - Got to the [Firebase Console](https://console.firebase.google.com/)
 - Navigate to `Build -> Authentication -> Users` and check if a new user is created

## Step 6 - Implement signup navigation

 - Retrieve the `authState` from the `AuthViewModel` and navigate to home if authenticated as follows
```kotlin
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current
    
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate(AppRoutes.Home.route)
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT
            ).show()
    
            else -> Unit
        }
    }
```
 - Update `AppNavigation` and `HomeScreen` to require `AuthViewModel` as done previously in step 5
 - Add a `LaunchedEffect` to navigate to login screen if unauthenticated as follows
```kotlin
    val authState = authViewModel.authState.observeAsState()
    
    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }
```
 - Update the sign out button as follows to call the `signOut` method
```kotlin
    Button(onClick = {
        authViewModel.signOut()
    }) {
        Text(text = "Logout")
    }
```
 - Run the application and test signup and sign out process

## Step 7 - Challenge: Implement login

 - Use the knowledge you gained so far to implement the login flow


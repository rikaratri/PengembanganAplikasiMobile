package edu.uksw.fti.pam.pam_activity_intent

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import edu.uksw.fti.pam.pam_activity_intent.ui.theme.PAM_Activity_IntentTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAM_Activity_IntentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginForm()
                   // MainScreen()
                }
            }
        }
    }
}

internal fun doAuth(
    username:String,
    password:String,
):Boolean{
    return (username.equals("admin")&& password.equals("admin"))
}

@Composable
fun LoginForm(){
    val lContext = LocalContext.current
    var usernameInput by remember {mutableStateOf("")}
    var passwordInput by remember {mutableStateOf("")}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
       
        TextField(
            value =usernameInput.toString() ,
            onValueChange = {usernameInput = it } ,
            label = {Text(text="Username")},
            modifier = Modifier.fillMaxWidth(),
        )
        TextField(
            value = passwordInput.toString(),
            onValueChange = {passwordInput = it },
            label = {Text(text="Password")},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
        )
        Button(onClick = {
            val isAuthenticated = doAuth(usernameInput, passwordInput)
            if(isAuthenticated) {
                lContext.startActivity(
                    Intent(lContext, HomeActivity::class.java)
                        .apply {
                            putExtra("username", usernameInput)
                        }
                )
            }
        }
        )
        {
            Text(text = "Login")
        }
    }
}





@Preview(showBackground = true)
@Composable
fun LoginFormPreview(){
    PAM_Activity_IntentTheme {
        LoginForm()
    }
}


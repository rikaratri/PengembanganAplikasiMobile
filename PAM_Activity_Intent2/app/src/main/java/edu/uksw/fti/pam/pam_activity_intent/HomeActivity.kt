package edu.uksw.fti.pam.pam_activity_intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlin.collections.forEach as forEach1
import androidx.compose.material.Icon
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.tiles.material.Chip
import edu.uksw.fti.pam.pam_activity_intent.ui.theme.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAM_Activity_IntentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                  //  val username = getIntent().getStringExtra("username") ?: ""
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen(){

//    Box(Modifier.verticalScroll(rememberScrollState())){
//        Image(
//            modifier = Modifier
//                .fillMaxWidth()
//                .offset(0.dp, (-30).dp),
//            painter = painterResource(id = R.drawable.ic_list),
//            contentDescription = "",
//            contentScale = ContentScale.FillWidth
//        )
//        Column() {
//           // Content()
//        }
//    }
    val navController = rememberNavController()

    Scaffold(
        topBar = {TopBar()},
        bottomBar = {BottomNavigationBar(navController)},

    ) {
        Navigation(navController)

    }
    Column(
        modifier = Modifier.padding(vertical = 20.dp, horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .width(0.dp)
                .height(25.dp)
                .background(color = Color.LightGray, shape = CircleShape)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .width(400.dp)
                    .border(border = BorderStroke(width = 1.dp, Color.DarkGray))
                    .background(Color(0xFFF5F5F5), shape = CircleShape)
                    .padding(vertical = 10.dp, horizontal = 15.dp)

            ){
                Row{
                    Icon(Icons.Default.Search,  null)
                    Spacer(modifier = Modifier.width(30.dp))
                    Text("Cari layanan, makanan, & tujuan", style = TextStyle(color = Color.Gray))

                }
            }
            Spacer(modifier = Modifier.height(10.dp))

        }

        Spacer (modifier = Modifier.height(30.dp))
        GopaySection()
        Spacer (modifier = Modifier.height(20.dp))
        Promotions()
        Spacer (modifier = Modifier.height(0.dp))
        ScreenHome()
        Spacer (modifier = Modifier.height(20.dp))
        TextBold()
        Spacer (modifier = Modifier.height(30.dp))
        Promotions1()
    }

}

@Composable
fun TextBold(){
    Column{
        Text("Belanja Makin Hemat", fontWeight = FontWeight.Bold)
        Text("Dapetin diskon dan harga specialnya di ", fontFamily = FontFamily.SansSerif)
        Text("TokoPedia Sekarang juga!", fontFamily = FontFamily.SansSerif)
    }
}


@Composable
fun TopBar(){

    val context = LocalContext.current

    TopAppBar(
//        title = {
//            Box(modifier = Modifier.fillMaxWidth()) {
//                Image(
//                    painterResource(id = R.drawable.logo), "Logo",
//                    modifier = Modifier.size(80.dp).align(Alignment.Center)
//                )
//            }
//        },


        title = {Text(text = "          GOJEK", fontSize = 30.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        )},

        navigationIcon = {
                         IconButton(onClick = {
                             Toast.makeText(context, "Menu", Toast.LENGTH_SHORT).show()
                         }) {
                             Icon(Icons.Default.Menu, "Menu")
                         }},
        actions = {
            IconButton(onClick = {
                Toast.makeText(context, "Menu", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Default.Notifications, "Menu")
            }
        },
        backgroundColor = Color.Green,
        contentColor = Color.White,
    )

}

@Composable
fun BottomNavigationBar(navController: NavController){
    val items = listOf(
        Navigation.Beranda,
        Navigation.Pesanan,
        Navigation.Chat,
        Navigation.MyAccount,
    )

    BottomNavigation(
        backgroundColor = Color.Green,
        contentColor = Color.White
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach1 { items ->
            BottomNavigationItem(
                icon = {Icon(painterResource(id = items.icon), contentDescription = items.title)},
                label = {Text(text = items.title)},
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == items.route,
                onClick = {
                    navController.navigate(items.route){
                        navController.graph.startDestinationRoute?.let{ route ->
                            popUpTo(route = route){
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Home Screen",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun PesananScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Beranda Screen",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun ChatScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Chat Screen",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun MyAccountScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "MyAccount Screen",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun Navigation(navController : NavHostController){
    NavHost(navController, startDestination = Navigation.Beranda.route){
        composable(Navigation.Beranda.route){
            HomeScreen()
        }
        composable(Navigation.Pesanan.route){
            PesananScreen()
        }
        composable(Navigation.Chat.route){
            ChatScreen()
        }
        composable(Navigation.MyAccount.route){
            MyAccountScreen()
        }

    }
}

@Composable
fun GopaySection(){
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color(0xff0081a0)
    ){
        Row(
            modifier = Modifier.padding(
                horizontal = 16.dp, vertical=16.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Card(shape = RoundedCornerShape(10.dp)){
                Column(modifier = Modifier.padding(bottom = 8.dp,
                start = 16.dp, end = 16.dp)){
                    Image(
                        modifier = Modifier
                            .width(100.dp)
                            .height(40.dp),
                        painter =
                            painterResource(id = R.drawable.logo),
                            contentDescription = null,
                    )
                    Text("Rp.50.000", style = TextStyle (fontWeight = FontWeight.Bold)
                    )
                    Text("Klik & cek Riwayat", style = TextStyle (color = Green500)
                    )

                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                Image(painter = painterResource(id = R.drawable.ic_tf), contentDescription = null )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Bayar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                Image(painter = painterResource(id = R.drawable.ic_qr_code), contentDescription = null )
                Spacer(modifier = Modifier.height(8.dp))
                Text("QrCode")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                Image(painter = painterResource(id = R.drawable.ic_topup), contentDescription = null )
                Spacer(modifier = Modifier.height(8.dp))
                Text("TopUp")
            }
        }
    }
}


@Composable
fun Promotions(){
    LazyRow(
        Modifier.height(160.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        item {
            PromotionItem(
                imagePainter = painterResource(id = R.drawable.promoa),
                title = "",
                subtitle = "",
                header = "",
                backgroundColor = MaterialTheme.colors.primary
            )
        }
        item {
            PromotionItem(
                imagePainter = painterResource(id = R.drawable.promoc),
                title = "",
                subtitle = "",
                header = "",
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
fun PromotionItem(
    title : String = "",
    subtitle: String = "",
    header: String = "",
    backgroundColor : Color = Color.Transparent,
    imagePainter: Painter
){
    Card(
        Modifier.width(300.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = backgroundColor,
        elevation = 0.dp
    ){
        Row{
            Column(
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ){
                Text(text = title, fontSize= 14.sp, color = Color.White)
                Text(text = subtitle, fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold )
                  Text(text = header, fontSize = 28.sp, color = Color.White, fontWeight = FontWeight.Bold )
            }
                Image(
                painter = imagePainter, contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                alignment = Alignment.CenterEnd,
                contentScale = ContentScale.Crop
                )
        }
    }
}

@Composable
fun Promotions1(){
    LazyRow(
        Modifier.height(160.dp),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ){
        item {
            PromotionItem1(
                imagePainter = painterResource(id = R.drawable.promoc),
                title = "",
                subtitle = "",
                header = "",
                backgroundColor = MaterialTheme.colors.primary
            )
        }
        item {
            PromotionItem1(
                imagePainter = painterResource(id = R.drawable.promoa),
                title = "",
                subtitle = "",
                header = "",
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
fun PromotionItem1(
    title : String = "",
    subtitle: String = "",
    header: String = "",
    backgroundColor : Color = Color.Transparent,
    imagePainter: Painter
){
    Card(
        Modifier.width(300.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = backgroundColor,
        elevation = 0.dp
    ){
        Row{
            Column(
                Modifier
                    .padding(horizontal = 1.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ){
                Text(text = title, fontSize= 14.sp, color = Color.White)
                Text(text = subtitle, fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold )
                Text(text = header, fontSize = 28.sp, color = Color.White, fontWeight = FontWeight.Bold )
            }
            Image(
                painter = imagePainter, contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                alignment = Alignment.CenterEnd,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun ScreenHome(){
    Column{
        ChipSection(chips = listOf("POPULAR","FAVORITE","SERING DIGUNAKAN"))
    }
}

@Composable
fun ChipSection(chips: List<String>){
    var selectedChipIndex by remember{
        mutableStateOf(0)
    }
    LazyRow{
        items(chips.size){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 10.dp, top = 15.dp, bottom = 15.dp)
                    .clickable{
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(15.dp))
                    .background(
                        if(selectedChipIndex == it)
                            Green700
                    else Green500
                    )
                    .padding(15.dp)
            ){
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PAM_Activity_IntentTheme {
        MainScreen()

    }
}


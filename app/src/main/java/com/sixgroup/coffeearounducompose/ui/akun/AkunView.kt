package com.sixgroup.coffeearounducompose.ui.akun

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sixgroup.coffeearounducompose.model.UserModel
import com.sixgroup.coffeearounducompose.R
import com.sixgroup.coffeearounducompose.ui.theme.Accent
import com.sixgroup.coffeearounducompose.ui.theme.CoffeeAroundUComposeTheme
import com.sixgroup.coffeearounducompose.ui.theme.DarkBrown
import com.sixgroup.coffeearounducompose.ui.theme.Grey
import com.sixgroup.coffeearounducompose.ui.theme.MontSerrat

class AkunView {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AkunPage(modifier: Modifier = Modifier, model: UserModel, context: Context) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)
        ) {
//            Text(
//                text = "Akun Profil",
//                fontSize = 32.sp,
//                fontFamily = MontSerrat,
//                fontWeight = FontWeight.SemiBold,
//                color = DarkBrown,
//                modifier = Modifier.padding(start = 10.dp, top = 24.dp)
//            )
            Card(modifier = Modifier.padding(10.dp), colors = CardDefaults.cardColors(
                containerColor = Grey.copy(0.2f)
            )) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        elevation = CardDefaults.cardElevation(0.dp),
                        colors = CardDefaults.cardColors(containerColor = DarkBrown.copy(0.4f)),
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(CircleShape),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "edit_akun",
                            modifier = Modifier
                                .width(54.dp)
                                .height(54.dp)
                                .padding(10.dp),
                            tint = Accent
                        )
                    }
                    Column(modifier = Modifier.padding(vertical = 16.dp)) {
                        Text(
                            text = model.name,
                            fontFamily = MontSerrat,
                            fontWeight = FontWeight.SemiBold,
                            color = DarkBrown,
                            fontSize = 20.sp
                        )
                        Text(
                            text = model.phone_number,
                            fontFamily = MontSerrat,
                            fontWeight = FontWeight.Medium,
                            color = DarkBrown.copy(0.4f),
                            fontSize = 14.sp
                        )
                    }
                }
            }
            Card(modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxWidth(), onClick = {

            }, colors = CardDefaults.cardColors(
                containerColor = Grey.copy(0.2f)
            )) {
                Row {
                    Card(
                        elevation = CardDefaults.cardElevation(0.dp),
                        colors = CardDefaults.cardColors(containerColor = Accent.copy(0.4f)),
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(CircleShape),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "edit_akun",
                            modifier = Modifier
                                .width(54.dp)
                                .height(54.dp)
                                .padding(10.dp),
                            tint = Accent
                        )
                    }
                    Column(modifier = Modifier.padding(vertical = 18.dp)) {
                        Text(
                            text = "Edit Akun",
                            color = DarkBrown,
                            fontFamily = MontSerrat,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Kelola akun yang tersimpan",
                            color = DarkBrown.copy(0.4f),
                            fontFamily = MontSerrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp
                        )
                    }
                }
            }
            /*Card(modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxWidth(), onClick = {

            }) {
                Row {
                    Card(
                        elevation = CardDefaults.cardElevation(0.dp),
                        colors = CardDefaults.cardColors(containerColor = Accent.copy(0.4f)),
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(CircleShape),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = "ubah_password",
                            modifier = Modifier
                                .width(54.dp)
                                .height(54.dp)
                                .padding(10.dp),
                            tint = Accent
                        )
                    }
                    Column(modifier = Modifier.padding(vertical = 18.dp)) {
                        Text(
                            text = "Ganti Password",
                            color = DarkBrown,
                            fontFamily = MontSerrat,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Kelola password akun anda",
                            color = DarkBrown.copy(0.4f),
                            fontFamily = MontSerrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp
                        )
                    }
                }
            }*/
            Card(modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxWidth(), onClick = {

            }, colors = CardDefaults.cardColors(
                containerColor = Grey.copy(0.2f)
            )) {
                Row {
                    Card(
                        elevation = CardDefaults.cardElevation(0.dp),
                        colors = CardDefaults.cardColors(containerColor = Accent.copy(0.4f)),
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(CircleShape),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Logout,
                            contentDescription = "logout",
                            modifier = Modifier
                                .width(54.dp)
                                .height(54.dp)
                                .padding(10.dp),
                            tint = Accent
                        )
                    }
                    Column(modifier = Modifier.padding(vertical = 18.dp)) {
                        Text(
                            text = "Log out",
                            color = DarkBrown,
                            fontFamily = MontSerrat,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Keluar dari aplikasi",
                            color = DarkBrown.copy(0.4f),
                            fontFamily = MontSerrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun AkunPagePreview() {
        CoffeeAroundUComposeTheme {
            val userModel = UserModel(
                id = 1,
                address = "Rumah",
                email = "User@gmail.com",
                foto = "https://cdn.discordapp.com/attachments/775027234096414720/1079261802526945401/image.png",
                name = "MePet",
                password = "lohe",
                phone_number = "+62821756390",
                role = "user",
                token = ""
            )
            AkunPage(context = LocalContext.current, model = userModel)
        }
    }
}
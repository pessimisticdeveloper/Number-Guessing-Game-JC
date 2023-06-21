package com.example.sayitahminoyunu


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.random.Random


@Composable
fun TahminEkrani(navController: NavController){
    val tf_Tahmin = remember { mutableStateOf("") }
    val kalanHak = remember { mutableStateOf(5)}
    val yonlendirme = remember { mutableStateOf("") }
    val rasgeleSayi = remember { mutableStateOf(0) }

    LaunchedEffect(key1 = true){
        rasgeleSayi.value = Random.nextInt(101) //0-101
        //println(rasgeleSayi)
    }

    Column(modifier = Modifier.fillMaxSize()
        ,horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Kalan Hak: ${kalanHak.value}", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color.Green)
        Text(text = "Yardım: ${yonlendirme.value}", fontSize = 25.sp, fontWeight = FontWeight.Normal, color = Color.Blue)
        TextField(
            value = tf_Tahmin.value,
            onValueChange = { tf_Tahmin.value = it },
            label = { Text(text = "Tahmin")})
        Button(onClick = {
            kalanHak.value = kalanHak.value -1
            val tahmin = tf_Tahmin.value.toInt()

            if (tahmin == rasgeleSayi.value){
                navController.navigate("sonuc_ekrani/true"){
                    popUpTo("tahmin_ekrani"){inclusive = true}
                }
                return@Button
            }
            if (tahmin > rasgeleSayi.value){
                yonlendirme.value = "Azalt."
            }
            if (tahmin < rasgeleSayi.value){
                yonlendirme.value = "Arttır."
            }
            if (kalanHak.value == 0){
                navController.navigate("sonuc_ekrani/false"){
                    popUpTo("tahmin_ekrani"){inclusive = true}
                }
            }
            tf_Tahmin.value = ""
                         },
            modifier = Modifier.size(width = 250.dp, height = 50.dp)) {
            Text(text = "TAHMİN ET")
        }
    }
}
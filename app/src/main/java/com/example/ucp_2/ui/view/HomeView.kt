package com.example.ucp_2.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp_2.R

@Composable
fun HomeView(
    onDosenButton: () -> Unit,
    onMataKuliahButton: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Background dari gambar dengan efek blur
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = 2.dp),
            contentScale = ContentScale.Crop // Sesuaikan gambar agar mengisi seluruh layar
        )

        // Bagian Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp) // Tinggi header
                .background(
                    color = Color(0xAA90CAF9), // Transparansi pada header
                    shape = RoundedCornerShape(bottomEnd = 50.dp) // Sudut kanan bawah melingkar
                ),
            contentAlignment = Alignment.Center // Teks berada di tengah
        ) {
            Text(
                text = "Univesrsitas Muhammadiyah Yogyakarta",
                fontSize = 26.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)
            )
        }

        // Kolom utama
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 100.dp), // Atur jarak dari header
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.width(30.dp))

            // Baris tombol
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                    // Tombol Dosen
                    Button(
                        onClick = { onDosenButton() },
                        modifier = Modifier
                            .padding(8.dp)
                            .size(width = 140.dp, height = 80.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.darkblue))// Warna latar belakang tombol Dosen

                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person, // Ikon untuk Dosen
                                contentDescription = null,
                                modifier = Modifier.size(24.dp),
                                tint = Color.White
                            )

                            Text(text = "Dosen")
                        }
                    }


                    // Tombol Mata Kuliah
                Button(
                    onClick = { onMataKuliahButton() },
                    modifier = Modifier
                        .padding(8.dp)
                        .size(width = 140.dp, height = 80.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu, // Ikon untuk Mata Kuliah
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black
                        )
                        Text(text = "Mata Kuliah")
                    }
                }
            }
        }
    }
}

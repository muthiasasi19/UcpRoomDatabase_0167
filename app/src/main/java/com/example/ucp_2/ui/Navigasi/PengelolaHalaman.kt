package com.example.ucp_2.ui.Navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp_2.ui.view.HomeView
import com.example.ucp_2.ui.view.dosen.DestinasiInsert
import com.example.ucp_2.ui.view.dosen.HomeDosenView
import com.example.ucp_2.ui.view.dosen.InsertDosenView
import com.example.ucp_2.ui.view.matakuliah.DestinasiInsertMataKuliah
import com.example.ucp_2.ui.view.matakuliah.DetailMKView
import com.example.ucp_2.ui.view.matakuliah.HomeMKView
import com.example.ucp_2.ui.view.matakuliah.InsertMKView
import com.example.ucp_2.ui.view.matakuliah.UpdateMKView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute.route,  // Halaman pertama yang dituju
        modifier = modifier
    ) {
        // Rute untuk Halaman utama (Home)
        composable(route = HomeRoute.route) {
            HomeView(
                onDosenButton = {
                    navController.navigate(DestinasiHomeDosen.route)// Navigasi ke halaman dosen
                },
                onMataKuliahButton = {
                    navController.navigate(DestinasiHomeMataKuliah.route)  // Navigasi ke halaman mata kuliah
                }
            )
        }
        // Rute untuk halaman daftar dosen
        composable(route = DestinasiHomeDosen.route) {
            HomeDosenView(
                onAddDosen = {
                    navController.navigate(DestinasiInsert.route)
                },
                onBack = { navController.popBackStack() },
                onDetailClick = { nidn ->
                    println("Navigasi ke detail dengan NIDN: $nidn")
                },
            )
        }

        // Rute untuk halaman form tambah dosen
        composable(
            route = DestinasiInsert.route
        ) {
            InsertDosenView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier,
            )
        }


        // Rute untuk halaman mata kuliah
        composable(route = DestinasiHomeMataKuliah.route) {
            HomeMKView(
                onAddMk = {
                    navController.navigate(DestinasiInsertMataKuliah.route) // Navigasi untuk menambah mata kuliah
                },
                onDetailClick = { kode ->
                    navController.navigate("${DestinasiDetailMataKuliah.route}/$kode")
                    println("PengelolaHalaman: kode =$kode")
                },
                onBack = { navController.popBackStack() },
                modifier = modifier,
            )
        }
        composable(
            route = DestinasiInsertMataKuliah.route
        ) {
            InsertMKView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier,
            )
        }
        composable(
            DestinasiDetailMataKuliah.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailMataKuliah.kodeMatakuliah) {
                    type = NavType.StringType
                }
            )
        ) {
            val kode = it.arguments?.getString(DestinasiDetailMataKuliah.kodeMatakuliah)
            kode?.let { kode ->
                DetailMKView(
                    onBack = { navController.popBackStack() },
                    onEditClick = { navController.navigate( "${DestinasiUpdateMataKuliah.route}/$it") },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdateMataKuliah.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateMataKuliah.kodeMatakuliah) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateMKView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier,
            )
        }
    }
}
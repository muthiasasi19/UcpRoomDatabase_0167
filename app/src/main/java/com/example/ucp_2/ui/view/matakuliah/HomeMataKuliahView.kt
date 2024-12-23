package com.example.ucp_2.ui.view.matakuliah

import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp_2.R
import com.example.ucp_2.data.entity.MataKuliah
import com.example.ucp_2.ui.customwidget.CustomTopAppBar
import com.example.ucp_2.ui.viewmodel.dosen.PenyediaViewModel
import com.example.ucp_2.ui.viewmodel.matakuliah.HomeMKViewModel
import com.example.ucp_2.ui.viewmodel.matakuliah.HomeUiState
import kotlinx.coroutines.launch

@Composable
fun HomeMKView(
    viewModel : HomeMKViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddMk: () -> Unit = { },
    onBack: () -> Unit = { },
    onDetailClick: (String) -> Unit = { },
    modifier: Modifier = Modifier,
){
    Scaffold (
        topBar = {
            CustomTopAppBar(
                judul = "Daftar Mata Kuliah",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddMk,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Matakuliah",
                )
            }
        }
    ) { innerPadding ->
        val homeMataKuliahUiState by viewModel.homeUiState.collectAsState()

        BodyHomeMataKuliahView(
            homeMataKuliahUiState = homeMataKuliahUiState,
            onClick = {
                onDetailClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeMataKuliahView(
    homeMataKuliahUiState: HomeUiState,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() } // snacbarState
    when {
        homeMataKuliahUiState.isLoading -> {
            // Menampilkan indikator loading
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        homeMataKuliahUiState.isError -> {
            //Menampilkan pesan error
            LaunchedEffect(homeMataKuliahUiState.errorMessage) {
                homeMataKuliahUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message) // Tampilkan snackbar
                    }
                }
            }
        }
        homeMataKuliahUiState.listMataKuliah.isEmpty() -> {
            //Menampilkan pesan jika data kosong
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Tidak ada data Mata kuliah",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else -> {Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(900.dp)
                .padding(top = 110.dp)
                .background(
                    color = colorResource(id = R.color.skyblue),
                    shape = RoundedCornerShape(topStart = 25.dp, topEnd = 0.dp) // Rounded hanya di kiri atas
                )
        )
            //Menampilkan daftar Matakuliah
            ListMataKuliah(
                listMk = homeMataKuliahUiState.listMataKuliah,
                onClick = {
                    onClick(it)
                    println(it)
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListMataKuliah(
    listMk: List<MataKuliah>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = listMk,
            itemContent = { mk ->
                CardMk(
                    mk = mk,
                    onClick = {
                        onClick(mk.kode)
                    }
                )
            }
        )
    }
}

}
package com.example.ucp_2.ui.view.matakuliah

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.ucp_2.ui.viewmodel.matakuliah.DetailMKViewModel
import com.example.ucp_2.ui.viewmodel.matakuliah.DetailUiState


@Composable
fun DetailMKView( // Fungsi utama untuk menampilkan detail mata kuliah
    modifier: Modifier,
    ViewModel: DetailMKViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBack: () -> Unit = { },
    onEditClick: (String) -> Unit = { },
    onDeleteClick: (String) -> Unit = { },
){
    Scaffold (
        topBar = {
            CustomTopAppBar(
                judul = "Detail Mata Kuliah",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEditClick(ViewModel.detailUiState.value.detailUiEvent.kode)
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit, contentDescription = "Edit Mata Kuliah",
                )
            }
        }
    ){ innerPadding ->
        val detailUiState by ViewModel.detailUiState.collectAsState()
        BodyDetailMataKuliah(
            modifier = Modifier.padding(innerPadding),
            detailUiState = detailUiState,
            onDeleteClick = {
                ViewModel.deleteMataKuliah()
                onDeleteClick
            }
        )
    }
}

@Composable
fun BodyDetailMataKuliah(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState = DetailUiState(),
    onDeleteClick: () -> Unit = { }
){
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    when {
        detailUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()  // Menampilkan progress indicator
            }
        }
        detailUiState.isEventNotEmpty -> {
            Column (
                modifier = modifier.fillMaxWidth().padding(16.dp)
            ){
                ItemDetailMk(
                    mataKuliah =   detailUiState.detailUiEvent.toMataKuliahEntity(),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        deleteConfirmationRequired = true
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.skyblue), // warna background button
                        contentColor = colorResource(id = R.color.white)    //   warna teks
                    )
                ) {
                    Text(text = "Delete")
                }
                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCacel = {deleteConfirmationRequired =false},
                        modifier =  Modifier.padding(8.dp)
                    )
                }
            }
        }
        detailUiState.isEventNotEmpty -> { // Jika data tidak ditemukan
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Data Tidak ditemukan",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ItemDetailMk(  // Menampilkan detail mata kuliah dalam kartu
    modifier: Modifier =Modifier,
    mataKuliah: MataKuliah
){
    Card ( // Card untuk detail mata kuliah
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ){
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            ComponentDetailMataKuliah(judul = "Kode", isinya = mataKuliah.kode)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMataKuliah(judul = "Nama", isinya = mataKuliah.nama)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMataKuliah(judul = "Sks", isinya = mataKuliah.sks)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMataKuliah(judul = "Semester", isinya = mataKuliah.semester)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMataKuliah(judul = "Jenis", isinya = mataKuliah.jenis)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMataKuliah(judul = "Dosen Pengampu", isinya = mataKuliah.dosenPengampu)
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}

@Composable
fun ComponentDetailMataKuliah( // Menampilkan satu baris detail mata kuliah
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
){
    Column(
        modifier = modifier.fillMaxWidth(),

        horizontalAlignment = Alignment.Start
    ){
        Text(
            text = "$judul :",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya, fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DeleteConfirmationDialog( // konfirmasi hapus data
    onDeleteConfirm: () -> Unit, onDeleteCacel: () -> Unit, modifier: Modifier =
        Modifier
) {
    AlertDialog(onDismissRequest = { /* Do Nothing */},
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCacel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}
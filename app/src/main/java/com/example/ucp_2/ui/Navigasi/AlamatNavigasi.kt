package com.example.ucp_2.ui.Navigasi

interface AlamatNavigasi {
    val route: String
}
//mendefinisikan rute untuk halaman utama
object HomeRoute : AlamatNavigasi{
    override val route = "home"
}
// mendefinisikan rute untuk halaman dosen
object DestinasiHomeDosen : AlamatNavigasi {
    override val route = "homeDosen"
}
//mendefinisikan rute untuk halaman detail dosen
object DestinasiDetailDosen : AlamatNavigasi {
    override val route = "detailDosen"
    const val kodeDosen = "kode_dosen"
    val routesWithArg = "$route/{$kodeDosen}"
}
//mendefinisikan rute untuk halaman mata kuliah
object DestinasiHomeMataKuliah : AlamatNavigasi {
    override val route = "homeMatakuliah"
}
//mendefinisikan rute untuk halaman detail mata kuliah
object DestinasiDetailMataKuliah : AlamatNavigasi {
    override val route = "detailMatakuliah"
    const val kodeMatakuliah = "kode_matakuliah"
    val routesWithArg = "$route/{$kodeMatakuliah}"
}

object DestinasiUpdateMataKuliah : AlamatNavigasi {
    override val route = "updateMatakuliah"
    const val kodeMatakuliah = "kode_matakuliah"
    val routesWithArg = "$route/{$kodeMatakuliah}"
}
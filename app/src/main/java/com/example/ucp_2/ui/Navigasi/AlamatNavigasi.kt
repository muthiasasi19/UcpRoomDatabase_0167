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
    override val route = "home_dosen"
}
//mendefinisikan rute untuk halaman detail dosen
object DestinasiDetailDosen : AlamatNavigasi {
    override val route = "detail_dosen"
    const val KODE_DOSEN = "kode_dosen"
    val routesWithArg = "$route/{$KODE_DOSEN}"
}
//mendefinisikan rute untuk halaman mata kuliah
object DestinasiHomeMataKuliah : AlamatNavigasi {
    override val route = "home_matakuliah"
}
//mendefinisikan rute untuk halaman detail mata kuliah
object DestinasiDetailMataKuliah : AlamatNavigasi {
    override val route = "detail_matakuliah"
    const val KODE_MATAKULIAH = "kode_matakuliah"
    val routesWithArg = "$route/{$KODE_MATAKULIAH}"
}

object DestinasiUpdateMataKuliah : AlamatNavigasi {
    override val route = "update_matakuliah"
    const val KODE_MATAKULIAH = "kode_matakuliah"
    val routesWithArg = "$route/{$KODE_MATAKULIAH}"
}
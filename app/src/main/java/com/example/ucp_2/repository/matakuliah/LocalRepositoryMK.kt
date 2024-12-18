package com.example.ucp_2.repository.matakuliah

import com.example.ucp_2.data.dao.MatakuliahDao
import com.example.ucp_2.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMK
    (private val matakuliahDao: MatakuliahDao)  : RepositoryMK
{
    override suspend fun insertMK(matakuliah: Matakuliah) {
        matakuliahDao.insertMatakuliah(matakuliah)
    }

    override fun getAllMK(): Flow<List<Matakuliah>> {
        return matakuliahDao.getAllMatakuliah()
    }

    override fun getMK(kode: String): Flow<Matakuliah> {
        return matakuliahDao.getMatakuliah(kode)
    }

    override suspend fun deleteMK(matakuliah: Matakuliah) {
        matakuliahDao.deleteMatakuliah(matakuliah)
    }

    override suspend fun updateMK(matakuliah: Matakuliah) {
        matakuliahDao.updateMatakuliah(matakuliah)
    }

}
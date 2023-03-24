package id.ac.unpas.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.hmtif.divisi.kaderisasi.Mahasiswa.Aktif.model.dataAtk

@Dao
interface databinaanDao {
    @Query("SELECT * FROM dataAtk")
    fun loadAll(): LiveData<List<databinaan>>

    @Query("SELECT * FROM dataAtk WHERE npm = :npm")
    fun find(npm: String): databinaan?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: databinaan)

    @Delete
    fun delete(item: databinaan)
}

@Database(entities = [databinaan::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databinaanDao(): databinaanDao
}
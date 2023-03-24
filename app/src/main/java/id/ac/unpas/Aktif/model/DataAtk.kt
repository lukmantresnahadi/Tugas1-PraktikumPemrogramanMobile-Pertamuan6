package id.ac.unpas.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class dataAtk(
    @PrimaryKey val id: String,
    val nobarang: String,
    val namabarang: String,
    val kodebarang: String,
    val jumlahbarang: String
)

package id.ac.unpas.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import id.ac.unpas.model.dataAtk
import id.ac.unpas.persistences.AppDatabase

@Composable
fun databinaanscreen() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "data-binaan"
    ).build()
    val databinaanDao = db.databinaanDao()
    val list : LiveData<List<databinaan>> = databinaanDao.loadAll()
    val items: List<databinaan> by list.observeAsState(initial = listOf())
    Column(modifier = Modifier.fillMaxWidth()) {
        FormDataBinaan(databinaanDao)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()) {

                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Nomor Barang", fontSize = 14.sp)
                        Text(text = item.npm, fontSize = 16.sp, fontWeight =
                        FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Nama barang", fontSize = 14.sp)
                        Text(text = item.nama, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Kode Barang", fontSize = 14.sp)
                        Text(text = item.tingkat, fontSize = 16.sp, fontWeight =
                        FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Jumlah Barang", fontSize = 14.sp)
                        Text(text = item.jumlahpertemuan, fontSize = 16.sp, fontWeight =
                        FontWeight.Bold)
                    }

                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}
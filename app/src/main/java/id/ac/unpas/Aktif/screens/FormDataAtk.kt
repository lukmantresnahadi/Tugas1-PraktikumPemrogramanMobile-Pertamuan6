package id.ac.unpas.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import id.ac.unpas.model.dataAtk
import id.ac.unpas.persistences.dataAtkDao
import id.ac.unpas.Aktif.ui.theme.Purple700
import id.ac.unpas.ui.theme.Teal200
import kotlinx.coroutines.launch


@Composable
fun FormDataBinaan(databinaanDao: databinaanDao) {
    val scope = rememberCoroutineScope()
    val npm = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val tingkat = remember { mutableStateOf(TextFieldValue("")) }
    val jumlahpertemuan = remember { mutableStateOf(TextFieldValue("")) }
    val tglmulaibinaan = remember { mutableStateOf(TextFieldValue("")) }
    val tglselesaibinaan = remember { mutableStateOf(TextFieldValue("")) }


    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "No Barang") },
            value = No.value,
            onValueChange = {
                npm.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "01") }
        )
        OutlinedTextField(
            label = { Text(text = "Nama Barang") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "penggaris") }
        )
        OutlinedTextField(
            label = { Text(text = "kode barang") },
            value = kode.value,
            onValueChange = {
                kode.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "b02") }
        )
        OutlinedTextField(
            label = { Text(text = "Jumlah barang") },
            value = jumlahbarang.value,
            onValueChange = {
                jumlahbarang.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "9") }
        )


        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier.padding(4.dp).fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                val id = uuid4().toString()
                val item = databinaan(id, npm.value.text, nama.value.text,
                    tingkat.value.text, jumlahpertemuan.value.text, tglmulaibinaan.value.text,
                    tglselesaibinaan.value.text)
                scope.launch { databinaanDao.insertAll(item) }
                npm.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                tingkat.value = TextFieldValue("")
                jumlahpertemuan.value = TextFieldValue("")
                tglmulaibinaan.value = TextFieldValue("")
                tglselesaibinaan.value = TextFieldValue("")

            }, colors = loginButtonColors) {
                Text(
                    text = "Save",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                npm.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                tingkat.value = TextFieldValue("")
                jumlahpertemuan.value = TextFieldValue("")
                tglmulaibinaan.value = TextFieldValue("")
                tglselesaibinaan.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
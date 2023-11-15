package com.example.pertemuan6.ui.theme

import com.example.pertemuan6.data.OrderUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import android.icu.text.NumberFormat
import androidx.lifecycle.ViewModel
import com.example.pertemuan6.data.FormData

private const val HARGA_PER_CUP = 3000

class OrderViewModel : ViewModel(){
    private val _stateUI = MutableStateFlow(OrderUIState())
    private val _nameSTATE = MutableStateFlow(FormData())
    val stateUI: StateFlow<OrderUIState> = _stateUI.asStateFlow()
    val nameST : StateFlow<FormData> = _nameSTATE.asStateFlow()


    fun setNama(list: MutableList<String>){
        _nameSTATE.update { stateSaatIni -> stateSaatIni.copy(
            nama = list[0],
            alamat = list[1],
            phone = list[2]
        ) }
    }

    fun setJumlah(jmlEsJumbo: Int) {
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                jumlah = jmlEsJumbo,
                harga = hitungHarga(jmlEsJumbo))
        }
    }

    fun setRasa(rasaPilihan: String) {
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(rasa = rasaPilihan)
        }
    }

    fun resetOrder() {
        _stateUI.value = OrderUIState()
    }
}

private fun hitungHarga(jumlah: Int ): String {
    val kalkulasiHarga = jumlah * HARGA_PER_CUP
    return NumberFormat.getNumberInstance().format(kalkulasiHarga)
}

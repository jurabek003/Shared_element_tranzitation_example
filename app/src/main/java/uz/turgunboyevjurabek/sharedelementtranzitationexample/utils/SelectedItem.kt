package uz.turgunboyevjurabek.sharedelementtranzitationexample.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object SelectedItem {
    var selectedItemImage by mutableStateOf("0")
    var selectedItem by mutableStateOf("Item - 0")

}
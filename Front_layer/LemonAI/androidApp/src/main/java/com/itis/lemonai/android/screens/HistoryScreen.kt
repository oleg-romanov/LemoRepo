package com.itis.lemonai.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.itis.lemonai.android.components.Primary
import com.itis.lemonai.android.components.Secondary
import compose.icons.FeatherIcons
import compose.icons.feathericons.List
import compose.icons.feathericons.Grid
import compose.icons.feathericons.Filter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.Random

@Composable
fun HistoryScreen() {
    var isGridMode = remember { mutableStateOf(false) }
    var isFiltering = remember { mutableStateOf(false) }
    var expanded = remember { mutableStateOf(false) }
    var sortingOption = remember { mutableStateOf(SortingOption.DATE) }

    var items = remember { mutableStateOf(generateDummyData(100)) }

    //var items: List<HistoryItem> //= generateDummyData(5)

    // Функция для сортировки элементов
    val sortedItems = when (sortingOption.value) {
        SortingOption.DATE -> items.value.sortedByDescending { it.date }
        SortingOption.SHOP -> items.value.sortedBy { it.shop }
        SortingOption.RESULT -> items.value.sortedByDescending { it.result }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                    shape = RoundedCornerShape(10.dp)
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { isGridMode.value = !isGridMode.value }) {
                if (isGridMode.value) {
                    Icon(imageVector = FeatherIcons.List, contentDescription = null)
                } else {
                    Icon(imageVector = FeatherIcons.Grid, contentDescription = null)
                }
            }
            IconButton(onClick = { isFiltering.value = true
            expanded.value = true}) {
                Icon(imageVector = FeatherIcons.Filter, contentDescription = null)
            }
        }

        if (isFiltering.value) {
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                SortingOption.values().forEach { option ->
                    DropdownMenuItem(text = { Text(option.label) },
                        onClick = {
                        sortingOption.value = option
                        expanded.value = false
                    })
                }
            }
        }
        if (isGridMode.value) {
            LazyVerticalGrid(
                modifier = Modifier.padding(bottom = 56.dp),
                columns = GridCells.Adaptive(120.dp)
            ) {
                items(sortedItems) { item ->
                    GridItem(item)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(bottom = 56.dp)
            ) {
                items(sortedItems) { item ->
                    ListItem(item)
                }
            }
        }
    }
}

@Composable
fun ListItem(item: HistoryItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "Дата: ${item.date}", fontWeight = FontWeight.Bold)
        Text(text = "Магазин: ${item.shop}")
        Text(text = "Результат: ${item.result}")
    }
}

@Composable
fun GridItem(item: HistoryItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = item.date,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(4.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(text = "Магазин: ${item.shop}", modifier = Modifier.padding(top = 4.dp))
        Text(text = "Результат: ${item.result}", modifier = Modifier.padding(top = 4.dp))
    }
}

enum class SortingOption(val label: String) {
    DATE("По дате"),
    SHOP("По магазину"),
    RESULT("По результату")
}

data class HistoryItem(
    val date: String,
    val shop: String,
    val result: String
)

fun generateDummyData(count: Int): List<HistoryItem> {
    val shops = listOf("Магнит", "Пятерочка", "Перекресток", "Дикси", "Ашан", "Лента", "Метро")
    val results = listOf("Хороший", "Плохой", "Зеленый")

    val random = Random()
    val items = mutableListOf<HistoryItem>()

    for (i in 0 until count) {
        val randomDate = generateRandomDate()
        val randomShop = shops[random.nextInt(shops.size)]
        val randomResult = results[random.nextInt(results.size)]

        items.add(HistoryItem(randomDate, randomShop, randomResult))
    }

    return items
}
fun generateRandomDate(): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, -Random().nextInt(365)) // Генерация дат в течение последнего года
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(calendar.time)
}
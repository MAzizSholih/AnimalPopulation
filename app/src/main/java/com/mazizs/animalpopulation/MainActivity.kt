package com.mazizs.animalpopulation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mazizs.animalpopulation.data.DataSource
import com.mazizs.animalpopulation.model.Topic
import com.mazizs.animalpopulation.ui.theme.AnimalPopulationTheme

//Fungsi onCreate ini digunakan untuk mengedit tampilan aktivitas utama dengan menggunakan komponen UI yang telah didefinisikan dalam TopicGrid
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimalPopulationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicGrid(
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi TopicGrid yaitu umtuk menampilkan dan mengatur list grid
@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid( //Untuk membuat grid dengan list item yang dapat discroll secara vertikal
        columns = GridCells.Fixed(2), //Untuk menentukan jumlah kolom yang akan ditampilkan di list grid
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        modifier = modifier
    ) {
        items(DataSource.topics) { topic ->
            TopicCard(topic)
        }
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi TopicCard yaitu membuat tampilan seperti card yang menampilkan informasi tentang populasi hewan serta mengaturnya
@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card {
        Box(
            modifier = modifier.fillMaxWidth()
        ) {
            Image( //Untuk menampilkan gambar topik
                painter = painterResource(id = topic.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.padding_small),
                    bottom = dimensionResource(R.dimen.padding_small)
                )
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally //Untuk membuat teks di tengah secara horizontal
        ) {
            Text(
                text = stringResource(id = topic.name),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.population),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = dimensionResource(R.dimen.padding_small))
                        .size(15.dp)
                )
                Text(
                    text = topic.availablePopulation.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

//Fungsi di bawah ini adalah komponen Composable yang digunakan untuk menampilkan preview atau pratinjau dari AnimalPopulation
@Preview(showBackground = true)
@Composable
fun TopicPreview() {
    AnimalPopulationTheme {
        val topic = Topic(R.string.badak_hitam, 5500, R.drawable.badak_hitam)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopicCard(topic = topic)
        }
    }
}

package com.br.weightcontrol.ui.chart

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.br.weightcontrol.designsystem.theme.WeiTheme
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.ui.TrackListResourcePreviewParameterProvider
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer

@Composable
fun TrackListChart(tracks: List<Track>, modifier: Modifier = Modifier) {
    val chartEntryModelProducer = tracks.mapIndexed { index, (_, weight, createdAt) ->
        Entry(
            localDate = createdAt,
            x = index.toFloat(),
            y = weight.toFloat()
        )
    }
        .let { ChartEntryModelProducer(it) }
        .getModel()

    val bottomAxisValueFormatter =
        AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, chartValues ->
            (chartValues.chartEntryModel.entries.first().getOrNull(value.toInt()) as? Entry)
                ?.localDate
                ?.run { "$dayOfMonth/$monthNumber" }
                .orEmpty()
        }

    val startAxisValueFormatter =
        AxisValueFormatter<AxisPosition.Vertical.Start> { value, _ ->
            value.toInt().toString()
        }

    val marker = rememberMarker()
    ProvideChartStyle(rememberChartStyle(listOf(MaterialTheme.colorScheme.primary))) {
        Chart(
            modifier = modifier,
            chart = lineChart(
                pointPosition = LineChart.PointPosition.Start,
                persistentMarkers = remember(marker) {
                    mapOf(chartEntryModelProducer.maxX to marker)
                }
            ),
            model = chartEntryModelProducer,
            startAxis = startAxis(valueFormatter = startAxisValueFormatter),
            bottomAxis = bottomAxis(valueFormatter = bottomAxisValueFormatter),
            marker = marker
        )
    }
}


@Preview
@Composable
fun LineChartScreenPreview2(
    @PreviewParameter(TrackListResourcePreviewParameterProvider::class)
    trackListResource: List<Track>,
) {
    WeiTheme {
        TrackListChart(trackListResource)
    }
}

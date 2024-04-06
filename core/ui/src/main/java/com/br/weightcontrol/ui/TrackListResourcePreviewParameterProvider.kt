package com.br.weightcontrol.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.br.weightcontrol.model.Track
import kotlinx.datetime.LocalDate

class TrackListResourcePreviewParameterProvider : PreviewParameterProvider<List<Track>> {
    override val values = sequenceOf(
        listOf(
            Track(weight = 80.0, createdAt = LocalDate.parse("2022-07-14")),
            Track(weight = 79.0, createdAt = LocalDate.parse("2022-07-15")),
            Track(weight = 85.0, createdAt = LocalDate.parse("2022-07-16")),
            Track(weight = 80.0, createdAt = LocalDate.parse("2022-07-17")),
            Track(weight = 79.0, createdAt = LocalDate.parse("2022-07-18")),
            Track(weight = 77.0, createdAt = LocalDate.parse("2022-07-18")),
            Track(weight = 75.0, createdAt = LocalDate.parse("2022-07-19")),
        )
    )
}

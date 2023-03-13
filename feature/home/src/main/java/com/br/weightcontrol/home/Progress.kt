package com.br.weightcontrol.home

import com.br.weightcontrol.model.Track

data class Progress(
    val last: Track? = null,
    val first: Track? = null,
    val lower: Track? = null,
    val higher: Track? = null,
)
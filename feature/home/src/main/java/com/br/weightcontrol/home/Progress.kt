package com.br.weightcontrol.home

import com.br.weightcontrol.model.Track

data class Progress(
    val first: Track? = null,
    val previous: Track? = null,
    val lower: Track? = null,
    val higher: Track? = null,
)
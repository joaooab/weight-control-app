package com.br.weightcontrol.ads

fun adBannerId() =
    if (BuildConfig.DEBUG) BuildConfig.AD_BANNER_TEST else BuildConfig.AD_BANNER
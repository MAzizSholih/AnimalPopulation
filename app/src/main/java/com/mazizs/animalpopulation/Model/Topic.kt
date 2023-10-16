package com.mazizs.animalpopulation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val name: Int,
    val availablePopulation: Int,
    @DrawableRes val imageRes: Int
)

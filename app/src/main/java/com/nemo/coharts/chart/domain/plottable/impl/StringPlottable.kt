package com.nemo.coharts.chart.domain.plottable.impl

import com.nemo.coharts.chart.domain.plottable.Plottable

data class StringPlottable(
    override val label: String,
    override val value: String,
) : Plottable


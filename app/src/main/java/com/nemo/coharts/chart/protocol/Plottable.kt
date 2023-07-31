package com.nemo.coharts.chart.protocol

sealed interface Plottable {
    val label: String
    val value: Any
}

data class StringPlottable(
    override val label: String,
    override val value: String,
) : Plottable

data class DoublePlottable(
    override val label: String,
    override val value: Double,
) : Plottable

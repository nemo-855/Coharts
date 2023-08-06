package com.nemo.coharts.chart.domain.plottable.impl

import com.nemo.coharts.chart.domain.plottable.Plottable

data class DoublePlottable(
    override val label: String,
    override val value: Double,
) : Plottable, Comparable<DoublePlottable> {
    override fun compareTo(other: DoublePlottable): Int {
        return value.compareTo(other.value)
    }

    fun plus(other: DoublePlottable): DoublePlottable? {
        if (label != other.label) return null

        return DoublePlottable(label, value + other.value)
    }
}

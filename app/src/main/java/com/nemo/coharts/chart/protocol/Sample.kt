package com.nemo.coharts.chart.protocol

data class Sample<X : Plottable, Y : Plottable>(
    val xPlottable: X,
    val yPlottable: Y
)

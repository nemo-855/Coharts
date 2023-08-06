package com.nemo.coharts.chart.domain.sample

import com.nemo.coharts.chart.domain.plottable.Plottable
import com.nemo.coharts.chart.domain.plottable.impl.DoublePlottable

data class Sample<X : Plottable>(
    val xPlottable: X,
    val yPlottable: DoublePlottable,
) {
    companion object {
        fun <X : Plottable> List<Sample<X>>.sumByLabel(): List<Sample<X>> {
            return buildMap<X, DoublePlottable> {
                this@sumByLabel.forEach { (x, y) ->
                    if (this@buildMap[x] == null) {
                        this@buildMap[x] = y
                    } else {
                        this@buildMap[x] = this@buildMap[x]?.plus(y) ?: y
                    }
                }
            }.map { Sample(it.key, it.value) }
        }
    }
}

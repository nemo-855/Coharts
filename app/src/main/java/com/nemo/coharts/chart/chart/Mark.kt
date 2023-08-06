package com.nemo.coharts.chart.chart

import androidx.compose.ui.graphics.drawscope.DrawScope
import com.nemo.coharts.chart.domain.sample.Sample
import com.nemo.coharts.chart.domain.plottable.Plottable

sealed interface Mark {
    fun <X : Plottable> drawContent(
        drawScope: DrawScope,
        samples: List<Sample<X>>,
    )
}

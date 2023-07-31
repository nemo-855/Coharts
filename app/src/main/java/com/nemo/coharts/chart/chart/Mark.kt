package com.nemo.coharts.chart.chart

import androidx.compose.ui.graphics.drawscope.DrawScope
import com.nemo.coharts.chart.protocol.DoublePlottable
import com.nemo.coharts.chart.protocol.Plottable

sealed interface Mark {
    fun <X : Plottable> drawContent(
        drawScope: DrawScope,
        samples: List<Pair<X, DoublePlottable>>,
    )
}

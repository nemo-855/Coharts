package com.nemo.coharts.chart.chart

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import com.nemo.coharts.chart.domain.plottable.Plottable
import com.nemo.coharts.chart.domain.sample.Sample

private const val marginPaddingRatio = 0.4f

object BarMark : Mark {
    override fun <X : Plottable> drawContent(
        drawScope: DrawScope,
        samples: List<Sample<X>>
    ) {
        val maxYPlottable = samples.maxOfOrNull { it.yPlottable } ?: return

        samples.forEachIndexed { index, (_, yPlottable) ->
            val sectionWidth = drawScope.size.width / samples.size

            val barWidth = sectionWidth * (1 - marginPaddingRatio)
            val barHeight = drawScope.size.height * (yPlottable.value / maxYPlottable.value)

            val leftX = sectionWidth * index + (sectionWidth * marginPaddingRatio / 2)
            val rightX = leftX + barWidth
            val bottomY = drawScope.size.height
            val topY = bottomY - barHeight.toFloat()

            val path = Path().also {
                it.lineTo(leftX, topY)
                it.lineTo(leftX, bottomY)
                it.lineTo(rightX, bottomY)
                it.lineTo(rightX, topY)
                it.lineTo(leftX, topY)
            }
            drawScope.drawPath(
                color = Color.Blue,
                path = path,
                style = Fill,
            )
        }
    }
}
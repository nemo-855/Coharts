package com.nemo.coharts.chart.chart

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import com.nemo.coharts.chart.protocol.DoublePlottable
import com.nemo.coharts.chart.protocol.Plottable

private const val marginPaddingRatio = 0.4f

object BarMark : Mark {
    override fun <X : Plottable> drawContent(
        drawScope: DrawScope,
        samples: List<Pair<X, DoublePlottable>>
    ) {
        samples.forEachIndexed { index, (_, yPlottable) ->
            // FIXME DoublePlottableにComparableを継承すれば綺麗に書けそう、
            //  maxYはmaxYPlottableを包含する、一番キリの良さそうな数値を入れる想定
            val maxYPlottable = samples
                .maxOfOrNull { it.second.value }
                ?.let { yPlottable.copy(value = it) }
                ?: return@forEachIndexed

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
package com.nemo.coharts.chart.chart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nemo.coharts.chart.protocol.DoublePlottable
import com.nemo.coharts.chart.protocol.Plottable
import com.nemo.coharts.chart.protocol.Sample
import com.nemo.coharts.chart.protocol.StringPlottable

/**
 * TODO kdoc記入
 *
 * y座標の方は加算できるPlottableでなくてはならない
 */
@Composable
fun <X : Plottable> Chart(
    modifier: Modifier = Modifier,
    samples: List<Sample<X>>,
    mark: Mark,
) {

    Column(modifier = modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .weight(9f)
                .fillMaxSize()
        ) {
            drawVerticalLine(xDepartmentNumber = samples.size)
            drawHorizontalLine()
            mark.drawContent(
                drawScope = this,
                // FIXME Sampleクラス側にcompanion objectで、
                //  List<Sample>.buildMapみたいな感じで実装すればスッキリしそう
                samples = buildMap<X, DoublePlottable> {
                    samples.forEach { sample ->
                        val yPlottable = this[sample.xPlottable]
                        if (yPlottable == null) {
                            this[sample.xPlottable] = sample.yPlottable
                        } else {
                            this[sample.xPlottable] = yPlottable.copy(
                                value = yPlottable.value + sample.yPlottable.value
                            )
                        }
                    }
                }.toList(),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            samples.forEach { (xPlottable, _) ->
                // FIXME 表示幅に応じてサイズを変えたい。
                //  それか応急処置であれば外部でTextサイズ変更できるようにするとか？？
                Text(
                    modifier = Modifier.weight(1f),
                    text = xPlottable.value.toString(),
                    fontSize = 6.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

private fun DrawScope.drawHorizontalLine(
    lineColor: Color = Color.Black,
    yDepartmentNumber: Int = 3,
) {
    val oneYDepartmentHeight = 3
    val maxYHeight = oneYDepartmentHeight * yDepartmentNumber

    for (index in 0..yDepartmentNumber) {
        val startX = 0f
        val endX = size.width
        val y = size.height - size.height * oneYDepartmentHeight * index / maxYHeight

        drawLine(
            start = Offset(
                x = startX,
                y = y,
            ),
            end = Offset(
                x = endX,
                y = y,
            ),
            color = lineColor,
        )
    }
}

private fun DrawScope.drawVerticalLine(
    lineColor: Color = Color.Black,
    lineEffect: PathEffect = PathEffect.dashPathEffect(
        intervals = floatArrayOf(2f, 2f),
        phase = 4f,
    ),
    xDepartmentNumber: Int,
) {
    for (index in 0..xDepartmentNumber) {
        val x = size.width * index / xDepartmentNumber
        val startY = 0f
        val endY = size.height

        drawLine(
            start = Offset(
                x = x,
                y = startY,
            ),
            end = Offset(
                x = x,
                y = endY,
            ),
            color = lineColor,
            pathEffect = lineEffect,
        )
    }
}

@Preview
@Composable
fun PreviewChart() {
    val samples = listOf(
        Sample(
            xPlottable = StringPlottable(label = "所属グループ", value = "グループA"),
            yPlottable = DoublePlottable(label = "記録", value = 50.0),
        ),
        Sample(
            xPlottable = StringPlottable(label = "所属グループ", value = "グループB"),
            yPlottable = DoublePlottable(label = "記録", value = 60.0),
        ),
        Sample(
            xPlottable = StringPlottable(label = "所属グループ", value = "グループC"),
            yPlottable = DoublePlottable(label = "記録", value = 70.0),
        ),
        Sample(
            xPlottable = StringPlottable(label = "所属グループ", value = "グループB"),
            yPlottable = DoublePlottable(label = "記録", value = 100.0),
        ),
        Sample(
            xPlottable = StringPlottable(label = "所属グループ", value = "グループD"),
            yPlottable = DoublePlottable(label = "記録", value = 60.0),
        ),
        Sample(
            xPlottable = StringPlottable(label = "所属グループ", value = "グループE"),
            yPlottable = DoublePlottable(label = "記録", value = 70.0),
        ),
        Sample(
            xPlottable = StringPlottable(label = "所属グループ", value = "グループF"),
            yPlottable = DoublePlottable(label = "記録", value = 100.0),
        ),
    )

    Chart(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp)
            .aspectRatio(1f),
        samples = samples,
        mark = BarMark,
    )
}
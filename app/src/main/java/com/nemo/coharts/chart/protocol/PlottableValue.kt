package com.nemo.coharts.chart.protocol

data class PlottableValue<T : Plottable>(val value: T) {
    companion object {
        fun value(
            label: String,
            keyPath: String,
        ): PlottableValue<StringPlottable> {
            return PlottableValue(
                value = StringPlottable(
                    label = label,
                    value = keyPath,
                )
            )
        }

        fun value(
            label: String,
            keyPath: Double,
        ): PlottableValue<DoublePlottable> {
            return PlottableValue(
                value = DoublePlottable(
                    label = label,
                    value = keyPath,
                )
            )
        }
    }
}

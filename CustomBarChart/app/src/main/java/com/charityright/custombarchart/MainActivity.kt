package com.charityright.custombarchart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.charityright.custombarchart.library.charts.BarChart
import com.charityright.custombarchart.library.components.Description
import com.charityright.custombarchart.library.data.BarData
import com.charityright.custombarchart.library.data.BarDataSet
import com.charityright.custombarchart.library.data.BarEntry
import com.charityright.custombarchart.library.components.XAxis
import com.charityright.custombarchart.library.formatter.IndexAxisValueFormatter







class MainActivity : AppCompatActivity() {

    private lateinit var barChart: BarChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        barChart = findViewById(R.id.barChart)
        setBarChart()
    }

    private fun setBarChart() {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 0.toFloat()))
        entries.add(BarEntry(1f, 1.toFloat()))
        entries.add(BarEntry(2f, 2.toFloat()))
        entries.add(BarEntry(3f, 3.toFloat()))
        entries.add(BarEntry(4f, 4.toFloat()))
        entries.add(BarEntry(5f, 5.toFloat()))
        entries.add(BarEntry(6f, 6.toFloat()))
        entries.add(BarEntry(7f, 7.toFloat()))
        entries.add(BarEntry(8f, 8.toFloat()))
        entries.add(BarEntry(9f, 9.toFloat()))
        entries.add(BarEntry(10f, 10.toFloat()))
        entries.add(BarEntry(11f, 11.toFloat()))
        entries.add(BarEntry(12f, 12.toFloat()))

        val barDataSet = BarDataSet(entries, "Cells")

        val data = BarData(barDataSet)

        val labels = arrayOf(
            "Zero","Jan", "Feb", "March", "April", "May",
            "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"
        )
        val xAxis: XAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        xAxis.granularity = -1f
        xAxis.isGranularityEnabled = true

        barChart.data = data // set the data and list of lables into chart
        barChart.description.text = "Add Description Here"  // set the description

        //barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barDataSet.color = resources.getColor(R.color.design_default_color_primary)

        barChart.animateY(1000)
    }
}


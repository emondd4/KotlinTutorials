package com.charityright.custombarchart.library.interfaces.dataprovider;

import com.charityright.custombarchart.library.components.YAxis;
import com.charityright.custombarchart.library.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}

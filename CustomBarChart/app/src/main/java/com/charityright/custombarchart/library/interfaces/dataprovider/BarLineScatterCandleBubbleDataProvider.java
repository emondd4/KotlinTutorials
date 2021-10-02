package com.charityright.custombarchart.library.interfaces.dataprovider;

import com.charityright.custombarchart.library.components.YAxis.AxisDependency;
import com.charityright.custombarchart.library.data.BarLineScatterCandleBubbleData;
import com.charityright.custombarchart.library.utils.Transformer;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(AxisDependency axis);
    boolean isInverted(AxisDependency axis);
    
    float getLowestVisibleX();
    float getHighestVisibleX();

    BarLineScatterCandleBubbleData getData();
}

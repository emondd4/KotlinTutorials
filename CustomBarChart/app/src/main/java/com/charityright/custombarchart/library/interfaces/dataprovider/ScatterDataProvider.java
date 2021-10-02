package com.charityright.custombarchart.library.interfaces.dataprovider;

import com.charityright.custombarchart.library.data.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}

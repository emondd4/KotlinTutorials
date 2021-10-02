package com.charityright.custombarchart.library.interfaces.dataprovider;

import com.charityright.custombarchart.library.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}

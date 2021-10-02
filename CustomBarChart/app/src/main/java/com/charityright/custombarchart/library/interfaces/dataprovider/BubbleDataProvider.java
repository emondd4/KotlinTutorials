package com.charityright.custombarchart.library.interfaces.dataprovider;

import com.charityright.custombarchart.library.data.BubbleData;

public interface BubbleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BubbleData getBubbleData();
}

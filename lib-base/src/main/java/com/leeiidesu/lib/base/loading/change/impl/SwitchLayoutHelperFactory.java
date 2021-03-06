package com.leeiidesu.lib.base.loading.change.impl;


import com.leeiidesu.lib.base.loading.change.Mode;
import com.leeiidesu.lib.base.loading.change.SwitchLayoutHelper;
import com.leeiidesu.lib.base.loading.view.IStatusView;

/**
 * Created by leeiides on 2017/6/23.
 */

public class SwitchLayoutHelperFactory {


    public static SwitchLayoutHelper getSwitchLayoutHelper(Mode mode, IStatusView view) {
        switch (mode) {
            case REPLACE:
                return getSwitchLayoutHelperFromReplaceMode(view);
            case COVER:
                return getSwitchLayoutHelperFromCoverMode(view);
        }
        return getSwitchLayoutHelperFromCoverMode(view);
    }

    public static SwitchLayoutHelper getSwitchLayoutHelperFromReplaceMode(IStatusView view) {
        return new ReplaceSwitchLayoutHelper(view);
    }

    public static SwitchLayoutHelper getSwitchLayoutHelperFromCoverMode(IStatusView view) {
        return new CoverSwitchLayoutHelper(view);
    }

}

package com.iisi.opd.data.out.vo;

import com.iisi.opd.data.out.service.impl.DataOutServiceImpl;

public class DataOutOptionsVo {
    private DataOutServiceImpl.CheckOptions options;
    private boolean onlyPublic;

    public DataOutOptionsVo() {
        this.options = DataOutServiceImpl.CheckOptions.DEFAULT;
        this.onlyPublic = true;
    }

    public DataOutServiceImpl.CheckOptions getOptions() {
        return this.options;
    }

    public void setOptions(DataOutServiceImpl.CheckOptions options) {
        this.options = options;
    }

    public boolean isOnlyPublic() {
        return this.onlyPublic;
    }

    public void setOnlyPublic(boolean onlyPublic) {
        this.onlyPublic = onlyPublic;
    }
}
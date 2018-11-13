package com.iisi.opd.data.out.service;

import java.util.List;
import java.util.Map;

import com.iisi.common.util.Pager;
import com.iisi.opd.data.out.service.impl.DataOutServiceImpl;
import com.iisi.opd.data.out.vo.DataOutOptionsVo;
import com.iisi.opd.exception.OpdException;

public abstract interface DataOutService {
    public abstract Pager getData(Pager paramPager, String paramString1, String paramString2, String paramString3,
            String paramString4, String paramString5, DataOutServiceImpl.CheckOptions paramCheckOptions) throws OpdException;

    public abstract Pager getData(Pager paramPager, String paramString1, String paramString2, String paramString3,
            String paramString4, String paramString5, DataOutOptionsVo paramDataOutOptionsVo) throws OpdException;

    public abstract List<Map<String, Object>> getData(String paramString1, String paramString2, String paramString3,
            String paramString4, String paramString5, String paramString6, String paramString7) throws OpdException;

    public abstract List<Map<String, Object>> getData(String paramString1, String paramString2, String paramString3,
            String paramString4, String paramString5, String paramString6, String paramString7,
            DataOutServiceImpl.CheckOptions paramCheckOptions) throws OpdException;

    public abstract List<Map<String, Object>> getData(String paramString1, String paramString2, String paramString3,
            String paramString4, String paramString5, String paramString6, String paramString7,
            DataOutOptionsVo paramDataOutOptionsVo) throws OpdException;

    public abstract String getOutputData(String paramString, List<Map<String, Object>> paramList) throws OpdException;

    public abstract String getOutputData(String paramString, List<Map<String, Object>> paramList,
            boolean paramBoolean) throws OpdException;
}

/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\data\out\service\DataOutService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
package com.iisi.opd.cfg.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import com.iisi.opd.auth.po.UnitPo;
import com.iisi.opd.category.po.DataCategoryPo;
import com.iisi.opd.tag.po.DataTagPo;

@Entity
@Table(name = "od_data_set")
public class DataSetPo {

    @Id
    @Column(name = "oid", length = 36)
    @GenericGenerator(name = "generator", strategy = "guid")
    @GeneratedValue(generator = "generator")
    private String oid;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "is_enable", nullable = false)
    private Boolean isEnable;

    @Column(name = "is_applied", nullable = false)
    private Boolean isApplied;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic;

    @Column(name = "no_public_reason")
    @Lob
    private String noPublicReason;

    @Column(name = "public_time")
    private Date publicTime;

    @Column(name = "last_edit_user_name", length = 100)
    private String lastEditUserName;

    @Column(name = "last_edit_time")
    private Date lastEditTime;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "data_out_count", nullable = false, columnDefinition = "bigint default '0'")
    private long dataOutCount;

    @Column(name = "download_count", nullable = false, columnDefinition = "bigint default '0'")
    private long downloadCount;

    @Column(name = "view_count", nullable = false, columnDefinition = "bigint default '0'")
    private long viewCount;

    @Column(name = "data_srv_count", nullable = false, columnDefinition = "bigint default '0'")
    private long dataSrvCount;

    @OneToMany(mappedBy = "dataSetPo", fetch = FetchType.LAZY, cascade = {
            javax.persistence.CascadeType.ALL }, orphanRemoval = true)
    private List<DataSetMetadataPo> dataSetMetadataPoList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "od_data_category_oid", referencedColumnName = "oid")
    private DataCategoryPo dataCategoryPo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "od_data_set_tag", joinColumns = { @JoinColumn(name = "od_data_set_oid") }, inverseJoinColumns = {
            @JoinColumn(name = "od_data_tag_oid") })
    private List<DataTagPo> dataTagPoList;

    @OneToMany(mappedBy = "dataSetPo", fetch = FetchType.LAZY, cascade = {
            javax.persistence.CascadeType.REMOVE }, orphanRemoval = false)
    private List<DataCfgPo> dataCfgPoList;

    @OneToMany(mappedBy = "dataSetPo", fetch = FetchType.LAZY, cascade = { javax.persistence.CascadeType.REMOVE })
    @Fetch(FetchMode.SUBSELECT)
    private List<DataCfgApplyPo> dataCfgApplyPoList;

    @OneToOne(mappedBy = "dataSetPo", fetch = FetchType.LAZY, cascade = {
            javax.persistence.CascadeType.REMOVE }, orphanRemoval = true)
    private DataSetApplyPo dataSetApplyPo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_oid", referencedColumnName = "oid", nullable = false)
    private UnitPo unitPo;

    public DataSetPo() {
        this.isActive = Boolean.TRUE;
        this.isEnable = Boolean.FALSE;
        this.isPublic = Boolean.FALSE;
        this.isApplied = Boolean.FALSE;
        this.dataTagPoList = new ArrayList();
        this.dataSetMetadataPoList = new ArrayList();
        this.dataCfgPoList = new ArrayList();
        this.dataCfgApplyPoList = new ArrayList();
    }

    public String getOid() {
        return this.oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isActive() {
        return this.isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean isEnable() {
        return this.isEnable;
    }

    public void setEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public Boolean isApplied() {
        return this.isApplied;
    }

    public void setApplied(Boolean isApplied) {
        this.isApplied = isApplied;
    }

    public Boolean isPublic() {
        return this.isPublic;
    }

    public void setPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getNoPublicReason() {
        return this.noPublicReason;
    }

    public void setNoPublicReason(String noPublicReason) {
        this.noPublicReason = noPublicReason;
    }

    public Date getPublicTime() {
        return this.publicTime;
    }

    public void setPublicTime(Date publicTime) {
        this.publicTime = publicTime;
    }

    public String getLastEditUserName() {
        return this.lastEditUserName;
    }

    public void setLastEditUserName(String lastEditUserName) {
        this.lastEditUserName = lastEditUserName;
    }

    public Date getLastEditTime() {
        return this.lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public List<DataSetMetadataPo> getDataSetMetadataPoList() {
        return this.dataSetMetadataPoList;
    }

    public void setDataSetMetadataPoList(List<DataSetMetadataPo> dataSetMetadataPoList) {
        this.dataSetMetadataPoList = dataSetMetadataPoList;
    }

    public DataCategoryPo getDataCategoryPo() {
        return this.dataCategoryPo;
    }

    public void setDataCategoryPo(DataCategoryPo dataCategoryPo) {
        this.dataCategoryPo = dataCategoryPo;
    }

    public List<DataTagPo> getDataTagPoList() {
        return this.dataTagPoList;
    }

    public void setDataTagPoList(List<DataTagPo> dataTagPoList) {
        this.dataTagPoList.clear();
        this.dataTagPoList.addAll(dataTagPoList);
    }

    public void addDataTagPoList(List<DataTagPo> dataTagPoList) {
        this.dataTagPoList.addAll(dataTagPoList);
    }

    public List<DataCfgPo> getDataCfgPoList() {
        return this.dataCfgPoList;
    }

    public void setDataCfgPoList(List<DataCfgPo> dataCfgPoList) {
        this.dataCfgPoList = dataCfgPoList;
    }

    public void addDataCfgPoList(List<DataCfgPo> dataCfgPoList) {
        this.dataCfgPoList.addAll(dataCfgPoList);
    }

    public List<DataCfgApplyPo> getDataCfgApplyPoList() {
        return this.dataCfgApplyPoList;
    }

    public void setDataCfgApplyPoList(List<DataCfgApplyPo> dataCfgApplyPoList) {
        this.dataCfgApplyPoList = dataCfgApplyPoList;
    }

    public void addDataCfgApplyPoList(List<DataCfgApplyPo> dataCfgApplyPoList) {
        this.dataCfgApplyPoList = dataCfgApplyPoList;
    }

    public DataSetApplyPo getDataSetApplyPo() {
        return this.dataSetApplyPo;
    }

    public void setDataSetApplyPo(DataSetApplyPo dataSetApplyPo) {
        this.dataSetApplyPo = dataSetApplyPo;
    }

    public UnitPo getUnitPo() {
        return this.unitPo;
    }

    public void setUnitPo(UnitPo unitPo) {
        this.unitPo = unitPo;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getDataOutCount() {
        return this.dataOutCount;
    }

    public void addDataOutCount() {
        this.dataOutCount += 1L;
    }

    public long getDownloadCount() {
        return this.downloadCount;
    }

    public void addDownloadCount() {
        this.downloadCount += 1L;
    }

    public long getViewCount() {
        return this.viewCount;
    }

    public void addViewCount() {
        this.viewCount += 1L;
    }

    public long getDataSrvCount() {
        return this.dataSrvCount;
    }

    public void addDataSrvCount() {
        this.dataSrvCount += 1L;
    }

    public static enum OrderByOption {
        NEWEST_DESC, OLDEST_ASC, HOT_DESC, DATA_OUT_DESC, LAST_EDIT_TIME_DESC, VIEW_DESC;
    }
}
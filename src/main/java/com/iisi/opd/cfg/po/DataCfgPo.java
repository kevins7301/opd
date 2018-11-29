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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "od_data_cfg")
public class DataCfgPo {
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
    @Column(name = "is_structured", nullable = false)
    private Boolean isStructured;
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
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "last_edit_user_name", length = 100)
    private String lastEditUserName;
    @Column(name = "last_edit_time")
    private Date lastEditTime;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "resource_modified_date")
    private Date resourceModifiedDate;
    @Column(name = "data_count")
    private int dataCount;
    @OneToMany(mappedBy = "dataCfgPo", fetch = FetchType.LAZY, cascade = {
            javax.persistence.CascadeType.ALL }, orphanRemoval = true)
    @OrderBy("fieldOrder")
    private List<DataFieldCfgPo> dataFieldCfgPoList;
    @OneToMany(mappedBy = "dataCfgPo", fetch = FetchType.LAZY, cascade = {
            javax.persistence.CascadeType.ALL }, orphanRemoval = true)
    private List<DataCfgMetadataPo> dataCfgMetadataPoList;
    @OneToOne(mappedBy = "dataCfgPo", fetch = FetchType.LAZY, cascade = {
            javax.persistence.CascadeType.ALL }, orphanRemoval = true)
    private DataCfgFilePo dataCfgFilePo;
    @OneToOne(mappedBy = "dataCfgPo", fetch = FetchType.LAZY, cascade = {
            javax.persistence.CascadeType.ALL }, orphanRemoval = true)
    private DataCfgTableInfoPo dataCfgTableInfoPo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "od_data_set_oid", referencedColumnName = "oid")
    private DataSetPo dataSetPo;
    @OneToOne(mappedBy = "dataCfgPo", fetch = FetchType.EAGER, cascade = {
            javax.persistence.CascadeType.ALL }, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private DataCfgApplyPo dataCfgApplyPo;
    @OneToMany(mappedBy = "dataCfgPo", fetch = FetchType.LAZY, cascade = {
            javax.persistence.CascadeType.ALL }, orphanRemoval = true)
    private List<DataCfgZipFilePo> dataCfgZipFilePoList;

    public DataCfgPo() {
        this.isStructured = Boolean.TRUE;
        this.isActive = Boolean.TRUE;
        this.isEnable = Boolean.FALSE;
        this.isPublic = Boolean.FALSE;
        this.isApplied = Boolean.FALSE;
        this.dataCount = 0;
        this.dataFieldCfgPoList = new ArrayList();
        this.dataCfgMetadataPoList = new ArrayList();
        this.dataCfgZipFilePoList = new ArrayList();
    }

    public String getOid() {
        return this.oid;
    }

    public void setOid(final String oid) {
        this.oid = oid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Boolean isStructured() {
        return this.isStructured;
    }

    public void setStructured(final Boolean isStructured) {
        this.isStructured = isStructured;
    }

    public Boolean isActive() {
        return this.isActive;
    }

    public void setActive(final Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean isEnable() {
        return this.isEnable;
    }

    public void setEnable(final Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public Boolean isApplied() {
        return this.isApplied;
    }

    public void setApplied(final Boolean isApplied) {
        this.isApplied = isApplied;
    }

    public Boolean isPublic() {
        return this.isPublic;
    }

    public void setPublic(final Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getNoPublicReason() {
        return this.noPublicReason;
    }

    public void setNoPublicReason(final String noPublicReason) {
        this.noPublicReason = noPublicReason;
    }

    public Date getPublicTime() {
        return this.publicTime;
    }

    public void setPublicTime(final Date publicTime) {
        this.publicTime = publicTime;
    }

    public String getLastEditUserName() {
        return this.lastEditUserName;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(final Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(final Date endTime) {
        this.endTime = endTime;
    }

    public void setLastEditUserName(final String lastEditUserName) {
        this.lastEditUserName = lastEditUserName;
    }

    public Date getLastEditTime() {
        return this.lastEditTime;
    }

    public void setLastEditTime(final Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public List<DataFieldCfgPo> getDataFieldCfgPoList() {
        return this.dataFieldCfgPoList;
    }

    public void setDataFieldCfgPoList(final List<DataFieldCfgPo> dataFieldCfgPoList) {
        this.dataFieldCfgPoList = dataFieldCfgPoList;
    }

    public List<DataCfgMetadataPo> getDataCfgMetadataPoList() {
        return this.dataCfgMetadataPoList;
    }

    public void setDataCfgMetadataPoList(final List<DataCfgMetadataPo> dataCfgMetadataPoList) {
        this.dataCfgMetadataPoList = dataCfgMetadataPoList;
    }

    public DataCfgFilePo getDataCfgFilePo() {
        return this.dataCfgFilePo;
    }

    public void setDataCfgFilePo(final DataCfgFilePo dataCfgFilePo) {
        this.dataCfgFilePo = dataCfgFilePo;
    }

    public DataCfgTableInfoPo getDataCfgTableInfoPo() {
        return this.dataCfgTableInfoPo;
    }

    public void setDataCfgTableInfoPo(final DataCfgTableInfoPo dataCfgTableInfoPo) {
        this.dataCfgTableInfoPo = dataCfgTableInfoPo;
    }

    public DataSetPo getDataSetPo() {
        return this.dataSetPo;
    }

    public void setDataSetPo(final DataSetPo dataSetPo) {
        this.dataSetPo = dataSetPo;
    }

    public DataCfgApplyPo getDataCfgApplyPo() {
        return this.dataCfgApplyPo;
    }

    public void setDataCfgApplyPo(final DataCfgApplyPo dataCfgApplyPo) {
        this.dataCfgApplyPo = dataCfgApplyPo;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public Date getResourceModifiedDate() {
        return this.resourceModifiedDate;
    }

    public void setResourceModifiedDate(final Date resourceModifiedDate) {
        this.resourceModifiedDate = resourceModifiedDate;
    }

    public int getDataCount() {
        return this.dataCount;
    }

    public void setDataCount(final int dataCount) {
        this.dataCount = dataCount;
    }

    public List<DataCfgZipFilePo> getDataCfgZipFilePoList() {
        return this.dataCfgZipFilePoList;
    }

    public void addDataCfgZipFilePo(final DataCfgZipFilePo dataCfgZipFilePo) {
        this.dataCfgZipFilePoList.add(dataCfgZipFilePo);
    }

}
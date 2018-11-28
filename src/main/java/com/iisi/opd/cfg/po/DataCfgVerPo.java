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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "od_data_cfg_ver")
public class DataCfgVerPo {
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
    @Column(name = "data_set_name", length = 100)
    private String dataSetName;
    @Column(name = "is_structured", nullable = false)
    private Boolean isStructured;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    @Column(name = "is_enable", nullable = false)
    private Boolean isEnable;
    @Column(name = "is_public", nullable = false)
    private Boolean isPublic;
    @Column(name = "no_public_reason")
    @Lob
    private String noPublicReason;
    @Column(name = "edit_user_name", length = 100)
    private String editUserName;
    @Column(name = "public_time")
    private Date publicTime;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "log_time", nullable = false)
    private Date logTime;
    @OneToMany(mappedBy = "dataCfgVerPo", fetch = FetchType.LAZY, cascade = {
            javax.persistence.CascadeType.ALL }, orphanRemoval = true)
    @OrderBy("fieldOrder")
    private List<DataFieldCfgVerPo> dataFieldCfgVerPoList;
    @OneToMany(mappedBy = "dataCfgVerPo", fetch = FetchType.LAZY, cascade = {
            javax.persistence.CascadeType.ALL }, orphanRemoval = true)
    private List<DataCfgVerMetadataPo> dataCfgVerMetadataPoList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "od_data_cfg_oid", referencedColumnName = "oid", nullable = false)
    private DataCfgPo dataCfgPo;

    public DataCfgVerPo() {
        this.isStructured = Boolean.TRUE;
        this.isActive = Boolean.TRUE;
        this.isEnable = Boolean.FALSE;
        this.isPublic = Boolean.FALSE;
        this.dataFieldCfgVerPoList = new ArrayList();
        this.dataCfgVerMetadataPoList = new ArrayList();
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

    public String getDataSetName() {
        return this.dataSetName;
    }

    public void setDataSetName(String dataSetName) {
        this.dataSetName = dataSetName;
    }

    public Boolean isStructured() {
        return this.isStructured;
    }

    public void setStructured(Boolean isStructured) {
        this.isStructured = isStructured;
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

    public String getEditUserName() {
        return this.editUserName;
    }

    public void setEditUserName(String editUserName) {
        this.editUserName = editUserName;
    }

    public Date getPublicTime() {
        return this.publicTime;
    }

    public void setPublicTime(Date publicTime) {
        this.publicTime = publicTime;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getLogTime() {
        return this.logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public List<DataFieldCfgVerPo> getDataFieldCfgVerPoList() {
        return this.dataFieldCfgVerPoList;
    }

    public void setDataFieldCfgVerPoList(List<DataFieldCfgVerPo> dataFieldCfgVerPoList) {
        this.dataFieldCfgVerPoList = dataFieldCfgVerPoList;
    }

    public List<DataCfgVerMetadataPo> getDataCfgVerMetadataList() {
        return this.dataCfgVerMetadataPoList;
    }

    public void setDataCfgVerMetadataList(List<DataCfgVerMetadataPo> dataCfgVerMetadataPoList) {
        this.dataCfgVerMetadataPoList = dataCfgVerMetadataPoList;
    }

    public DataCfgPo getDataCfgPo() {
        return this.dataCfgPo;
    }

    public void setDataCfgPo(DataCfgPo dataCfgPo) {
        this.dataCfgPo = dataCfgPo;
    }
}
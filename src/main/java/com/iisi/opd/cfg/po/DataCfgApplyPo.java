package com.iisi.opd.cfg.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "od_data_cfg_apply")
public class DataCfgApplyPo {
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
    @Column(name = "is_enable", nullable = false)
    private Boolean isEnable;
    @Column(name = "is_public", nullable = false)
    private Boolean isPublic;
    @Column(name = "no_public_reason")
    @Lob
    private String noPublicReason;
    @Column(name = "comment", columnDefinition = "varchar(max)")
    private String comment;
    @Column(name = "apply_user_name", length = 100, nullable = false)
    private String applyUserName;
    @Column(name = "apply_time", nullable = false)
    private Date applyTime;
    @Column(name = "refuse_time")
    private Date refuseTime;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "action_type", nullable = false)
    private ActionType actionType;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "data_status", nullable = false)
    private DataStatus dataStatus;
    @OneToMany(mappedBy = "dataCfgApplyPo", fetch = FetchType.LAZY, cascade = {
            javax.persistence.CascadeType.ALL }, orphanRemoval = true)
    @OrderBy("fieldOrder")
    private List<DataFieldCfgApplyPo> dataFieldCfgApplyPoList;
    @OneToMany(mappedBy = "dataCfgApplyPo", fetch = FetchType.LAZY, cascade = {
            javax.persistence.CascadeType.ALL }, orphanRemoval = true)
    private List<DataCfgMetadataApplyPo> dataCfgMetadataApplyPoList;
    @OneToOne(mappedBy = "dataCfgApplyPo", fetch = FetchType.LAZY, cascade = {
            javax.persistence.CascadeType.ALL }, orphanRemoval = true)
    private DataCfgFileApplyPo dataCfgFileApplyPo;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "od_data_cfg_oid", referencedColumnName = "oid")
    private DataCfgPo dataCfgPo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "od_data_set_apply_oid", referencedColumnName = "oid")
    private DataSetApplyPo dataSetApplyPo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "od_data_set_oid", referencedColumnName = "oid")
    private DataSetPo dataSetPo;

    public DataCfgApplyPo() {
        this.isStructured = Boolean.TRUE;
        this.isEnable = Boolean.FALSE;
        this.isPublic = Boolean.FALSE;
        this.dataFieldCfgApplyPoList = new ArrayList();
        this.dataCfgMetadataApplyPoList = new ArrayList();
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

    public Boolean isEnable() {
        return this.isEnable;
    }

    public void setEnable(final Boolean isEnable) {
        this.isEnable = isEnable;
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

    public String getComment() {
        return this.comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public String getApplyUserName() {
        return this.applyUserName;
    }

    public void setApplyUserName(final String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public Date getApplyTime() {
        return this.applyTime;
    }

    public void setApplyTime(final Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getRefuseTime() {
        return this.refuseTime;
    }

    public void setRefuseTime(final Date refuseTime) {
        this.refuseTime = refuseTime;
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

    public ActionType getActionType() {
        return this.actionType;
    }

    public void setActionType(final ActionType type) {
        this.actionType = type;
    }

    public DataStatus getDataStatus() {
        return this.dataStatus;
    }

    public void setDataStatus(final DataStatus status) {
        this.dataStatus = status;
    }

    public List<DataFieldCfgApplyPo> getDataFieldCfgApplyPoList() {
        return this.dataFieldCfgApplyPoList;
    }

    public void setDataFieldCfgApplyPoList(final List<DataFieldCfgApplyPo> dataFieldCfgApplyPoList) {
        this.dataFieldCfgApplyPoList = dataFieldCfgApplyPoList;
    }

    public List<DataCfgMetadataApplyPo> getDataCfgMetadataApplyPoList() {
        return this.dataCfgMetadataApplyPoList;
    }

    public void setDataCfgMetadataApplyPoList(final List<DataCfgMetadataApplyPo> dataCfgMetadataApplyPoList) {
        this.dataCfgMetadataApplyPoList = dataCfgMetadataApplyPoList;
    }

    public DataCfgFileApplyPo getDataCfgFileApplyPo() {
        return this.dataCfgFileApplyPo;
    }

    public void setDataCfgFileApplyPo(final DataCfgFileApplyPo dataCfgFileApplyPo) {
        this.dataCfgFileApplyPo = dataCfgFileApplyPo;
    }

    public DataCfgPo getDataCfgPo() {
        return this.dataCfgPo;
    }

    public void setDataCfgPo(final DataCfgPo dataCfgPo) {
        this.dataCfgPo = dataCfgPo;
    }

    public DataSetApplyPo getDataSetApplyPo() {
        return this.dataSetApplyPo;
    }

    public void setDataSetApplyPo(final DataSetApplyPo dataSetApplyPo) {
        this.dataSetApplyPo = dataSetApplyPo;
    }

    public DataSetPo getDataSetPo() {
        return this.dataSetPo;
    }

    public void setDataSetPo(final DataSetPo dataSetPo) {
        this.dataSetPo = dataSetPo;
    }

    public static enum ActionType {
        ENABLE(1), EDIT(2), DISABLE(3);

        private int actionType;

        private ActionType(final int actionType) {
            this.actionType = actionType;
        }

        public int getActionType() {
            return this.actionType;
        }
    }

    public static enum DataStatus {
        APPLY(1), AGREE(2), REFUSE(3);

        private int dataStatus;

        private DataStatus(final int dataStatus) {
            this.dataStatus = dataStatus;
        }

        public int getDataStatus() {
            return this.dataStatus;
        }
    }

}
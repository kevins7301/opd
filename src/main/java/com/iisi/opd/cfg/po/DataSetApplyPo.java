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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.iisi.opd.auth.po.UnitPo;
import com.iisi.opd.category.po.DataCategoryPo;
import com.iisi.opd.tag.po.DataTagPo;

@Entity
@Table(name = "od_data_set_apply")
public class DataSetApplyPo {

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

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "action_type", nullable = false)
    private ActionType actionType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "data_status", nullable = false)
    private DataStatus dataStatus;

    @OneToMany(mappedBy = "dataSetApplyPo", fetch = FetchType.LAZY, cascade = { javax.persistence.CascadeType.ALL })
    private List<DataSetMetadataApplyPo> dataSetMetadataPoList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "od_data_category_oid", referencedColumnName = "oid")
    private DataCategoryPo dataCategoryPo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "od_data_set_apply_tag", joinColumns = {
            @JoinColumn(name = "od_data_set_apply_oid") }, inverseJoinColumns = { @JoinColumn(name = "od_data_tag_oid") })
    private List<DataTagPo> dataTagPoList;

    @OneToMany(mappedBy = "dataSetApplyPo", fetch = FetchType.LAZY, cascade = { javax.persistence.CascadeType.REMOVE })
    private List<DataCfgApplyPo> dataCfgApplyPoList;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "od_data_set_oid", referencedColumnName = "oid")
    private DataSetPo dataSetPo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_oid", referencedColumnName = "oid", nullable = false)
    private UnitPo unitPo;

    public DataSetApplyPo() {
        this.isEnable = Boolean.FALSE;
        this.isPublic = Boolean.FALSE;
        this.dataTagPoList = new ArrayList();
        this.dataSetMetadataPoList = new ArrayList();
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

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getApplyUserName() {
        return this.applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public ActionType getActionType() {
        return this.actionType;
    }

    public void setActionType(ActionType type) {
        this.actionType = type;
    }

    public Date getApplyTime() {
        return this.applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getRefuseTime() {
        return this.refuseTime;
    }

    public void setRefuseTime(Date refuseTime) {
        this.refuseTime = refuseTime;
    }

    public DataStatus getDataStatus() {
        return this.dataStatus;
    }

    public void setDataStatus(DataStatus status) {
        this.dataStatus = status;
    }

    public List<DataSetMetadataApplyPo> getDataSetMetadataPoList() {
        return this.dataSetMetadataPoList;
    }

    public void setDataSetMetadataPoList(List<DataSetMetadataApplyPo> dataSetMetadataPoList) {
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
        this.dataTagPoList = dataTagPoList;
    }

    public List<DataCfgApplyPo> getDataCfgApplyPoList() {
        return this.dataCfgApplyPoList;
    }

    public void setDataCfgApplyPoList(List<DataCfgApplyPo> dataCfgApplyPoList) {
        this.dataCfgApplyPoList = dataCfgApplyPoList;
    }

    public DataSetPo getDataSetPo() {
        return this.dataSetPo;
    }

    public void setDataSetPo(DataSetPo dataSetPo) {
        this.dataSetPo = dataSetPo;
    }

    public UnitPo getUnitPo() {
        return this.unitPo;
    }

    public void setUnitPo(UnitPo unitPo) {
        this.unitPo = unitPo;
    }

    public static enum ActionType {
        ENABLE(1), EDIT(2), DISABLE(3);

        private int actionType;

        private ActionType(int actionType) {
            this.actionType = actionType;
        }

        public int getActionType() {
            return this.actionType;
        }
    }

    public static enum DataStatus {
        APPLY(1), AGREE(2), REFUSE(3);

        private int dataStatus;

        private DataStatus(int dataStatus) {
            this.dataStatus = dataStatus;
        }

        public int getDataStatus() {
            return this.dataStatus;
        }
    }

    public static enum ApplyOrderByOption {
        APPLY_TIME_DESC;

        private ApplyOrderByOption() {
        }
    }
}
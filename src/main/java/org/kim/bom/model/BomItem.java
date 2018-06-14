package org.kim.bom.model;

import org.kim.bom.annotation.ExcelCell;

import java.util.Date;

/**
 * 一份BOM的内容项，一个BomHeader对象对应多个BomItem
 */
public class BomItem {
    /**
     * BomItem的ID，在数据库中是一个自增长列
     */
    private int id;

    /**
     * 对应的BomHeader的Id
     */
    private int bomHeaderId;

    /**
     * 上级节点的物料号
     */
    @ExcelCell(columnIndex = 0)
    private String parentItem;

    /**
     * Item的物料号
     */
    @ExcelCell(columnIndex = 1)
    private String itemNumber;

    /**
     * 使用数量
     */
    @ExcelCell(columnIndex = 2)
    private String quantity;

    /**
     * 版本号
     */
    @ExcelCell(columnIndex = 3)
    private String revision;

    /**
     * 单位
     */
    @ExcelCell(columnIndex = 4)
    private String um;

    /**
     * 物料描述
     */
    @ExcelCell(columnIndex = 5)
    private String description;

    @ExcelCell(columnIndex = 6)
    private String effectiveDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBomHeaderId() {
        return bomHeaderId;
    }

    public void setBomHeaderId(int bomHeaderId) {
        this.bomHeaderId = bomHeaderId;
    }

    public String getParentItem() {
        return parentItem;
    }

    public void setParentItem(String parentItem) {
        this.parentItem = parentItem;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}

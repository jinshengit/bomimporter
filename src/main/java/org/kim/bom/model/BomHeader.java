package org.kim.bom.model;

import org.kim.bom.annotation.ExcelCell;
import org.kim.bom.annotation.ExcelFile;

import java.util.Date;
import java.util.List;

@ExcelFile(columnCount = 7, ignoreRows = 6)
/**
 * 一份BOM的对象
 */
public class BomHeader {

    public BomHeader() { }

    public BomHeader(String partNumber, String revision, String approvalDate) {
        this.partNumber = partNumber;
        this.revision = revision;
        this.approvalDate = approvalDate;
        createTime = new Date();
    }

    /**
     * BOM的ID，在数据库中是一个自增长列
     */
    private int id;

    /**
     * BOM的物料号
     */
    @ExcelCell(rowIndex = 0,  columnIndex = 1)
    private String partNumber;

    /**
     * BOM的描述
     */
    @ExcelCell(rowIndex = 1, columnIndex = 1)
    private String description;

    /**
     * BOM的版本号
     */
    @ExcelCell(rowIndex = 2, columnIndex = 1)
    private String revision;

    /**
     * BOM发布的时间
     */
    @ExcelCell(rowIndex = 3, columnIndex = 1)
    private String approvalDate;

    /**
     * BOM创建(上传)的时间
     */
    private Date createTime;

    private List<BomItem> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<BomItem> getItems() {
        return items;
    }

    public void setItems(List<BomItem> items) {
        this.items = items;
    }
}

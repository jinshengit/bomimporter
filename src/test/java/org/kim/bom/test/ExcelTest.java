package org.kim.bom.test;

import junit.framework.TestCase;
import org.kim.bom.model.BomHeader;
import org.kim.bom.model.BomItem;
import org.kim.bom.util.ExcelHelper;

public class ExcelTest extends TestCase {

    public void testImport() {
        String filePath = "D:\\bom.xlsx";

        BomHeader bomHeader = ExcelHelper.readExcel(filePath);

        if (bomHeader != null) {
            System.out.println("PartNumber: " + bomHeader.getPartNumber());
            System.out.println("Description: " + bomHeader.getDescription());
            System.out.println("Revision: " + bomHeader.getRevision());
            System.out.println("Date: " + bomHeader.getApprovalDate());

            if (bomHeader.getItems() != null) {
                for (BomItem item : bomHeader.getItems()) {
                    System.out.println(item.getParentItem() + "\t" + item.getItemNumber() + "\t" + item.getQuantity() + "\t" + item.getRevision() + "\t" + item.getEffectiveDate());
                }
            }
        }
    }
}

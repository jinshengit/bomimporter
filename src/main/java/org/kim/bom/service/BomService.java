package org.kim.bom.service;

import org.kim.bom.model.BomHeader;

public interface BomService {
    BomHeader parseBomHeader(String filePath);
}

package com.audit.asset.dto;

import com.audit.asset.model.Asset;
import java.time.LocalDate;

public record AssetRequest(
        String serialNumber,
        String name,
        String category,
        Asset.AssetStatus status,
        LocalDate nextCalibrationDate
) {}
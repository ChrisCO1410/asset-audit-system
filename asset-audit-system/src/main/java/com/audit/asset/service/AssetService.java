package com.audit.asset.service;

import com.audit.asset.dto.AssetRequest;
import com.audit.asset.model.Asset;
import com.audit.asset.repository.AssetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public Asset getAssetById(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Asset not found with id: " + id));
    }

    public Asset createAsset(AssetRequest request) {
        if (assetRepository.existsBySerialNumber(request.serialNumber())) {
            throw new IllegalArgumentException("Asset with serial number " + request.serialNumber() + " already exists.");
        }

        Asset asset = Asset.builder()
                .serialNumber(request.serialNumber())
                .name(request.name())
                .category(request.category())
                .status(request.status())
                .nextCalibrationDate(request.nextCalibrationDate())
                .build();

        return assetRepository.save(asset);
    }

    public Asset updateAsset(Long id, AssetRequest request) {
        Asset asset = getAssetById(id);

        asset.setName(request.name());
        asset.setCategory(request.category());
        asset.setStatus(request.status());
        asset.setNextCalibrationDate(request.nextCalibrationDate());

        return assetRepository.save(asset);
    }

    public void deleteAsset(Long id) {
        if (!assetRepository.existsById(id)) {
            throw new IllegalArgumentException("Asset not found with id: " + id);
        }
        assetRepository.deleteById(id);
    }
}
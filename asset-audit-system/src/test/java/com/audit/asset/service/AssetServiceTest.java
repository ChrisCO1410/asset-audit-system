package com.audit.asset.service;

import com.audit.asset.dto.AssetRequest;
import com.audit.asset.model.Asset;
import com.audit.asset.repository.AssetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssetServiceTest {

    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private AssetService assetService;

    @Test
    void createAsset_Success() {
        AssetRequest request = new AssetRequest("SN-001", "Scope", "Tools", Asset.AssetStatus.AVAILABLE, LocalDate.now());
        Asset asset = Asset.builder().id(1L).serialNumber("SN-001").name("Scope").build();

        when(assetRepository.existsBySerialNumber("SN-001")).thenReturn(false);
        when(assetRepository.save(any(Asset.class))).thenReturn(asset);

        Asset created = assetService.createAsset(request);

        assertNotNull(created);
        assertEquals("SN-001", created.getSerialNumber());
        verify(assetRepository, times(1)).save(any(Asset.class));
    }
}
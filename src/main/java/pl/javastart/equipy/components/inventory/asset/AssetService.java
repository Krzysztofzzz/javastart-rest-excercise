package pl.javastart.equipy.components.inventory.asset;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.components.user.DuplicateSerialNumberException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetService {

    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    public AssetService(AssetRepository assetRepository, AssetMapper assetMapper) {
        this.assetRepository = assetRepository;
        this.assetMapper = assetMapper;
    }

    List<AssetDto> findAll() {
        return assetRepository.findAll()
                .stream()
                .map(assetMapper::toDto)
                .collect(Collectors.toList());
    }

    List<AssetDto> findAllByNameOrSerialNumber(String search) {
        return assetRepository.findAllByNameOrSerialNumber(search)
                .stream()
                .map(assetMapper::toDto)
                .collect(Collectors.toList());
    }

    Optional<AssetDto> findById(Long id) {
        return assetRepository.findById(id).map(assetMapper::toDto);
    }

    List<AssetAssignmentDto> getAssetAssignments(Long id) {
        return assetRepository.findById(id)
                .map(Asset::getAssignments)
                .orElseThrow(AssetNotFoundException::new)
                .stream()
                .map(AssetAssignmentMapper::toDto)
                .collect(Collectors.toList());
    }

    AssetDto save(AssetDto assetDto) {
        Optional<Asset> assetBySerialNumber = assetRepository.findBySerialNumber(assetDto.getSerialNumber());
        assetBySerialNumber.ifPresent(a -> {
            throw new DuplicateSerialNumberException();
        });
        return mapAndSave(assetDto);
    }

    AssetDto update(AssetDto assetDto) {
        Optional<Asset> assetBySerialNumber = assetRepository.findBySerialNumber(assetDto.getSerialNumber());
        assetBySerialNumber.ifPresent(a -> {
            if (!a.getId().equals(assetDto.getId()))
                throw new DuplicateSerialNumberException();
        });
        return mapAndSave(assetDto);
    }

    private AssetDto mapAndSave(AssetDto assetDto) {
        Asset assetEntity = assetMapper.toEntity(assetDto);
        Asset savedAsset = assetRepository.save(assetEntity);
        return assetMapper.toDto(savedAsset);
    }
}

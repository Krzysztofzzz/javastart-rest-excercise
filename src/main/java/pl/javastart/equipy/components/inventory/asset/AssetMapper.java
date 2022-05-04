package pl.javastart.equipy.components.inventory.asset;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.components.inventory.category.Category;
import pl.javastart.equipy.components.inventory.category.CategoryRepository;

import java.util.Optional;

@Service
public class AssetMapper {
    private CategoryRepository categoryRepository;

    public AssetMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    AssetDto toDto(Asset asset){
        AssetDto assetDto = new AssetDto();
        assetDto.setId(asset.getId());
        assetDto.setName(asset.getName());
        assetDto.setDescription(asset.getDescription());
        assetDto.setSerialNumber(asset.getSerialNumber());
        if (asset.getCategory() != null)
            assetDto.setCategory(asset.getCategory().getName());
        return assetDto;
    }

    Asset toEntity(AssetDto assetDto){
        Asset asset = new Asset();
        asset.setId(assetDto.getId());
        asset.setName(assetDto.getName());
        asset.setDescription(assetDto.getDescription());
        asset.setSerialNumber(assetDto.getSerialNumber());
        Optional<Category> category = categoryRepository.findByName(assetDto.getCategory());
        category.ifPresent(asset::setCategory);
        return asset;
    }
}

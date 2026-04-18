package fr.didiersenou.defimeningesapi.mapper;

import fr.didiersenou.defimeningesapi.dto.CategoryDTO;
import fr.didiersenou.defimeningesapi.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDTO toDTO(Category entity) {
        if (entity == null) {
            return null;
        }
        return new CategoryDTO(
                entity.getId(),
                entity.getLocale(),
                entity.getName(),
                entity.getDefaultTimeLimit());
    }
}
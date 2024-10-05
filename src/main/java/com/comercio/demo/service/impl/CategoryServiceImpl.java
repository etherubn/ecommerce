package com.comercio.demo.service.impl;

import com.comercio.demo.dto.request.CreateCategoryDto;
import com.comercio.demo.dto.response.ResponseCategoryDto;
import com.comercio.demo.entity.Category;
import com.comercio.demo.handlerException.IdWrongValueException;
import com.comercio.demo.handlerException.NotFoundException;
import com.comercio.demo.handlerException.RepeatedException;
import com.comercio.demo.repository.CategoryRepository;
import com.comercio.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Transactional(readOnly = true)
    @Override
    public List<ResponseCategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(category -> {
                    ResponseCategoryDto responseCategoryDto = modelMapper.map(category, ResponseCategoryDto.class);
                    return responseCategoryDto;
                })
                .toList();
    }

    @Transactional
    @Override
    public ResponseCategoryDto create(CreateCategoryDto createCategoryDto) {
        if (categoryRepository.existsByTipo(createCategoryDto.getTipo())){
            throw new RepeatedException("Category");
        }

        Category category = modelMapper.map(createCategoryDto, Category.class);
        Category categorySaved = categoryRepository.save(category);

        return modelMapper.map(categorySaved, ResponseCategoryDto.class);
    }

    @Transactional
    @Override
    public ResponseCategoryDto update(Long id, CreateCategoryDto createCategoryDto) {

        if (!categoryRepository.existsById(id)){
            throw new NotFoundException("Category",id);
        }

        if (categoryRepository.existsByTipo(createCategoryDto.getTipo())){
            throw new RepeatedException("Category");
        }
        
        Category category = modelMapper.map(createCategoryDto, Category.class);

        category.setId(id);

        Category categoryUpdated = categoryRepository.save(category);

        return modelMapper.map(categoryUpdated, ResponseCategoryDto.class);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        existedId(id);
        Category category = categoryRepository.findById(id).orElseThrow(()-> new NotFoundException("Category",id));

        categoryRepository.delete(category);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseCategoryDto getById(Long id) {
        existedId(id);
        Category category = categoryRepository.findById(id).orElseThrow(()-> new NotFoundException("Category",id));
        return modelMapper.map(category, ResponseCategoryDto.class);
    }



    private static void existedId(Long id) {
        if (id ==null || id <=0){
            throw new IdWrongValueException("Category", id);
        }
    }

}

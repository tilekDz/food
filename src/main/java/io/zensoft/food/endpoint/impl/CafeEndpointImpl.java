package io.zensoft.food.endpoint.impl;

import io.zensoft.food.domain.CafeCreateRequest;
import io.zensoft.food.domain.CafeUpdateRequest;
import io.zensoft.food.dto.request.CafeCreateRequestDto;
import io.zensoft.food.dto.CafeDto;
import io.zensoft.food.dto.request.CafeUpdateRequestDto;
import io.zensoft.food.endpoint.CafeEndpoint;
import io.zensoft.food.mapper.CafeMapper;
import io.zensoft.food.model.Cafe;
import io.zensoft.food.service.CafeService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CafeEndpointImpl implements CafeEndpoint {

    private final CafeService cafeService;
    private final CafeMapper cafeMapper;

    @Autowired
    public CafeEndpointImpl(CafeService cafeService, CafeMapper cafeMapper) {
        this.cafeService = cafeService;
        this.cafeMapper = cafeMapper;
    }

    @Transactional
    @Override
    public CafeDto save(@NonNull CafeCreateRequestDto request) {
        CafeCreateRequest cafeCreateRequest = new CafeCreateRequest(request.getName());

        Cafe cafe = cafeService.add(cafeCreateRequest);

        return cafeMapper.toCafeDto(cafe);
    }

    @Transactional
    @Override
    public CafeDto update(@NonNull Long id, @NonNull CafeUpdateRequestDto request) {

        CafeUpdateRequest cafeUpdateRequest = new CafeUpdateRequest(
                id,
                request.getName()
        );

        Cafe updatedCafe = cafeService.update(cafeUpdateRequest);

        return cafeMapper.toCafeDto(updatedCafe);
    }

    @Transactional
    @Override
    public void delete(@NonNull Long id) {
        cafeService.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CafeDto> getAll() {
        return cafeService.getAll().stream()
                .map(cafeMapper::toCafeDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public CafeDto getById(@NonNull Long id) {
        return cafeMapper.toCafeDto(cafeService.getById(id));
    }
}
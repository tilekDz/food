package io.zensoft.food.service.impl;

import io.zensoft.food.domain.DishCreateRequest;
import io.zensoft.food.domain.DishUpdateRequest;
import io.zensoft.food.model.Cafe;
import io.zensoft.food.model.Dish;
import io.zensoft.food.repository.CafeRepository;
import io.zensoft.food.repository.DishRepository;
import io.zensoft.food.service.DishService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private DishRepository dishRepository;

    private CafeRepository cafeRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository, CafeRepository cafeRepository) {
        this.dishRepository = dishRepository;
        this.cafeRepository = cafeRepository;
    }

    @Override
    public List<Dish> getAllByCafeId(@NonNull Long cafeId) {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(() -> new EntityNotFoundException("Cafe with id = " + cafeId + " not found."));
        return cafe.getDishes();
    }

    @Override
    public Dish getById(@NonNull Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dish with id = " + id + " not found"));
    }

    @Transactional
    @Override
    public Dish update(@NonNull DishUpdateRequest request) {

        if (!dishRepository.existsById(request.getId())) {
            throw new EntityNotFoundException("Dish with id = " + request.getId() + " not found.");
        }

        Dish dish = new Dish();

        dish.setId(request.getId());
        dish.setName(request.getName());
        dish.setPortion(request.getPortion());
        dish.setPrice(request.getPrice());
        dish.setCafe(request.getCafe());
        return dishRepository.save(dish);
    }

    @Override
    public void delete(@NonNull Long id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dish with id = " + id + " not found."));
        dishRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Dish add(@NonNull DishCreateRequest request) {

        Dish dish = new Dish();
        dish.setName(request.getName());
        dish.setPrice(request.getPrice());
        dish.setPortion(request.getPortion());
        dish.setCafe(request.getCafe());

        return dishRepository.save(dish);
    }
}

package com.sangamone.repo;

import org.springframework.data.repository.CrudRepository;

import com.sangamone.model.Train;

public interface TrainRepo extends CrudRepository<Train, Integer> {

}

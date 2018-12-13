package com.example.javaweb.repository;

import com.example.javaweb.domain.ParentInfo;
import org.springframework.data.repository.CrudRepository;

public interface ParentRegRepository extends CrudRepository<ParentInfo,Long> {
}

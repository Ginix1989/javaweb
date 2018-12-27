package com.example.javaweb.repository;

import com.example.javaweb.domain.ChildrenInfo;
import com.example.javaweb.domain.ParentInfo;
import org.springframework.data.repository.CrudRepository;


public interface ChildrenInfoRepository extends CrudRepository<ChildrenInfo,Long> {
}

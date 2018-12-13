package com.example.javaweb.repository;

import com.example.javaweb.domain.ParentUseServe;
import org.springframework.data.repository.CrudRepository;

/**
 * 保存用户预约服务信息
 */
public interface ParentUseServeRepository extends CrudRepository<ParentUseServe,Long> {
}

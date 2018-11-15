package com.example.javaweb.repository;

import com.example.javaweb.domain.ServeInfo;
import org.springframework.data.repository.CrudRepository;

/***
 *  完成 首页展示信息的各种操作
 */
public interface ServerInfoRepositrory extends CrudRepository<ServeInfo,Long> {
}

package com.gsoft.widget.willdo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gsoft.widget.willdo.entity.WilldoSettingEntity;

/**
 * 待办WilldoSetting Persistence
 *
 */
public interface WilldoSettingPersistence extends JpaRepository<WilldoSettingEntity, Long> {

	@Query("FROM  WilldoSettingEntity c WHERE c.userId=?1 ")
	List<WilldoSettingEntity> findByPid(Long personnelId);

	
}

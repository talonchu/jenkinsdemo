package com.gsoft.widget.willdo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gsoft.widget.willdo.entity.WilldoSrcEntity;

/**
 * 待办WilldoSrc Persistence
 *
 */
public interface WilldoSrcPersistence extends JpaRepository<WilldoSrcEntity, Long> {

	/**
	 * 根据来源code得到对象
	 * @param srcCode
	 * @return
	 */
	@Query("FROM  WilldoSrcEntity c WHERE c.srcCode=?1 ")
	WilldoSrcEntity findByCode(String srcCode);

	
}

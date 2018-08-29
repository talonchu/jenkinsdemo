package com.gsoft.widget.willdo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsoft.widget.willdo.entity.WilldoEntity;

/**
 * 待办 Persistence
 *
 */
public interface WilldoPersistence extends JpaRepository<WilldoEntity, Long> {

	
}

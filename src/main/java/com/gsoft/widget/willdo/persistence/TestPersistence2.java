package com.gsoft.widget.willdo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsoft.widget.willdo.entity.Test;

/**
 * 待办 Persistence
 *
 */
public interface TestPersistence2 extends JpaRepository<Test, Long> {

	
}

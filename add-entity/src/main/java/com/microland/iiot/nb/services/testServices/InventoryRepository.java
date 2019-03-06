package com.microland.iiot.nb.services.testServices;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microland.iiot.nb.services.dto.UserInfo;

public interface InventoryRepository extends JpaRepository<UserInfo, Integer> {
	@Query("from UserInfo where userName LIKE :name")
	public List<UserInfo> findAllDemo(@Param("name") String name);

}

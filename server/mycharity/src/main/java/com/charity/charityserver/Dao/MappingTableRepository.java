package com.charity.charityserver.Dao;

import com.charity.charityserver.Entity.MappingTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MappingTableRepository extends JpaRepository<MappingTable, Integer> {
    MappingTable findMappingTableByFromItemid(String fromItemid);
}

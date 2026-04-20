package com.gamelist.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository <Status, Long>{
    Status findByStatusNameIgnoreCase(String statusName);
    List <Status> findByStatusName (String statusName);
    
}

package com.example.spring_web.backendprojet.service;

import java.util.List;
import java.util.Optional;

public interface GlobalServiceCrud<K>  {
    
    List<K> getAllElement();

    K save(K k) ;

    void delete(K k) ;

    void deleteAll(List<K> k)   ;

    List<K> saveAll(List<K> k)   ;

    Optional<K> findById(Long identifier)   ;


}

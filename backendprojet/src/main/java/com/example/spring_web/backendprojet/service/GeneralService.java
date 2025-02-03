package com.example.spring_web.backendprojet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface GeneralService<K>  {
    
     K save(K k) ;

    void delete(K k) ;

    void deleteAll(List<K> k)   ;

    List<K> saveAll(List<K> k)   ;

  // <I, J> void deleteparameter(HashMap<I, J> params);

    Optional<K> findById(Long identifier)   ;


}

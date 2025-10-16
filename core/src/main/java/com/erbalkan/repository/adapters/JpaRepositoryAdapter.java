package com.erbalkan.repository.adapters;

import java.util.List;
import java.util.Optional;

import com.erbalkan.domain.BaseEntity;
import com.erbalkan.repository.IRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class JpaRepositoryAdapter<T extends BaseEntity> implements IRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<T> _entityClass;

    public JpaRepositoryAdapter(Class<T> entityClass){
        _entityClass = entityClass;
    }

    @Override
    public Optional<T> findById(Long id){
        T entity = entityManager.find(_entityClass, id);
        return (entity != null && !entity.isDeleted()) ? Optional.of(entity) : Optional.empty();
    }

    @Override
    public List<T> findAll(){
        String jpql = "SELECT e FROM " + _entityClass.getSimpleName() + " e WHERE e.isDeleted = false";
        return entityManager.createQuery(jpql,_entityClass).getResultList();
    }

    @Override
    public T save(T entity){
        if(entity.getId() == null){
            entityManager.persist(entity);
            return entity;
        }else{return entityManager.merge(entity);}
    }

    @Override
    public void delete(T entity){
        entity.setDeleted(true);
        entityManager.merge(entity);
    }
}


/*
@PersistenceContext
JPA’nın EntityManager’ını enjekte eder.
Spring olmadan da çalışabilir (örneğin Jakarta EE ortamında).

entityClass
Generic T tipi için sınıf referansı tutulur.
JPQL sorgularında T’nin adını kullanmak için gereklidir.

findById
EntityManager.find() ile veriyi getirir.
Soft delete kontrolü: isDeleted == false değilse Optional.empty() döner.

findAll
JPQL ile isDeleted = false olanları getirir.
entityClass.getSimpleName() ile dinamik sorgu oluşturulur.

save
id == null ise yeni kayıt → persist.
id != null ise güncelleme → merge.

delete
Fiziksel silme yerine isDeleted = true yapılır.
merge ile veritabanına yansıtılır.


Open Closed Prensibi
Yeni adapter’lar (MongoAdapter, RestAdapter) eklenebilir, mevcut kod değişmez
*/
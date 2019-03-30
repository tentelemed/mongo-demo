package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.io.Serializable;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleMongoRepository<T, ID>
        implements BaseRepository<T, ID> {

    private MongoOperations mongoOperations;
    private MongoEntityInformation<T, ID> metadata;

    public BaseRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }

    @Override
    public Page<T> mySearch(Query query, Pageable pageable) {

        long total = mongoOperations.count(query, metadata.getJavaType() );
        return new PageImpl<T>(mongoOperations.find(query.with(pageable), metadata.getJavaType()), pageable, total);
    }
}

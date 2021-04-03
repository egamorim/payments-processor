package br.com.zup.paymentprocessor.data.repository;

import br.com.zup.paymentprocessor.data.entity.TedEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TedRepository extends CrudRepository<String, TedEntity> {

}

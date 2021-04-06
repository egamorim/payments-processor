package br.com.zup.paymentprocessor.application.domain.payment.ted;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TedRepository extends CrudRepository<String, TedEntity> {

}

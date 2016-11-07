package co.synergyspace.accounts.repositories.impl;

import co.synergyspace.accounts.entities.impl.UserEntity;
import co.synergyspace.accounts.repositories.IAccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tarek on 04/11/16.
 */
@Repository
public interface AccountRepository extends IAccountRepository<UserEntity>, JpaRepository<UserEntity, Long> {
}

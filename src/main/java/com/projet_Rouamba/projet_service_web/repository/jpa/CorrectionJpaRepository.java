package com.projet_Rouamba.projet_service_web.repository.jpa;

import com.projet_Rouamba.projet_service_web.domain.CorrectionEntity;
import com.projet_Rouamba.projet_service_web.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CorrectionJpaRepository extends JpaRepository<CorrectionEntity, Long> {

    CorrectionEntity findByUserentity(UserEntity userEntity);


    Optional<CorrectionEntity> findByUserentityAndDate(UserEntity user, LocalDate date);

    List<CorrectionEntity> findAllByDateBetween(LocalDate startDate, LocalDate endDate);


}

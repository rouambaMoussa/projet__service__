package com.projet_Rouamba.projet_service_web.service;

import com.projet_Rouamba.projet_service_web.dto.CorrectionDTO;
import com.projet_Rouamba.projet_service_web.domain.CorrectionEntity;
import com.projet_Rouamba.projet_service_web.domain.UserEntity;
import com.projet_Rouamba.projet_service_web.repository.jpa.CorrectionJpaRepository;
import com.projet_Rouamba.projet_service_web.repository.jpa.UserJpaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CorrectionServiceImpl {


    private  CorrectionJpaRepository correctionJpaRepository;
    private  UserJpaRepository userJpaRepository;

    private UserServiceImpl userService;
    public CorrectionServiceImpl(CorrectionJpaRepository correctionJpaRepository, UserJpaRepository userJpaRepository, UserServiceImpl userService) {
        this.correctionJpaRepository = correctionJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.userService = userService;
    }

    public void addCorrection(CorrectionDTO correctionDTO) {

        UserEntity user = userJpaRepository.findByUsername(correctionDTO.getUserDTO().getUsername());

        if (user == null) {
            throw new IllegalArgumentException("Utilisateur non trouvé avec le nom d'utilisateur : " + correctionDTO.getUserDTO().getUsername());
        } else {

            LocalDate today = LocalDate.now();
            Optional<CorrectionEntity> existingCorrection = correctionJpaRepository.findByUserentityAndDate(user, today);

            if (existingCorrection.isPresent()) {

                throw new IllegalArgumentException("L'utilisateur a déjà effectué une saisie pour aujourd'hui.");
            } else {

                CorrectionEntity correctionEntity = new CorrectionEntity();
                correctionEntity.setUserentity(user);
                correctionEntity.setDate(today);
                correctionEntity.setValue(correctionDTO.getValue());
                correctionJpaRepository.save(correctionEntity);
            }
        }
    }


    private CorrectionDTO convertToDTO(CorrectionEntity correctionEntity) {
        CorrectionDTO correctionDTO = new CorrectionDTO();
        correctionDTO.setId(correctionEntity.getId());
        correctionDTO.setValue(correctionEntity.getValue());
        correctionDTO.setUserDTO(userService.mapEntityToDto(correctionEntity.getUserentity()));

        return correctionDTO;
    }

    private CorrectionEntity convertToEntity(CorrectionDTO correctionDTO){
        CorrectionEntity correctionEntity= new CorrectionEntity();
        correctionEntity.setId(correctionDTO.getId());
        correctionEntity.setValue(correctionDTO.getValue());
        correctionEntity.setUserentity(userService.mapDtoToEntity(correctionDTO.getUserDTO()));
        return correctionEntity;
    }

    public List<CorrectionDTO> listCorrection() {
        List<CorrectionDTO> correctionDTO1 = new ArrayList<>();

        for (CorrectionEntity correctionEntity1 : this.correctionJpaRepository.findAll())
        {
            correctionDTO1.add(convertToDTO(correctionEntity1));
        }


        return correctionDTO1 ;

    }

    public void modifierCorrection(Long id, CorrectionDTO correctionDTO) {
    Optional <CorrectionEntity> correctionEntity=this.correctionJpaRepository.findById(id);

    if (correctionEntity.isPresent())
    {
        CorrectionEntity correctionEntity1 = correctionEntity.get();
        correctionEntity1.setDate(LocalDate.now());
        correctionEntity1.setId(correctionEntity.get().getId());
        correctionEntity1.setValue(correctionDTO.getValue());
        correctionEntity1.setUserentity(userService.mapDtoToEntity(correctionDTO.getUserDTO()));


       this.correctionJpaRepository.save(correctionEntity1);
    }

    }

    public void delCorrection(Long id) {

        Optional<CorrectionEntity> optionalCorrection = correctionJpaRepository.findById(id);
        if (optionalCorrection.isPresent()) {

            correctionJpaRepository.delete(optionalCorrection.get());
        } else {

            throw new IllegalArgumentException("Correction non trouvée avec l'ID : " + id);
        }
    }

    public CorrectionDTO getCorrectionById(Long id) {
        Optional<CorrectionEntity> correctionEntityOptional = correctionJpaRepository.findById(id);
        if (correctionEntityOptional.isPresent()) {
            return convertToDTO(correctionEntityOptional.get());
        } else {
            throw new IllegalArgumentException("Correction non trouvée avec l'ID : " + id);
        }
    }

    public List<CorrectionDTO> getCorrectionsInPeriod(LocalDate startDate, LocalDate endDate) {
        List<CorrectionEntity> corrections = correctionJpaRepository.findAllByDateBetween(startDate, endDate);
        return corrections.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}



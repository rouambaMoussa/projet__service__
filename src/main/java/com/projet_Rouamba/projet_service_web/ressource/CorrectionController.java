package com.projet_Rouamba.projet_service_web.ressource;

import com.projet_Rouamba.projet_service_web.dto.CorrectionDTO;
import com.projet_Rouamba.projet_service_web.service.CorrectionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CorrectionController {


    private CorrectionServiceImpl correctionServiceImpl;

    @PostMapping("/corrections")
    public void addCorrection(@RequestBody CorrectionDTO correctionDTO) {
       this.correctionServiceImpl.addCorrection(correctionDTO);
    }

    @GetMapping("/afficheList")
    public List <CorrectionDTO> listCorrection() {

        return this.correctionServiceImpl.listCorrection();
    }

    @PutMapping("/modifier/{id}")
    public void modifierCorrection(@PathVariable Long id, @RequestBody CorrectionDTO correctionDTO) {
        this.correctionServiceImpl.modifierCorrection(id, correctionDTO);
    }

    @DeleteMapping("/Supcorrection/{id}")
    public void delCorrection(@PathVariable Long id) {
        this.correctionServiceImpl.delCorrection(id);
    }


    @GetMapping("/corrections/{id}")
    public ResponseEntity<CorrectionDTO> getCorrectionById(@PathVariable Long id) {
        CorrectionDTO correctionDTO = correctionServiceImpl.getCorrectionById(id);
        return ResponseEntity.ok(correctionDTO);
    }

    @GetMapping("/corrections")
    public ResponseEntity<List<CorrectionDTO>> getCorrectionsInPeriod(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<CorrectionDTO> corrections = correctionServiceImpl.getCorrectionsInPeriod(startDate, endDate);
        return ResponseEntity.ok(corrections);
    }




}



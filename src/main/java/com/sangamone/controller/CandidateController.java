package com.sangamone.controller;

import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.sangamone.repo.CandidateRepo;
import com.sangamone.model.Candidate;

@RestController
public class CandidateController {
	@Autowired
	CandidateRepo repo;
	
	@PostMapping("/uploadCandidate")
    public String uploadCSV(@RequestPart("file") MultipartFile file) throws IOException, CsvValidationException {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] nextRecord;
            csvReader.readNext(); // Skip header row

            while ((nextRecord = csvReader.readNext()) != null) {
            	 String congressCandidateName = nextRecord[0];
                 String BJPCandidateName = nextRecord[1];
                 String JDSCandidateName = nextRecord[2];
                 String othersCandidateName = nextRecord[3];
                 String seatNo = nextRecord[4];
                 
                   Candidate candidate = new Candidate();
                   candidate.setCongressCandidateName(congressCandidateName);
                   candidate.setBJPCandidateName(BJPCandidateName);
                   candidate.setJDSCandidateName(JDSCandidateName);
                   candidate.setOthersCandidateName(othersCandidateName);
                   candidate.setSeatNo(seatNo);

                    repo.save(candidate);
               }
           }

           return "Candidates uploaded successfully!";
       }



}

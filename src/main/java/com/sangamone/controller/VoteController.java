package com.sangamone.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.sangamone.model.Vote;
import com.sangamone.repo.VoteRepo;

@RestController
public class VoteController {

	@Autowired
	VoteRepo repo;
	
	
	@PostMapping("/uploadVote")
    public String uploadCSV(@RequestPart("file") MultipartFile file) throws IOException, CsvValidationException {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] nextRecord;
            csvReader.readNext(); // Skip header row

            while ((nextRecord = csvReader.readNext()) != null) {
                String seatNo = nextRecord[0];
                int congress = Integer.parseInt(nextRecord[1]);
                int bjp = Integer.parseInt(nextRecord[2]);
                int jds = Integer.parseInt(nextRecord[3]);
                int others = Integer.parseInt(nextRecord[4]);

                Vote vote = new Vote();
                vote.setSeatNo(seatNo);
                vote.setCongress(congress);
                vote.setBjp(bjp);
                vote.setJds(jds);
                vote.setOthers(others);

                repo.save(vote);
            }
        }

        return "File uploaded successfully!";
    }




}

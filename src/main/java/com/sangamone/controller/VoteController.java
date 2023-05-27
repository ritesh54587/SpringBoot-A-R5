package com.sangamone.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

@GetMapping("/getCandidateVotesBySeatId")

public List<Vote> getCandidateVotesBySeatId(@RequestParam int seat_id){
	
	List<Vote> list = repo.getCandidateVotesBySeatId(seat_id);
	return list;
	}

@GetMapping("/getWinnerNameBySeatNo")
public Map<String, Object> getWinnerNameBySeatNo(@RequestParam String seatNo) {
    String winnerName = repo.getWinnerNameBySeatNo(seatNo);
    Map<String,Object> response= new HashMap<>();
    response.put("WinnerPartyName", winnerName);
    return response;
}

@GetMapping("/findWinner")
public String findWinner(@RequestParam int seat_id) {
    Vote vote = repo.findPartyVotes(seat_id);
    
    if (vote != null) {
        int congressVotes = vote.getCongress();
        int bjpVotes = vote.getBjp();
        int jdsVotes = vote.getJds();
        int othersVotes = vote.getOthers();
        String winner;
        int winnerVotes;
        
        if (congressVotes > bjpVotes && congressVotes > jdsVotes && congressVotes > othersVotes) {
            winner = "Congress";
            winnerVotes = congressVotes;
        } else if (bjpVotes > congressVotes && bjpVotes > jdsVotes && bjpVotes > othersVotes) {
            winner = "BJP";
            winnerVotes = bjpVotes;
        } else if (jdsVotes > congressVotes && jdsVotes > bjpVotes && jdsVotes > othersVotes) {
            winner = "JDS";
            winnerVotes = jdsVotes;
        } else {
            winner = "Others";
            winnerVotes = othersVotes;
        }
        
        vote.setWinner(winner);
        vote.setWinnerVotes(winnerVotes);
        repo.save(vote);
        
        FileWriter writer = null;
        try {
            writer = new FileWriter("ritesh.csv", true);
            writer.write(winner + "," + winnerVotes + "\n");
            writer.close();
            
            return "Winner updated: " + winner + " - " + winnerVotes;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to update winner.";
        } 
                
                
    } else {
        return "No data found for the seat ID.";
    }
}

@GetMapping("/findAllWinners")
public String findAllWinner() throws IOException {
	
    

for (int i = 0; i < 25; i++) {
    findWinner(i);
}
    
    return "Winners updated for all seat IDs.";
	
}
@GetMapping("/downloadVotes")
public String downloadVotes(HttpServletResponse response) throws IOException {
    Iterable<Vote> votes = repo.findAll();

    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=\"votes.csv\"");

    PrintWriter writer = response.getWriter();
    writer.println("SeatNo,Congress,BJP,JDS,Others,Winner,WinnerVotes");
    for (Vote vote : votes) {
        writer.println(vote.getSeatNo() + "," + vote.getCongress() + "," + vote.getBjp() + ","
                + vote.getJds() + "," + vote.getOthers() + "," + vote.getWinner() + "," + vote.getWinnerVotes());
    }
    writer.flush();
    writer.close();

    return "File downloaded successfully";
}



}




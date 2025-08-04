package com.build.jobapp.restapi.controller;

import com.build.jobapp.restapi.model.Job;
import com.build.jobapp.restapi.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;


    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from REST API!";
    }

    @GetMapping
    public List<Job> getAllJobs(){
        return jobService.getAllJobs();
    }

    @PostMapping
    public Job createJob(@RequestBody Job job){
        return jobService.createJob(job);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable int id)
    {
        Job job = jobService.getJob(id);
        if (job == null) {
            return ResponseEntity.notFound().build();  // Return 404 if job not found
        }
        return ResponseEntity.ok(job);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable int id) {
        Job job = jobService.getJob(id);
        if (job == null) {
            return ResponseEntity.notFound().build();
        }
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> UpdateJob(@PathVariable int id,@RequestBody Job updateJob){

       try{
           Job update = jobService.updateJob(id,updateJob);
           return ResponseEntity.ok(update);
       }catch (IllegalArgumentException e){
           return ResponseEntity.notFound().build();
        }

    }
}

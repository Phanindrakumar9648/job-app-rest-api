package com.build.jobapp.restapi.service;

import com.build.jobapp.restapi.model.Job;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobService {

    private final Map<Integer, Job> jobstore = new HashMap<>();
    private final Queue<Integer> recycledIds = new LinkedList<>(); // For deleted IDs
    private int idCounter = 1;

    public List<Job> getAllJobs() {
        return new ArrayList<>(jobstore.values());
    }

    public Job getJob(int id) {
        return jobstore.get(id);
    }

    public Job createJob(Job job) {
        int newId;

        // CASE 1: Manual ID supplied by client (e.g., to restore deleted one)
        if (job.getId() != null) {
            if (jobstore.containsKey(job.getId())) {
                throw new IllegalArgumentException("Job with ID " + job.getId() + " already exists.");
            }
            newId = job.getId();
            recycledIds.remove(newId); // just in case it was deleted before
        }
        // CASE 2: Auto ID assignment
        else {
            if (!recycledIds.isEmpty()) {
                newId = recycledIds.poll();
            } else {
                newId = idCounter++;
            }
        }

        job.setId(newId);
        jobstore.put(newId, job);
        return job;
    }


    public void deleteJob(int id) {
        jobstore.remove(id);
    }

    public Job updateJob(int id,Job updatedJob){
        if (!jobstore.containsKey(id)) {
            throw new IllegalArgumentException("Job with ID " + id + " not found.");
        }
        updatedJob.setId(id);
        jobstore.put(id,updatedJob);
        return updatedJob;
    }

}

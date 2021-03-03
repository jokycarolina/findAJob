package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Profile;

public interface ProfilesRepository extends JpaRepository<Profile, Integer> {

}

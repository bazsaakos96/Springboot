package hu.uni.miskolc.lev.java.CourseBoot.service;

import hu.uni.miskolc.lev.java.CourseBoot.model.entity.Profile;

import java.util.List;

public interface ProfileService {
        List<Profile> getAllProfile();
        String getProfileByid(int profile_id);
        void updateProfile(int profile_id, int age, String name);
}
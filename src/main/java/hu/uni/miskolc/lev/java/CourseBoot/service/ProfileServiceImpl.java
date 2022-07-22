package hu.uni.miskolc.lev.java.CourseBoot.service;
import hu.uni.miskolc.lev.java.CourseBoot.model.repo.ProfileRepository;
import hu.uni.miskolc.lev.java.CourseBoot.model.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{
    private final ProfileRepository profileRepository;
    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Profile> getAllProfile() {
        return (List<Profile>) profileRepository.findAll();
    }

    public void updateProfile(int profile_id, int age, String name){
        Optional<Profile> profile = profileRepository.findById(profile_id);
        if (profile.isPresent()) {
            Profile p =profile.get();
            p.setName(name);
            p.setAge(age);
            profileRepository.save(p);
        }
    }
    public String getProfileByid(int profile_id){
        Optional<Profile> profile = profileRepository.findById(profile_id);
        String result = null;
        if (profile.isPresent()) {
            result="<br>Profil adatok:<table align='center'>" +
                    "<tr><th>Név:</th><td><input id='name"+profile_id+"' value='"+profile.get().getName()+"'/></td><tr>" +
                    "<tr><th>Kor:</th><td><input id='age"+profile_id+"' type='number' value='"+profile.get().getAge()+"'/></td></tr>" +
                    "<tr><td colspan='2'><button onclick='updateProfile("+profile_id+")'>Módosít</button>" +
                    "<button onclick='loadPages()'>X</button></td></tr>";
        }
        return result;
    }
}
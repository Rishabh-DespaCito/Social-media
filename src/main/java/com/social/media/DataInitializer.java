package com.social.media;

import com.social.media.models.Post;
import com.social.media.models.SocialGroup;
import com.social.media.models.SocialProfile;
import com.social.media.models.SocialUser;
import com.social.media.repositories.PostRepository;
import com.social.media.repositories.SocialGroupRepository;
import com.social.media.repositories.SocialProfileRepository;
import com.social.media.repositories.SocialUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    private final SocialUserRepository userRepository;
    private final SocialProfileRepository socialProfileRepository;
    private final SocialGroupRepository groupRepository;
    private final PostRepository postRepository;

    public DataInitializer(SocialUserRepository userRepository,SocialProfileRepository socialProfileRepository,SocialGroupRepository groupRepository,PostRepository postRepository){
        this.userRepository = userRepository;
        this.socialProfileRepository = socialProfileRepository;
        this.groupRepository = groupRepository;
        this.postRepository = postRepository;
    }

    @Bean
    public CommandLineRunner initializeData(){
        return args -> {
            //create some users.
            SocialUser user1 = new SocialUser();
            SocialUser user2 = new SocialUser();
            SocialUser user3 = new SocialUser();

            //save users to the Database.
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            //create some groups.

            SocialGroup group1 = new SocialGroup();
            SocialGroup group2 = new SocialGroup();


            //add users to the group.

            group1.getSocialUsers().add(user1);
            group1.getSocialUsers().add(user2);

            group2.getSocialUsers().add(user2);
            group2.getSocialUsers().add(user3);

            //save groups.
            groupRepository.save(group1);
            groupRepository.save(group2);

            //associate users with groups since its a bidirectional relationship.

            user1.getGroups().add(group1);
            user2.getGroups().add(group1);
            user2.getGroups().add(group2);
            user3.getGroups().add(group2);

            //save users again to update them in userRepository.
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);




            //create posts.

            Post post1 = new Post();
            Post post2 = new Post();
            Post post3 = new Post();

            //associate posts with users.

            post1.setSocialUser(user1);
            post2.setSocialUser(user2);
            post3.setSocialUser(user3);

            //save posts to post Repository

            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);

            //create socialProfiles

            SocialProfile profile1 = new SocialProfile();
            SocialProfile profile2 = new SocialProfile();
            SocialProfile profile3 = new SocialProfile();

            //associate profiles with users.

            profile1.setUser(user1);
            profile2.setUser(user2);
            profile3.setUser(user3);

            //save to profile respository, to the database.

            socialProfileRepository.save(profile1);
            socialProfileRepository.save(profile2);
            socialProfileRepository.save(profile3);


        };
    }



}

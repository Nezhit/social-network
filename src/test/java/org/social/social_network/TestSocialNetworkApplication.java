package org.social.social_network;

import org.springframework.boot.SpringApplication;

public class TestSocialNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.from(SocialNetworkApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
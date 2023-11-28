package com.feed.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserAccount {
    private String username;
    private Set<String> friends = new HashSet<>();

    public void addFriend(String friendName){
        friends.add(friendName);

    }
}

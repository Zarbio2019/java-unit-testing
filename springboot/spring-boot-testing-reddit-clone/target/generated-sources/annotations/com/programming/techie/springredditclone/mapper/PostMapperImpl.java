package com.programming.techie.springredditclone.mapper;

import com.programming.techie.springredditclone.dto.PostRequest;
import com.programming.techie.springredditclone.dto.PostResponse;
import com.programming.techie.springredditclone.model.Post;
import com.programming.techie.springredditclone.model.Post.PostBuilder;
import com.programming.techie.springredditclone.model.Subreddit;
import com.programming.techie.springredditclone.model.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-16T21:49:24-0500",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
@Component
public class PostMapperImpl extends PostMapper {

    @Override
    public Post map(PostRequest postRequest, Subreddit subreddit, User user) {
        if ( postRequest == null && subreddit == null && user == null ) {
            return null;
        }

        PostBuilder post = Post.builder();

        if ( postRequest != null ) {
            post.description( postRequest.getDescription() );
            post.postId( postRequest.getPostId() );
            post.postName( postRequest.getPostName() );
            post.url( postRequest.getUrl() );
        }
        if ( subreddit != null ) {
            post.subreddit( subreddit );
        }
        if ( user != null ) {
            post.user( user );
        }
        post.createdDate( java.time.Instant.now() );
        post.voteCount( 0 );

        return post.build();
    }

    @Override
    public PostResponse mapToDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponse postResponse = new PostResponse();

        postResponse.setId( post.getPostId() );
        postResponse.setUserName( postUserUsername( post ) );
        postResponse.setSubredditName( postSubredditName( post ) );
        postResponse.setPostName( post.getPostName() );
        postResponse.setUrl( post.getUrl() );
        postResponse.setDescription( post.getDescription() );
        postResponse.setVoteCount( post.getVoteCount() );

        postResponse.setDuration( getDuration(post) );
        postResponse.setDownVote( isPostDownVoted(post) );
        postResponse.setCommentCount( commentCount(post) );
        postResponse.setUpVote( isPostUpVoted(post) );

        return postResponse;
    }

    private String postUserUsername(Post post) {
        if ( post == null ) {
            return null;
        }
        User user = post.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    private String postSubredditName(Post post) {
        if ( post == null ) {
            return null;
        }
        Subreddit subreddit = post.getSubreddit();
        if ( subreddit == null ) {
            return null;
        }
        String name = subreddit.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

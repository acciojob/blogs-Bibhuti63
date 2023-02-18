package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content)  {
        //create a blog at the current time
        User user;
//        if(!userRepository1.findById(userId).isPresent()){
//            throw new Exception();
//        }
        user=userRepository1.findById(userId).get();
        Blog blog=new Blog();

        blog.setUser(user);
        blog.setTitle(title);
        blog.setContent(content);

        //save the user and blog
        userRepository1.save(user);
        //blog will saved by cascading.
        return blog;

    }

    public void deleteBlog(int blogId)  {
        //delete blog and corresponding images
//        if(!blogRepository1.findById(blogId).isPresent()){
//            throw new Exception();
//        }
        blogRepository1.deleteById(blogId);
    }
}

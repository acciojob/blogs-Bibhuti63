package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions) throws Exception {
        //add an image to the blog
        if(!blogRepository2.findById(blogId).isPresent()){
            throw new Exception();
        }

        Blog blog=blogRepository2.findById(blogId).get();

        Image image=new Image();
        image.setBlog(blog);
        image.setDimension(dimensions);
        image.setDescription(description);

        blog.getImageList().add(image);

        blogRepository2.save(blog);
        //image will saved by cascading.

        return image;

    }

    public void deleteImage(Integer id) throws Exception {
        if(!imageRepository2.findById(id).isPresent()){
            throw new Exception();
        }
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) throws Exception {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        if(!imageRepository2.findById(id).isPresent()){
            throw new Exception();
        }

        String arr[]=screenDimensions.split("X");
        int sL=Integer.parseInt(arr[0]);
        int sB=Integer.parseInt(arr[1]);

        Image image=imageRepository2.findById(id).get();
        String imageDimension=image.getDimension();
        String arr2[]=imageDimension.split("X");
        int iL=Integer.parseInt(arr2[0]);
        int iB=Integer.parseInt(arr2[1]);

        return ((sL/iL)*(sB/iB));

    }
}

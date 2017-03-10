package com.example.imagine.javadevelopers;

/**
 * Created by Imagine on 09/03/2017.
 */
public class User {
    private String login;
    private String avatar_url;
    private String id;
    private String html_url;

    public User(String avatar_url,String login, String id, String html_url)    {
        this.avatar_url=avatar_url;
        this.login=login;
        this.id=id;
        this.html_url=html_url;
    }

    public String getImage()    {
        return avatar_url;
    }

    public String getHtml_url()    {
        return html_url;
    }

    public String getImg_url()    {
        return avatar_url;
    }
    public void setUrl(String html_url)    {
        this.html_url=html_url;
    }

    public void setImage(String avatar_url)    {
        this.avatar_url=avatar_url;
    }

    public String getName()    {
        return login;
    }

    public void setName(String login)    {
        this.login=login;
    }

    public String getPrice()    {
        return id;
    }

    public void setPrice(String id)    {
        this.id=id;
    }


}

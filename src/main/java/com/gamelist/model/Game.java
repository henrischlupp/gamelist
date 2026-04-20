package com.gamelist.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity

public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 120)
    @Column(nullable = false)
    private String title;

    @Size(max = 120)
    private String developer;

    @Size(max = 500)
    private String notes;

    private LocalDate releaseDate;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false)
    private String platform;

    @ManyToOne
    @JoinColumn(name = "statusid")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @JsonIgnoreProperties ("game")

    public Game () {}


    public Game(@NotBlank @Size(max = 120) String title, @Size(max = 120) String developer,
            @Size(max = 500) String notes, LocalDate releaseDate, @NotNull Status status,
            @NotBlank @Size(max = 80) String platform) {

        super();
        this.title = title;
        this.developer = developer;
        this.notes = notes;
        this.releaseDate = releaseDate;
        this.status = status;
        this.platform = platform;
    }


  

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDeveloper() {
        return developer;
    }


    public void setDeveloper(String developer) {
        this.developer = developer;
    }


    public String getNotes() {
        return notes;
    }


    public void setNotes(String notes) {
        this.notes = notes;
    }


    public LocalDate getReleaseDate() {
        return releaseDate;
    }


    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }


    public String getPlatform() {
        return platform;
    }


    public void setPlatform(String platform) {
        this.platform = platform;
    }


    public Status getStatus() {
        return status;
    }


    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "";
    }
    
}


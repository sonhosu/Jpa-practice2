package com.jpa.crud.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpa.crud.dto.BoardDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Board {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    @Column
    private String title;
    @Column
    private String contents;

    @Column
    private String username;
    @Column
    private LocalDateTime dateTime;

    private LocalDateTime updateTime;

    @JsonIgnore
    @OneToMany(mappedBy = "board")
    private List<Comment> comment = new ArrayList<>();

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Board(BoardDto boardDto) {
        this.id = boardDto.getId();
        this.title = boardDto.getTitle();
        this.username = boardDto.getUsername();
        this.contents = boardDto.getContents();
        this.dateTime = LocalDateTime.now();
        this.user = boardDto.getUser();

    }

}

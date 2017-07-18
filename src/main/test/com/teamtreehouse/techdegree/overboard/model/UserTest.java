package com.teamtreehouse.techdegree.overboard.model;

import com.teamtreehouse.techdegree.overboard.exc.AnswerAcceptanceException;
import com.teamtreehouse.techdegree.overboard.exc.VotingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Chris on 7/17/17.
 */
public class UserTest {
    private User user;
    private User newUser;
    private Board board;
    private Question question;


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        board = new Board("Board");
        user = new User(board, "User");
        newUser = new User(board, "NewUser");
        question = user.askQuestion("Question?");


    }

    @Test
    public void reputationGoesUpIfQuestionIsUpVoted() throws Exception {
        newUser.upVote(question);

        assertEquals(5, user.getReputation());

    }

}
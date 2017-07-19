package com.teamtreehouse.techdegree.overboard.model;

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
    private Board board;
    private User user;
    private User newUser;
    private Question question;
    private Answer answer;


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        board = new Board("Board");
        user = new User(board, "User");
        newUser = new User(board, "NewUser");
        question = user.askQuestion("Question?");
        answer = newUser.answerQuestion(question, "answer");


    }

    @Test
    public void reputationGoesUpWhenQuestionIsUpVoted() throws Exception {
        newUser.upVote(question);

        assertEquals(5, user.getReputation());

    }

    @Test
    public void answerUpVoteGoesUpByTenPoints() throws Exception {
        user.upVote(answer);

        assertEquals(10, newUser.getReputation());
    }

    @Test
    public void acceptedAnswerAddsToReputation() throws Exception {
        user.acceptAnswer(answer);

        assertEquals(15, newUser.getReputation());
    }

    @Test
    public void userCannotUpVoteSelf() throws Exception {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        user.upVote(question);
    }

    @Test
    public void userCannotDownVoteSelf() throws Exception {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        user.downVote(question);
    }


    @Test
    public void userCannotUpVoteTheirAnswer() throws Exception {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        newUser.upVote(answer);
    }

    @Test
    public void userCannotDownVoteTheirAnswer() throws Exception {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        newUser.downVote(answer);
    }

    @Test
    public void originalUserAcceptsAnswer() throws Exception {
        user.acceptAnswer(answer);

        assertTrue(answer.isAccepted());
    }
}

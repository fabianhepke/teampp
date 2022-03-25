package com.teampp.usecase;

import com.teampp.domain.builder.ConcreteTeamBuilder;
import com.teampp.domain.entities.UserTeamConnection;
import com.teampp.domain.repositories.TeamRepository;
import com.teampp.domain.repositories.UserRepository;
import com.teampp.domain.repositories.UserTeamConnectionRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LeaveTeamTest {

    private final UserTeamConnectionRepository userTeamConnectionRepository = mock(UserTeamConnectionRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final TeamRepository teamRepository = mock(TeamRepository.class);
    private int userID, teamID, currentTeamID;
    private LeaveTeam leaveTeam;

    @Before
    public void setUp() {
        userID = 30;
        teamID = 100002;
        currentTeamID = 100003;
        leaveTeam = new LeaveTeam(userTeamConnectionRepository, teamRepository, userRepository);
    }

    @Test
    public void leaveTeamTest() {
        when(userRepository.getCurrentTeam(userID)).thenReturn(new ConcreteTeamBuilder().setTeamID(currentTeamID).build());
        when(teamRepository.getTeamMemberNum(currentTeamID)).thenReturn(3);
        when(teamRepository.getTeamName(currentTeamID)).thenReturn("FC Bayern München");

        leaveTeam.leaveTeam(userID, teamID);

        verify(userRepository).getCurrentTeam(userID);
        verify(teamRepository).getTeamMemberNum(currentTeamID);
        verify(teamRepository).getTeamName(currentTeamID);
        verify(userTeamConnectionRepository).removeUserTeamConnection(teamID, userID);

    }

}

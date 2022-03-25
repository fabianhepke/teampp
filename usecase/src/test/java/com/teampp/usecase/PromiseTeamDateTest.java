package com.teampp.usecase;

import com.teampp.domain.repositories.DatePromiseRepository;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PromiseTeamDateTest {

    private final DatePromiseRepository datePromiseRepository = mock(DatePromiseRepository.class);
    private int dateID, userID;
    private boolean promised;
    private PromiseTeamDate promiseTeamDate;

    @Before
    public void setUp() {
        promiseTeamDate = new PromiseTeamDate(datePromiseRepository);
        dateID = 12;
        userID = 30;
        promised = true;
    }

    @Test
    public void changeDatePromiseTest() {
        when(datePromiseRepository.doesUserPromisedDate(dateID, userID)).thenReturn(true);
        promiseTeamDate.promiseTeamDate(dateID, userID, promised);
        verify(datePromiseRepository).doesUserPromisedDate(dateID, userID);
        verify(datePromiseRepository).changeDatePromise(dateID, userID, promised);
        verify(datePromiseRepository, never()).addDatePromise(dateID, userID, promised);
    }

    @Test
    public void addDatePromiseTest() {
        when(datePromiseRepository.doesUserPromisedDate(dateID, userID)).thenReturn(false);
        promiseTeamDate.promiseTeamDate(dateID, userID, promised);
        verify(datePromiseRepository).doesUserPromisedDate(dateID, userID);
        verify(datePromiseRepository).addDatePromise(dateID, userID, promised);
        verify(datePromiseRepository, never()).changeDatePromise(dateID, userID, promised);
    }

}

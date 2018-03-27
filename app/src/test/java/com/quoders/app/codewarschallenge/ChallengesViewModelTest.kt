package com.quoders.app.codewarschallenge

import android.arch.lifecycle.MutableLiveData
import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.ChallengesCompleted
import com.quoders.app.codewarschallenge.data.repository.UserRepository
import com.quoders.app.codewarschallenge.ui.challenges.ChallengesViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ChallengesViewModelTest {

    @Mock
    private lateinit var mockLiveData: MutableLiveData<ChallengesCompleted>

    private lateinit var challengesCompleted: ChallengesCompleted

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        challengesCompleted = ChallengesCompleted()
        challengesCompleted.totalPages = 5
    }

    @Test
    fun when_viewModelIsInitialised_then_requestChallenges() {
        //  given
        val mockRepository = mock(UserRepository::class.java)

        //  when
        val viewModel = ChallengesViewModel()
        val user = "userName"
        viewModel.init(user, mockRepository)

        //  then
        verify(mockRepository).getChallengesCompleted(user, 0)
        verify(mockRepository).getChallengesAuthored(user)
    }

    /*@Test
    fun given_viewModelIsInitialised_and_requestedChallenges_when_requestNextChallenges_subscribeAndAddPage() {
        //  given
        val mockRepository = mock(UserRepository::class.java)

        //  when
        val viewModel = ChallengesViewModel()
        val user = "userName"
        `when`(mockRepository.getChallengesCompleted(user, 0)).thenReturn(mockLiveData)
        `when`(mockRepository.getChallengesCompletedObservable(user, 1)).thenReturn(Observable.empty())
        `when`(mockLiveData.value).thenReturn(challengesCompleted)

        viewModel.init(user, mockRepository)
        viewModel.getNextChallengeCompletedPage()

        //  then
        verify(mockRepository).getChallengesCompleted(user, 0)
        verify(mockRepository).getChallengesAuthored(user)
        verify(mockRepository).getChallengesCompletedObservable(user, 1)
    }*/
}

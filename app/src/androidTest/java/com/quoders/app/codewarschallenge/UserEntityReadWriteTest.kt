package com.quoders.app.codewarschallenge

import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.quoders.app.codewarschallenge.data.local.database.CodewarsDatabase
import com.quoders.app.codewarschallenge.data.local.database.UserDao
import com.quoders.app.codewarschallenge.data.local.entities.UserEntity
import junit.framework.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserEntityReadWriteTest {

    lateinit var codewarsDatabase: CodewarsDatabase
    lateinit var userDao: UserDao

    @Before
    fun setup() {
        val context: Context = InstrumentationRegistry.getTargetContext()
        codewarsDatabase = Room.inMemoryDatabaseBuilder(context, CodewarsDatabase::class.java).build()
        userDao = codewarsDatabase.userDao()
    }

    @After
    fun closeDb() = codewarsDatabase.close()

    @Test
    fun given_TwoValidUsersEntities_when_AddedToDatabase_then_MustBeStoredAndRetrievedCorrectly() {
        //  given
        var user1 = UserEntity()
        user1.clan = "Gensokyo"
        user1.honor = 86057
        user1.name = "Voile"
        user1.rank = 3

        var user2 = UserEntity()
        user2.clan = "PropertyExpert"
        user2.honor = 56832
        user2.name = "smile67"
        user2.rank = 5

        //  when
        userDao.insertAll(user1, user2)
        val allUsers = userDao.getAll()

        //  then
        assertTrue(allUsers.size == 2)

        assertEquals(allUsers[0].name,"Voile")
        assertEquals(allUsers[0].clan,"Gensokyo")
        assertEquals(allUsers[0].honor, 86057)
        assertEquals(allUsers[0].rank, 3)

        assertEquals(allUsers[1].name,"smile67")
        assertEquals(allUsers[1].clan,"PropertyExpert")
        assertEquals(allUsers[1].honor, 56832)
        assertEquals(allUsers[1].rank, 5)
    }

    @Test
    fun given_ValidUserInDb_when_getByName_then_TheEntityIsRetrievedCorrectly() {
        //  given
        var user1 = UserEntity()
        user1.clan = "Gensokyo"
        user1.honor = 86057
        user1.name = "Voile"
        user1.rank = 3

        var user2 = UserEntity()
        user2.clan = "PropertyExpert"
        user2.honor = 56832
        user2.name = "smile67"
        user2.rank = 5

        //  when
        userDao.insertAll(user1, user2)
        val user = userDao.getByName("smile67")

        //  then
        assertNotNull(user)
        assertEquals(user.name, "smile67")
        assertEquals(user.clan, "PropertyExpert")
        assertEquals(user.honor, 56832)
        assertEquals(user.rank, 5)
    }
}
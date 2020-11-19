package com.app.demologintask.model

import com.app.demologintask.unittest.LoginValidator
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class LoginValidatorTest {

    val objectUnderTest = LoginValidator()

    @Test
    fun `empty username is invalid`() {
        //when
        val result = objectUnderTest.emptyUsername("")
        //then
        assertThat(result).isFalse
    }

    @Test
    fun `not empty username is valid`() {
        //when
        val result = objectUnderTest.validateUsername("rlogical")
        //then
        assertThat(result).isTrue
    }

    @Test
    fun `empty password is invalid`() {
        //when
        val result = objectUnderTest.emptyPassword("")
        //then
        assertThat(result).isFalse()
    }


    @Test
    fun `not empty password is valid`() {
        //when
        val result = objectUnderTest.validatePassword("123456")
        //then
        assertThat(result).isTrue
    }

    @Test
    fun `username is valid if equal to limit`() {
        //when
        val result = objectUnderTest.validatePassword("rlogical")
        //then
        assertThat(result).isTrue
    }

    @Test
    fun `password is valid if equal to limit`() {
        //when
        val result = objectUnderTest.validatePassword("12345671234567123456712345671234567123456712345671234567")
        //then
        assertThat(result).isTrue
    }


}
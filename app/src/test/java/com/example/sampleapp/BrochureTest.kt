package com.example.sampleapp

import com.example.sampleapp.data.remote.dto.ContentType
import com.example.sampleapp.data.remote.dto.SuperContent
import com.example.sampleapp.domain.model.BrochureConverter
import org.junit.Assert.assertTrue
import org.junit.Test

class BrochureTest {
    @Test
    fun isBrochure() {
        val data = SuperContent.Brochure("", "", "", "brochure", null)
        assertTrue(data.contentType == ContentType.brochure.name)
    }

    @Test
    fun hasConverter() {
        val data : Any = SuperContent.Brochure("", "", "", "brochure", null)
        assertTrue(data is BrochureConverter)
        val data2 : Any = SuperContent.BrochurePremium("", "", "", "brochure", null)
        assertTrue(data2 is BrochureConverter)
    }

    @Test
    fun testConverter(){
        val data = SuperContent.BrochurePremium("", "", "", "brochure", null)
        assertTrue(data.convert().isPremium)
    }

    //And other test :)
}
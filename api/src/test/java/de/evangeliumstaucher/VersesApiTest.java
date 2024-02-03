/*
 * API.Bible
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.6.3
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package de.evangeliumstaucher;

import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.InlineResponse20010;
import de.evangeliumstaucher.model.InlineResponse2009;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for VersesApi
 */
@Ignore
public class VersesApiTest {

    private final VersesApi api = new VersesApi();

    
    /**
     * 
     *
     * Gets a &#x60;Verse&#x60; object for a given &#x60;bibleId&#x60; and &#x60;verseId&#x60;. This Verse object also includes an &#x60;content&#x60; property with the verse corresponding to the verseId. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getVerseTest() throws ApiException {
        String bibleId = null;
        String verseId = null;
        String contentType = null;
        Boolean includeNotes = null;
        Boolean includeTitles = null;
        Boolean includeChapterNumbers = null;
        Boolean includeVerseNumbers = null;
        Boolean includeVerseSpans = null;
        String parallels = null;
        Boolean useOrgId = null;
                InlineResponse20010 response = api.getVerse(bibleId, verseId, contentType, includeNotes, includeTitles, includeChapterNumbers, includeVerseNumbers, includeVerseSpans, parallels, useOrgId);
        // TODO: test validations
    }
    
    /**
     * 
     *
     * Gets an array of &#x60;Verse&#x60; objects for a given &#x60;bibleId&#x60; and &#x60;chapterId&#x60; 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getVersesTest() throws ApiException {
        String bibleId = null;
        String chapterId = null;
                InlineResponse2009 response = api.getVerses(bibleId, chapterId);
        // TODO: test validations
    }
    
}

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

import de.evangeliumstaucher.invoker.ApiCallback;
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.ApiResponse;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.Pair;
import de.evangeliumstaucher.invoker.ProgressRequestBody;
import de.evangeliumstaucher.invoker.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import de.evangeliumstaucher.model.InlineResponse2006;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PassagesApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public PassagesApi() {
        this(Configuration.getDefaultApiClient());
    }

    public PassagesApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public int getHostIndex() {
        return localHostIndex;
    }

    public void setHostIndex(int hostIndex) {
        this.localHostIndex = hostIndex;
    }

    public String getCustomBaseUrl() {
        return localCustomBaseUrl;
    }

    public void setCustomBaseUrl(String customBaseUrl) {
        this.localCustomBaseUrl = customBaseUrl;
    }

    /**
     * Build call for getPassage
     * @param bibleId Id of Bible for passage (required)
     * @param passageId String reference id for the requested passage. (required)
     * @param contentType Content type to be returned in the content property.  Supported values are &#x60;html&#x60; (default), &#x60;json&#x60; (beta), and &#x60;text&#x60; (beta) (optional, default to html)
     * @param includeNotes Include footnotes in content (optional, default to false)
     * @param includeTitles Include section titles in content (optional, default to true)
     * @param includeChapterNumbers Include chapter numbers in content (optional, default to false)
     * @param includeVerseNumbers Include verse numbers in content. (optional, default to true)
     * @param includeVerseSpans Include spans that wrap verse numbers and verse text for bible content. (optional, default to false)
     * @param parallels Comma delimited list of bibleIds to include (optional)
     * @param useOrgId Use the supplied id(s) to match the verseOrgId instead of the verseId (optional, default to false)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Sections for this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Passage not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getPassageCall(String bibleId, String passageId, String contentType, Boolean includeNotes, Boolean includeTitles, Boolean includeChapterNumbers, Boolean includeVerseNumbers, Boolean includeVerseSpans, String parallels, Boolean useOrgId, final ApiCallback _callback) throws ApiException {
        String basePath = null;

        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/v1/bibles/{bibleId}/passages/{passageId}"
            .replaceAll("\\{" + "bibleId" + "\\}", localVarApiClient.escapeString(bibleId.toString()))
            .replaceAll("\\{" + "passageId" + "\\}", localVarApiClient.escapeString(passageId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (contentType != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("content-type", contentType));
        }

        if (includeNotes != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include-notes", includeNotes));
        }

        if (includeTitles != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include-titles", includeTitles));
        }

        if (includeChapterNumbers != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include-chapter-numbers", includeChapterNumbers));
        }

        if (includeVerseNumbers != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include-verse-numbers", includeVerseNumbers));
        }

        if (includeVerseSpans != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include-verse-spans", includeVerseSpans));
        }

        if (parallels != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("parallels", parallels));
        }

        if (useOrgId != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("use-org-id", useOrgId));
        }

        final String[] localVarAccepts = {
            "*/*"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getPassageValidateBeforeCall(String bibleId, String passageId, String contentType, Boolean includeNotes, Boolean includeTitles, Boolean includeChapterNumbers, Boolean includeVerseNumbers, Boolean includeVerseSpans, String parallels, Boolean useOrgId, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'bibleId' is set
        if (bibleId == null) {
            throw new ApiException("Missing the required parameter 'bibleId' when calling getPassage(Async)");
        }
        
        // verify the required parameter 'passageId' is set
        if (passageId == null) {
            throw new ApiException("Missing the required parameter 'passageId' when calling getPassage(Async)");
        }
        

        okhttp3.Call localVarCall = getPassageCall(bibleId, passageId, contentType, includeNotes, includeTitles, includeChapterNumbers, includeVerseNumbers, includeVerseSpans, parallels, useOrgId, _callback);
        return localVarCall;

    }

    /**
     * 
     * Gets a &#x60;Passage&#x60; object for a given &#x60;bibleId&#x60; and &#x60;passageId&#x60;. This Passage object also includes an &#x60;content&#x60; property with all verses corresponding to the passageId. The &#x60;passageId&#x60; parameter can represent a chapter, verse, or range of verses. 
     * @param bibleId Id of Bible for passage (required)
     * @param passageId String reference id for the requested passage. (required)
     * @param contentType Content type to be returned in the content property.  Supported values are &#x60;html&#x60; (default), &#x60;json&#x60; (beta), and &#x60;text&#x60; (beta) (optional, default to html)
     * @param includeNotes Include footnotes in content (optional, default to false)
     * @param includeTitles Include section titles in content (optional, default to true)
     * @param includeChapterNumbers Include chapter numbers in content (optional, default to false)
     * @param includeVerseNumbers Include verse numbers in content. (optional, default to true)
     * @param includeVerseSpans Include spans that wrap verse numbers and verse text for bible content. (optional, default to false)
     * @param parallels Comma delimited list of bibleIds to include (optional)
     * @param useOrgId Use the supplied id(s) to match the verseOrgId instead of the verseId (optional, default to false)
     * @return InlineResponse2006
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Sections for this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Passage not found </td><td>  -  </td></tr>
     </table>
     */
    public InlineResponse2006 getPassage(String bibleId, String passageId, String contentType, Boolean includeNotes, Boolean includeTitles, Boolean includeChapterNumbers, Boolean includeVerseNumbers, Boolean includeVerseSpans, String parallels, Boolean useOrgId) throws ApiException {
        ApiResponse<InlineResponse2006> localVarResp = getPassageWithHttpInfo(bibleId, passageId, contentType, includeNotes, includeTitles, includeChapterNumbers, includeVerseNumbers, includeVerseSpans, parallels, useOrgId);
        return localVarResp.getData();
    }

    /**
     * 
     * Gets a &#x60;Passage&#x60; object for a given &#x60;bibleId&#x60; and &#x60;passageId&#x60;. This Passage object also includes an &#x60;content&#x60; property with all verses corresponding to the passageId. The &#x60;passageId&#x60; parameter can represent a chapter, verse, or range of verses. 
     * @param bibleId Id of Bible for passage (required)
     * @param passageId String reference id for the requested passage. (required)
     * @param contentType Content type to be returned in the content property.  Supported values are &#x60;html&#x60; (default), &#x60;json&#x60; (beta), and &#x60;text&#x60; (beta) (optional, default to html)
     * @param includeNotes Include footnotes in content (optional, default to false)
     * @param includeTitles Include section titles in content (optional, default to true)
     * @param includeChapterNumbers Include chapter numbers in content (optional, default to false)
     * @param includeVerseNumbers Include verse numbers in content. (optional, default to true)
     * @param includeVerseSpans Include spans that wrap verse numbers and verse text for bible content. (optional, default to false)
     * @param parallels Comma delimited list of bibleIds to include (optional)
     * @param useOrgId Use the supplied id(s) to match the verseOrgId instead of the verseId (optional, default to false)
     * @return ApiResponse&lt;InlineResponse2006&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Sections for this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Passage not found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<InlineResponse2006> getPassageWithHttpInfo(String bibleId, String passageId, String contentType, Boolean includeNotes, Boolean includeTitles, Boolean includeChapterNumbers, Boolean includeVerseNumbers, Boolean includeVerseSpans, String parallels, Boolean useOrgId) throws ApiException {
        okhttp3.Call localVarCall = getPassageValidateBeforeCall(bibleId, passageId, contentType, includeNotes, includeTitles, includeChapterNumbers, includeVerseNumbers, includeVerseSpans, parallels, useOrgId, null);
        Type localVarReturnType = new TypeToken<InlineResponse2006>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Gets a &#x60;Passage&#x60; object for a given &#x60;bibleId&#x60; and &#x60;passageId&#x60;. This Passage object also includes an &#x60;content&#x60; property with all verses corresponding to the passageId. The &#x60;passageId&#x60; parameter can represent a chapter, verse, or range of verses. 
     * @param bibleId Id of Bible for passage (required)
     * @param passageId String reference id for the requested passage. (required)
     * @param contentType Content type to be returned in the content property.  Supported values are &#x60;html&#x60; (default), &#x60;json&#x60; (beta), and &#x60;text&#x60; (beta) (optional, default to html)
     * @param includeNotes Include footnotes in content (optional, default to false)
     * @param includeTitles Include section titles in content (optional, default to true)
     * @param includeChapterNumbers Include chapter numbers in content (optional, default to false)
     * @param includeVerseNumbers Include verse numbers in content. (optional, default to true)
     * @param includeVerseSpans Include spans that wrap verse numbers and verse text for bible content. (optional, default to false)
     * @param parallels Comma delimited list of bibleIds to include (optional)
     * @param useOrgId Use the supplied id(s) to match the verseOrgId instead of the verseId (optional, default to false)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Sections for this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Passage not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getPassageAsync(String bibleId, String passageId, String contentType, Boolean includeNotes, Boolean includeTitles, Boolean includeChapterNumbers, Boolean includeVerseNumbers, Boolean includeVerseSpans, String parallels, Boolean useOrgId, final ApiCallback<InlineResponse2006> _callback) throws ApiException {

        okhttp3.Call localVarCall = getPassageValidateBeforeCall(bibleId, passageId, contentType, includeNotes, includeTitles, includeChapterNumbers, includeVerseNumbers, includeVerseSpans, parallels, useOrgId, _callback);
        Type localVarReturnType = new TypeToken<InlineResponse2006>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
